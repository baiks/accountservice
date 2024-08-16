package com.account.service.repos;

import com.account.service.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customers, Long> {
}
