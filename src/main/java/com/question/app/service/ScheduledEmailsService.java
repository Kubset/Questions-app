package com.question.app.service;

import com.question.app.model.ScheduledEmails;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IScheduledEmailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledEmailsService {

    IScheduledEmailsRepository scheduledEmailsRepository;
    ICategoryRepository categoryRepository;

    @Autowired
    public ScheduledEmailsService(IScheduledEmailsRepository scheduledEmailsRepository, ICategoryRepository categoryRepository) {
        this.scheduledEmailsRepository = scheduledEmailsRepository;
        this.categoryRepository = categoryRepository;
    }

    public ScheduledEmails saveScheduledEmail(ScheduledEmails scheduledEmails) {
        scheduledEmails.setChoosenCategories(scheduledEmails.getChoosenCategories().stream().map(category -> categoryRepository.findByName(category.getName()).get()).collect(Collectors.toList()));
        scheduledEmailsRepository.save(scheduledEmails);
        return scheduledEmails;
    }

    public List<ScheduledEmails> getAll() {
        List<ScheduledEmails> ss = scheduledEmailsRepository.findAll();
        return ss;
    }
}
