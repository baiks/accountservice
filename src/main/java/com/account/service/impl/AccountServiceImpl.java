package com.account.service.impl;


import com.account.service.dtos.AccountsDto;
import com.account.service.dtos.NotificationDto;
import com.account.service.entities.AccountSeq;
import com.account.service.entities.Accounts;
import com.account.service.entities.Customers;
import com.account.service.exception.CustomException;
import com.account.service.kafka.Producer;
import com.account.service.repos.AccountsRepo;
import com.account.service.repos.AccountsSeqRepo;
import com.account.service.repos.CustomersRepo;
import com.account.service.services.AccountService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final Producer producer;
    private final AccountsRepo accountsRepo;
    private final AccountsSeqRepo accountsSeqRepo;
    private final ModelMapper modelMapper;
    private final CustomersRepo customersRepo;

    /**
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<Accounts> create(Long customerId) {

        List<AccountSeq> accountSeqs = accountsSeqRepo.findAll();
        AccountSeq accountseq = new AccountSeq();
        int accountSeq = 1;
        if (accountSeqs.size() != 0) {
            accountSeq = accountSeqs.get(0).getSequence();
        } else {
            accountseq = accountSeqs.get(0);
        }
        accountseq.setSequence(accountSeq);
        accountsSeqRepo.save(accountseq);

        String paddedAccountNumber = StringUtils.leftPad(String.valueOf(accountSeq), 10, '0');

        Optional<Accounts> res = accountsRepo.findByAccountNumber(paddedAccountNumber);
        if (res.isPresent()) {
            throw new CustomException("Duplicate account number detected");
        }

        //Capture account
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(paddedAccountNumber);
        Optional<Customers> customers = customersRepo.findById(customerId);
        if (!customers.isPresent()) {
            throw new CustomException("Customer id not found");
        }
        accounts.setCustomer(customers.get());
        accountsRepo.save(accounts);


        //Could do better by maintaining a message template
        String message = "Dear Customer, your account has been created is successfully. You can now deposit some cash";
        NotificationDto notificationDto = NotificationDto.builder().message(message).recipient(customers.get().getMobileNumber()).type(1).build();
        producer.sendMessage("notifications-topic", new Gson().toJson(notificationDto));
        return new ResponseEntity<>(accounts, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Accounts> findById(Long id) {
        Optional<Accounts> accounts = accountsRepo.findById(id);
        if (!accounts.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Accounts> close(Long id) {
        Optional<Accounts> accounts = accountsRepo.findById(id);
        if (!accounts.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        Accounts udaptedAccount = accounts.get();
        udaptedAccount.setClosed(true);
        accountsRepo.save(udaptedAccount);
        return new ResponseEntity<>(udaptedAccount, HttpStatus.OK);

    }
}