package com.example.AccountManager.person;
import com.example.AccountManager.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPersonAccounts() {
        return personDao.getAllPersonAccounts();
    }

    public List<PersonTransaction> getAllTransactions(UUID personId) {
        return personDao.getPersonTransaction(personId);
    }

    public void updateTransaction(Transactions transactions) {
        updateFirstUserDetails(transactions);
        updateSecondUserDetails(transactions);
    }
       public void updateFirstUserDetails(Transactions transactions)  {
           Integer NewBalance1 =0;
           List<Integer> balance = personDao.getBalance(transactions.getClientUuid()).stream().map(person -> person.getBalance()).collect(Collectors.toList());
           for (Integer amount : balance) NewBalance1 = amount - transactions.getAmountTransfer();
           if(NewBalance1<0) throw new ApiRequestException("Amount To Low for Transaction");
           personDao.updatePersonBalance(transactions.getClientUuid(),NewBalance1);
           personDao.updateTransactionBalance(transactions.getClientUuid(),transactions.getAmountTransfer(),transactions.getToClientUuid(),NewBalance1);
       }
    public void updateSecondUserDetails(Transactions transactions) {
        Integer NewBalance2 =0;
        List<Integer> balance2 = personDao.getBalance(transactions.getToClientUuid()).stream().map(person -> person.getBalance()).collect(Collectors.toList());
        for (Integer amount : balance2) NewBalance2 = amount + transactions.getAmountTransfer();
        personDao.updatePersonBalance(transactions.getToClientUuid(),NewBalance2);
    }
}