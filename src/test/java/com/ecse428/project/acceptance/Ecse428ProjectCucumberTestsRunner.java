package com.ecse428.project.acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@Tag("AcceptanceTest")
@RunWith(Cucumber.class)
@CucumberOptions(features = "gherkin/implemented")
public class Ecse428ProjectCucumberTestsRunner {
}
