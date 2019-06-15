package com.question.app.service;

import com.question.app.model.ScheduledEmail;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IScheduledEmailRepository;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledEmailService {

    IScheduledEmailRepository scheduledEmailsRepository;
    ICategoryRepository categoryRepository;

    @Autowired
    public ScheduledEmailService(IScheduledEmailRepository scheduledEmailsRepository, ICategoryRepository categoryRepository) {
        this.scheduledEmailsRepository = scheduledEmailsRepository;
        this.categoryRepository = categoryRepository;
    }

    public ScheduledEmail saveScheduledEmail(ScheduledEmail scheduledEmail) {
        scheduledEmail.setChoosenCategories(scheduledEmail.getChoosenCategories().stream().map(category -> categoryRepository.findByName(category.getName()).get()).collect(Collectors.toList()));
        scheduledEmailsRepository.save(scheduledEmail);
        return scheduledEmail;
    }

    public List<ScheduledEmail> getAll() {
        return scheduledEmailsRepository.findAll();
    }

    public Long getNewFireTime(String cron) {
        long nextExecution = -1L;

        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date today = new Date();
            nextExecution = cronExpression.getNextValidTimeAfter(today).getTime();

        } catch (Exception e) {
            System.out.println("catch" + e);
        }
        return nextExecution;
    }
}
