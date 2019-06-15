package com.question.app.task;


import com.google.common.collect.Lists;
import com.question.app.model.Category;
import com.question.app.model.Question;
import com.question.app.model.ScheduledEmails;
import com.question.app.service.CategoryService;
import com.question.app.service.QuestionService;
import com.question.app.service.ScheduledEmailsService;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.internet.InternetAddress;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Configuration
public class Task {

    ScheduledEmailsService scheduledEmailsService;
    EmailService emailService;
    CategoryService categoryService;
    QuestionService questionService;

    @Autowired
    public Task(QuestionService questionService, CategoryService categoryService, ScheduledEmailsService scheduledEmailsService, EmailService emailService) {
        this.scheduledEmailsService = scheduledEmailsService;
        this.emailService = emailService;
        this.categoryService = categoryService;
        this.questionService = questionService;
    }

    @Scheduled(cron = "${mail.scheduler.cron}")
    public void scheduleTaskUsingCronExpression() {

        List<ScheduledEmails> scheduledEmailsList = scheduledEmailsService.getAll();
        Long time = System.currentTimeMillis();


        scheduledEmailsList.stream()
                .filter(scheduledEmails -> Long.valueOf(scheduledEmails.getFireTime()) < time)
                .forEach(scheduledEmails -> {
                    scheduledEmails.setFireTime(getNewFireTime(scheduledEmails.getCron()));
                    scheduledEmailsService.saveScheduledEmail(scheduledEmails);
                    sendEmail(scheduledEmails.getRecipient(), scheduledEmails.getChoosenCategories());

                    System.out.println("email sent to" + scheduledEmails.getRecipient());


                });


    }

    private Long getNewFireTime(String cron) {

        Long nextExecution = -1L;

        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date today = new Date();
            nextExecution = cronExpression.getNextValidTimeAfter(today).getTime();

            System.out.println(today.toString());
            System.out.println(nextExecution);
        } catch (Exception e) {

            System.out.println("catch" + e);
            }


            return nextExecution;
    }

    private void sendEmail(String recivierEmail, List<Category> categories) {
        String body = generateBody(categories);
        String subject = "subject";
        String personal = "personal";

        try {

            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress("tomasz.kwiatkowski.8588@gmail.com", "Test"))
                    .to(Lists.newArrayList(new InternetAddress(recivierEmail, personal)))
                    .subject(subject)
                    .body(body)
                    .encoding("UTF-8").build();


            emailService.send(email);
        } catch(Exception e) {

            System.out.println("mail error");
        }
    }

    private String generateBody(List<Category> categories) {
        List<Question> questions = categories
                .stream()
                .map(c -> questionService.getQuestionsByCategoryName(c.getName()))
                .flatMap((x) -> x.stream())
                .collect(Collectors.toList());

        Collections.shuffle(questions);

        questions = questions.subList(0, 5);

        StringBuilder sb = new StringBuilder("Example set of questions: \n");

        questions.forEach(q -> sb.append(q.getQuestion()).append("\n"));

        return sb.toString();

    }
}
