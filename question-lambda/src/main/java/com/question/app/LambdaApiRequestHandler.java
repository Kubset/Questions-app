package com.question.app;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class LambdaApiRequestHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    private static SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;


    static {
        try {

            handler = SpringLambdaContainerHandler.getAwsProxyHandler(MvcConfig.class);
        } catch (Exception e) {

            System.out.println("kalalaldskdsadj");
        }

    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {

        return handler.proxy(awsProxyRequest, context);
    }

}
