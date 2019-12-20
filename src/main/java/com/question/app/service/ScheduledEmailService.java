package com.question.app.service;

import com.question.app.model.Category;
import com.question.app.model.ScheduledEmail;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IScheduledEmailRepository;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

        scheduledEmail.setChoosenCategories(scheduledEmail.getChoosenCategories().stream()
                .map(category -> category.getName().charAt(category.getName().length() - 1) == '*' ?
                        Collections.singletonList(category) : flatTree(category))
                .flatMap(List::stream)
                .collect(Collectors.toList()));

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

    //TODO: move to utils or sth else
    private List<Category> flatTree(Category category) {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        if(category.getSubCategories().size() != 0) {
            category.getSubCategories().forEach(c -> categories.addAll(flatTree(c)));
        }

        return categories;
    }

}
