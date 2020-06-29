package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import ru.javawebinar.topjava.Performance;

import java.util.HashMap;
import java.util.Map;

abstract public class ServiceTest {
    private static Map<String, Long> result = new HashMap<>();

    @Rule
    public Performance perf = new Performance(result);

    @AfterClass
    public static void printResult() {
        System.out.println("=======================");
        System.out.println("Result:");
        result.forEach((name, res) -> System.out.println(name + " - " + res + "ms"));
    }
}
