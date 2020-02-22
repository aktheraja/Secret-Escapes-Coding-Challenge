CREATE TABLE IF NOT EXISTS person (
    person_id UUID PRIMARY KEY NOT NULL,
    full_name VARCHAR(30) NOT NULL,
    balance INTEGER NOT NULL,
    email VARCHAR(30) NOT NULL,
     UNIQUE (person_id,email)
);




CREATE TABLE IF NOT EXISTS person_transactions (
    client_id UUID NOT NULL,
    transaction_date Date NOT NULL,
    AmountTx INTEGER NOT NULL,
    ToPersonUuid UUID NOT NULL,
    balance INTEGER NOT NULL
);