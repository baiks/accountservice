package com.account.service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"account_number"}))
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String accountNumber;

    @Column(nullable = false)
    private Long balance = 0L;

    @Column(nullable = false, columnDefinition = "bit not null default 0")
    private boolean active;

    @Column(nullable = false, columnDefinition = "bit not null default 0")
    private boolean closed;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customers customer;

}