package com.account.service.repos;

import com.account.service.entities.AccountSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsSeqRepo extends JpaRepository<AccountSeq, Long> {
}
