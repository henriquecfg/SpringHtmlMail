package com.henrique.services;


import com.henrique.email.MailService;
import com.henrique.models.BankAccount;
import com.henrique.models.Person;
import com.henrique.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MailService mailService;

    public BankAccount findOne(Integer id){
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        return bankAccount.orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "BankAccount with ID "+ id +" not found.")
        );
    }

    public List<BankAccount> findAll(){
        return bankAccountRepository.findAll();
    }

    public BankAccount save(BankAccount bankAccount,Integer ownerId){
        bankAccount.setCreatedAt(LocalDateTime.now());
        Person owner = personService.findOne(ownerId);
        bankAccount.setOwner(owner);
        BankAccount bankAccountSaved = bankAccountRepository.save(bankAccount);
        mailService.sendConfirmationHtmlMail(bankAccount);
        return bankAccountSaved;
    }

    public BankAccount update(BankAccount bankAccount,Integer ownerId){
        BankAccount oldBankAccount = findOne(bankAccount.getId());
        if(oldBankAccount.getOwner().getId().equals(ownerId)){
            bankAccount.setOwner(oldBankAccount.getOwner());
            bankAccount.setCreatedAt(oldBankAccount.getCreatedAt());
            return bankAccountRepository.save(bankAccount);
        }
        throw new  ResponseStatusException(NOT_FOUND, "Not the owner.");
    }

    public void delete(Integer id){
        findOne(id);
        bankAccountRepository.deleteById(id);
    }
}
