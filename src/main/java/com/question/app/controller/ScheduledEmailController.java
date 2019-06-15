package com.question.app.controller;

import com.question.app.model.ScheduledEmail;
import com.question.app.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class ScheduledEmailController {

    ScheduledEmailService scheduledEmailService;

    @Autowired
    public ScheduledEmailController(ScheduledEmailService scheduledEmailService) {
        this.scheduledEmailService = scheduledEmailService;
    }

    @PostMapping
    public ScheduledEmail addScheduledEmail(@RequestBody ScheduledEmail scheduledEmail) {
        return scheduledEmailService.saveScheduledEmail(scheduledEmail);
    }


}
