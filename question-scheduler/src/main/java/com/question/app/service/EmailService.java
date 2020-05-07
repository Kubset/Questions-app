package com.question.app.service;

import com.google.common.collect.Lists;
import com.question.app.model.Category;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class EmailService {


    @Autowired
    private it.ozimov.springboot.mail.service.EmailService defaultEmailService;

    @Autowired
    private Environment env;

    @Autowired
    public EmailService(it.ozimov.springboot.mail.service.EmailService defaultEmailService) {
        this.defaultEmailService = defaultEmailService;
    }



    public void sendEmail(String recipient, String body, String topic) {

        String personal = env.getProperty("mail.default.pesonal");
        String username = env.getProperty("spring.mail.username");
        String subject = env.getProperty("mail.default.subject");

        try {

            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress(username, personal))
                    .to(Lists.newArrayList(new InternetAddress(recipient, personal)))
                    .subject(topic)
                    .body(body)
                    .encoding("UTF-8").build();


            defaultEmailService.send(email);
        } catch(Exception e) {

            System.out.println("mail error" + e);
        }
    }



//
}
