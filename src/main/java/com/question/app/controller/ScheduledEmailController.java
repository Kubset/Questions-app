package com.question.app.controller;

import com.question.app.model.ScheduledEmail;
import com.question.app.service.ScheduledEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//TODO: Add endpoints for managing scheduled emails.
/**
 * Simple controller for scheduling emails
 * @author Jakub Setla
 */
@RestController
@RequestMapping("/scheduler")
public class ScheduledEmailController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ScheduledEmailService scheduledEmailService;

    @Autowired
    public ScheduledEmailController(ScheduledEmailService scheduledEmailService) {
        this.scheduledEmailService = scheduledEmailService;
    }


    /**
     * Scheduling email with set of categories
     * @param scheduledEmail
     * @return newly created scheduledEmail with assigned id and default values
     */
    @PostMapping
    public ScheduledEmail addScheduledEmail(@RequestBody ScheduledEmail scheduledEmail) {
        log.info("Scheduling email with category set {}", scheduledEmail);
        return scheduledEmailService.saveScheduledEmail(scheduledEmail);
    }


}
