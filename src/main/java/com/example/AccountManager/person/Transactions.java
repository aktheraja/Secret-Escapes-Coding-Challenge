package com.example.AccountManager.person;

import java.util.UUID;

public class Transactions {
    private final UUID clientUuid;
    private final Integer AmountTransfer;
    private final UUID ToClientUuid;

    public Transactions(UUID clientUuid, Integer amountTransfer, UUID toClientUuid) {
        this.clientUuid = clientUuid;
        AmountTransfer = amountTransfer;
        ToClientUuid = toClientUuid;
    }

    public UUID getClientUuid() {
        return clientUuid;
    }

    public Integer getAmountTransfer() {
        return AmountTransfer;
    }

    public UUID getToClientUuid() {
        return ToClientUuid;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "clientUuid=" + clientUuid +
                ", AmountTransfer=" + AmountTransfer +
                ", ToClientUuid=" + ToClientUuid +
                '}';
    }
}
