package com.account.service.repos;

import com.account.service.entities.Accounts;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByAccountNumber(String name);

    @Cacheable("user")
    Optional<Accounts> findById(long id);
}
