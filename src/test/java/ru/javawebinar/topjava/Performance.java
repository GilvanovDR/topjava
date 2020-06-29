package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Map;

public class Performance implements TestRule {
    Map<String, Long> resultMap;

    public Performance(Map<String, Long> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("=======================");
                System.out.println("Start test: " + description.getMethodName());
                long startTime = System.currentTimeMillis();
                base.evaluate();
                long time = System.currentTimeMillis() - startTime;
                System.out.println("=======================");
                System.out.println(description.getMethodName() + " - " + time + "ms");
                resultMap.put(description.getMethodName(), time);
            }
        };
    }
}
