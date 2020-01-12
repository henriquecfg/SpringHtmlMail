package com.henrique.email;

import com.henrique.models.BankAccount;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface MailService {

    void sendBankConfirmatonMail(BankAccount obj);

    void sendMail(SimpleMailMessage msg);

    void sendConfirmationHtmlMail(BankAccount bankAccount);

    void sendHtmlMail(MimeMessage msg);

}
