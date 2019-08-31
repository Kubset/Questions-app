package com.question.app.scheduler;


import com.question.app.model.Category;
import com.question.app.model.Question;
import com.question.app.model.ScheduledEmail;
import com.question.app.service.CategoryService;
import com.question.app.service.EmailService;
import com.question.app.service.QuestionService;
import com.question.app.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collections;
import java.util.List;

@EnableScheduling
@Configuration
public class Scheduler {

    private ScheduledEmailService scheduledEmailService;
    private EmailService emailService;
    private CategoryService categoryService;
    private QuestionService questionService;

    @Autowired
    public Scheduler(QuestionService questionService, CategoryService categoryService, ScheduledEmailService scheduledEmailService, EmailService emailService) {
        this.scheduledEmailService = scheduledEmailService;
        this.emailService = emailService;
        this.categoryService = categoryService;
        this.questionService = questionService;
    }

    @Scheduled(cron = "${mail.scheduler.cron}")
    public void emailSender() {

        List<ScheduledEmail> scheduledEmailList = scheduledEmailService.getAll();
        Long time = System.currentTimeMillis();
        System.out.println(scheduledEmailList);
        System.out.println(time);

        scheduledEmailList.stream()
                .filter(scheduledEmails -> scheduledEmails.getFireTime() < time)
                .forEach(scheduledEmails -> {
                    scheduledEmails.setFireTime(scheduledEmailService.getNewFireTime(scheduledEmails.getCron()));
                    scheduledEmailService.saveScheduledEmail(scheduledEmails);
                    emailService.sendEmail(scheduledEmails.getRecipient(), generateBody(scheduledEmails.getChoosenCategories()), scheduledEmails.getTopic());

                    System.out.println("email sent to" + scheduledEmails.getRecipient());
                });


    }




    private String generateBody(List<Category> categories) {
        int i=0;

        List<Question> questions = questionService.getQuestionsByCategory(categories);
        Collections.shuffle(questions);
        questions = questions.subList(0, 5);

        StringBuilder sb = new StringBuilder("Example set of questions: \n");

        questions.forEach(q -> sb.append("(")
                                 .append(q.getCategory().getName().toUpperCase())
                                 .append(")  ")
                                 .append(q.getQuestion())
                                 .append("\n"));

        return sb.toString();

    }
}
