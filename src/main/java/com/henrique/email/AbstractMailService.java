package com.henrique.email;

import com.henrique.models.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class AbstractMailService implements MailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendBankConfirmatonMail(BankAccount obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromBankAccount(obj);
        sendMail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromBankAccount(BankAccount obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getOwner().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Confirmed Account");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    @Override
    public void sendMail(SimpleMailMessage msg) {

    }

    protected String htmlFromTemplate(BankAccount obj){
        Context context = new Context();
        context.setVariable("bankAccount",obj);
        return templateEngine.process("mail/newBankAccount",context);
    }

    @Override
    public void sendConfirmationHtmlMail(BankAccount bankAccount) {
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessageFromBankAccount(bankAccount);
            sendHtmlMail(mm);
        } catch (MessagingException e) {
            sendBankConfirmatonMail(bankAccount);
        }

    }

    protected MimeMessage prepareMimeMessageFromBankAccount(BankAccount bankAccount) throws MessagingException {

        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmp = new MimeMessageHelper(mm,true);

        mmp.setTo(bankAccount.getOwner().getEmail());
        mmp.setFrom(sender);
        mmp.setSubject("Bank Confirmation");
        mmp.setSentDate(new Date(System.currentTimeMillis()));
        mmp.setText(htmlFromTemplate(bankAccount),true);
        return mm;
    }

    @Override
    public void sendHtmlMail(MimeMessage msg) {

    }
}
