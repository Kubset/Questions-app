package com.question.app.controller;

import com.question.app.model.ScheduledEmails;
import com.question.app.service.ScheduledEmailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class ScheduledEmailsController {

    ScheduledEmailsService scheduledEmailsService;

    @Autowired
    public ScheduledEmailsController(ScheduledEmailsService scheduledEmailsService) {
        this.scheduledEmailsService = scheduledEmailsService;
    }

    @PostMapping
    public ScheduledEmails addScheduledEmail(@RequestBody ScheduledEmails scheduledEmails) {
        return scheduledEmailsService.saveScheduledEmail(scheduledEmails);
    }


}
