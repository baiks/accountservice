package com.account.service.services;

import com.account.service.dtos.AccountsDto;
import com.account.service.entities.Accounts;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    /**
     *
     * @param customerId
     * @return
     */
    ResponseEntity<Accounts> create(Long customerId);


    /**
     * @param id
     * @return
     */
    ResponseEntity<Accounts> findById(Long id);

    /**
     *
     * @param id
     * @return
     */
    ResponseEntity<Accounts> close(Long id);

    ResponseEntity<Accounts> activate(Long id);

    ResponseEntity<Accounts> deActivate(Long id);
}