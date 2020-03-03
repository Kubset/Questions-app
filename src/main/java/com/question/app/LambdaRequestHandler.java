package com.question.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.question.app.scheduler.Scheduler;
import com.question.app.service.BeanUtil;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@EnableEmailTools
public class LambdaRequestHandler implements RequestHandler<Object, String> {

    Scheduler scheduler;



    public LambdaRequestHandler() {
        DemoApplication.main(new String[0]);
        this.scheduler = BeanUtil.getBean(Scheduler.class);
    }

    public String handleRequest(Object input, Context context) {


        context.getLogger().log("Input: " + input.toString());
        context.getLogger().log(scheduler.toString());
        scheduler.emailSender();
        return "scheduler sent.";
    }

}
