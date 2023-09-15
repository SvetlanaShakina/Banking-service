package io.bank.banking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.persistence.Column;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    private String name;

    @Column(updatable = false)
    private String pin;

    @Column(updatable = false, unique = true)
    private String number;

    @Column
    private double balance;
}
