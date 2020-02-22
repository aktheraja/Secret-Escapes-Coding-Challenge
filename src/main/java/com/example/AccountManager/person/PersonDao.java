package com.example.AccountManager.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Repository
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Person> getAllPersonAccounts(){
        String sql = "SELECT  " +
                "person_id ," +
                "full_name," +
                "balance," +
                "email " +
                "FROM Person";
        return jdbcTemplate.query(sql,mapPersonFromDb());

    }

    private RowMapper<Person> mapPersonFromDb() {
        return (resultSet,i)->
             new Person(
                     UUID.fromString(resultSet.getString("person_id")),
                     resultSet.getString("full_name"),
                     resultSet.getInt("balance"),
                     resultSet.getString("email")
             );

    }

     List<PersonTransaction> getPersonTransaction(UUID personId){

        String sql = "SELECT client_id, transaction_date,AmountTx,ToPersonUuid,balance FROM person_transactions WHERE client_id = ?";
      return jdbcTemplate.query(
              sql,
              new Object[]{personId},
              mapTransactionFromDb()
      );
    }

    private RowMapper<PersonTransaction> mapTransactionFromDb() {
        return ((resultSet, i) ->
                 new PersonTransaction(
                        UUID.fromString(resultSet.getString("client_id")),
                        resultSet.getDate("transaction_date"),
                        resultSet.getInt("AmountTx"),
                        UUID.fromString(resultSet.getString("ToPersonUuid")),
                        resultSet.getInt("balance")
        ));

    }

   int updatePersonBalance(UUID Uuid, Integer balance){
        String sql = "UPDATE person SET balance =? WHERE person_id = ? ";
        return jdbcTemplate.update(sql,balance,Uuid);
   }
    int updateTransactionBalance(UUID client_id,Integer AmountTx,UUID ToPersonUuid, Integer balance ){

        String sql = "INSERT INTO person_transactions (client_id,transaction_date,AmountTx,ToPersonUuid,balance) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,client_id,new Date(),AmountTx,ToPersonUuid,balance);
    }


    List<Person> getBalance(UUID personId){
        String sql = "SELECT person_id , full_name, balance, email FROM Person WHERE person_id = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{personId},
                mapBalanceFromDb()
        );
    }

    private RowMapper<Person> mapBalanceFromDb() {
        return ((resultSet, i) ->
                new Person(
                        UUID.fromString(resultSet.getString("person_id")),
                        resultSet.getString("full_name"),
                        resultSet.getInt("balance"),
                        resultSet.getString("email")
                )
        );
    }
}
