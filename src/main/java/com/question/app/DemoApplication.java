package com.question.app;

import com.question.app.scheduler.Scheduler;
import com.question.app.service.QuestionService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;


@SpringBootApplication
@EnableEmailTools
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }

}


