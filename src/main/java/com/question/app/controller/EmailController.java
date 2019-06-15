package com.question.app.controller;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailSchedulerService;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.InternetAddress;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("email")
public class EmailController {

    EmailService emailService;
    EmailSchedulerService emailSchedulerService;


    @Autowired
    public EmailController(EmailService emailService, EmailSchedulerService emailSchedulerService) {
        this.emailService = emailService;
        this.emailSchedulerService = emailSchedulerService;
    }

    public void sendEmailWithoutTemplating() {
        try {

            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress("tomasz.kwiatkowski.8588@gmail.com", "Test"))
                    .to(Lists.newArrayList(new InternetAddress("kuba1228334@gmail.com", "Pomponius Attĭcus")))
                    .subject("Laelius de amicitia")
                    .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")
                    .encoding("UTF-8").build();


            emailService.send(email);
        } catch(Exception e) {

            System.out.println("mail error");
        }
    }


    public void scheduleEmail() {
        try {

            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress("tomasz.kwiatkowski.8588@gmail.com", "Test"))
                    .to(Lists.newArrayList(new InternetAddress("kuba1228334@gmail.com", "Pomponius Attĭcus")))
                    .subject("Laelius de amicitia")
                    .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")
                    .encoding("UTF-8").build();

            final OffsetDateTime scheduledDateTime = OffsetDateTime.now().plusMinutes(1);
            final int priorityLevel = 1;
            emailSchedulerService.schedule(email, scheduledDateTime, priorityLevel);
        } catch(Exception e) {
            System.out.println("scheduler fail");
        }
    }

    @GetMapping
    public String sendEmail() {
        sendEmailWithoutTemplating();
        return "email sent";
    }

    @GetMapping("schedule")
    public String scheduleEmaill() {
        scheduleEmail();
        return "email scheduled";
    }




}
