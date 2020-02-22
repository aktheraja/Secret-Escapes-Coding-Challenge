package com.example.AccountManager.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("accounts")
    public List<Person> getAllPerson(){
        return personService.getAllPersonAccounts();
    }
   @GetMapping(path="{personId}")
    public List<PersonTransaction> getAllTransaction(@PathVariable("personId") UUID personId){

        return personService.getAllTransactions(personId);
   }
   @PostMapping("transactions")
   public void updateTransaction(@RequestBody Transactions transactions)  {
        System.out.println(transactions.toString());
            personService.updateTransaction(transactions);
   }
}
