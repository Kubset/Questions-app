package com.question.app.service;

import com.google.common.collect.Lists;
import com.question.app.model.Category;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class EmailService {

    private it.ozimov.springboot.mail.service.EmailService defaultEmailService;

    @Autowired
    public EmailService(it.ozimov.springboot.mail.service.EmailService defaultEmailService) {
        this.defaultEmailService = defaultEmailService;
    }

    public void sendEmail(String recipient, String body) {
        String subject = "subject";
        String personal = "personal";

        try {

            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress("tomasz.kwiatkowski.8588@gmail.com", "Test"))
                    .to(Lists.newArrayList(new InternetAddress(recipient, personal)))
                    .subject(subject)
                    .body(body)
                    .encoding("UTF-8").build();


            defaultEmailService.send(email);
        } catch(Exception e) {

            System.out.println("mail error" + e);
        }
    }




}
