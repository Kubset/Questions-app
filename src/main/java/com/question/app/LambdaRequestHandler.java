package com.question.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.question.app.scheduler.Scheduler;
import com.question.app.service.BeanUtil;
import com.question.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import reactor.util.annotation.NonNull;

import java.util.Map;

public class LambdaRequestHandler implements RequestHandler<Object, String> {

    Scheduler scheduler;



    public LambdaRequestHandler() {
        String[] args = new String[0];
//        SpringApplication.run(DemoApplication.class, args);
        DemoApplication.main(args);
        this.scheduler = BeanUtil.getBean(Scheduler.class);
    }

    public String handleRequest(Object input, Context context) {


        context.getLogger().log("Input: " + input.toString());
        context.getLogger().log(scheduler.toString());
        scheduler.emailSender();
        return "scheduler sent.";
    }

}
