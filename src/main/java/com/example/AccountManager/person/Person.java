package com.example.AccountManager.person;

import java.util.UUID;

public class Person {
    private final UUID uuid;
    private final String name;
    private final Integer balance;
    private final String email;

    public Person(UUID uuid, String name, Integer balance, String email) {
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }
}
