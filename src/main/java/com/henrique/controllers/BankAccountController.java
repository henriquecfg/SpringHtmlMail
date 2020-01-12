package com.henrique.controllers;


import com.henrique.models.BankAccount;
import com.henrique.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/{bankAccount.id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankAccount> get(@PathVariable("bankAccount.id") Integer bankAccountId) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findOne(bankAccountId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BankAccount>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findAll());
    }

    @PostMapping("/{bankAccount.id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankAccount> create(@Validated @RequestBody BankAccount bankAccount,@PathVariable("bankAccount.id") Integer bankAccountId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.save(bankAccount, bankAccountId));
    }

    @PutMapping("/{bankAccount.id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankAccount> update(@Validated @RequestBody BankAccount bankAccount, @PathVariable("bankAccount.id") Integer bankAccountId) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.update(bankAccount,bankAccountId));
    }

    @DeleteMapping("/{bankAccount.id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("bankAccount.id") Integer bankAccountId) {
        bankAccountService.delete(bankAccountId);
    }
}
