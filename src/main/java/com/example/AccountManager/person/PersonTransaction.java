package com.example.AccountManager.person;

import java.util.Date;
import java.util.UUID;

public class PersonTransaction {
    private final UUID PersonUuid;
    private final Date date;
    private final Integer AmountTx;
    private final UUID ToPersonUuid;
    private final Integer balance;

    public PersonTransaction(UUID personUuid, Date date, Integer amountTx, UUID toPersonUuid, Integer balance) {
        PersonUuid = personUuid;
        this.date = date;
        AmountTx = amountTx;
        ToPersonUuid = toPersonUuid;
        this.balance = balance;
    }

    public UUID getPersonUuid() {
        return PersonUuid;
    }

    public Date getDate() {
        return date;
    }

    public Integer getAmountTx() {
        return AmountTx;
    }

    public UUID getToPersonUuid() {
        return ToPersonUuid;
    }

    public Integer getBalance() {
        return balance;
    }
}
