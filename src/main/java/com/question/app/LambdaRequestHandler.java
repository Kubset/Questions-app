package com.question.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;


public class LambdaRequestHandler implements RequestHandler<Map<String,String>, String> {

    public String handleRequest(Map<String,String> input, Context context) {

        context.getLogger().log("Input: " + input);
        System.out.println("TUUTTUUUT");
        return "[{\"hello\":\"world\"}]";
    }

}
