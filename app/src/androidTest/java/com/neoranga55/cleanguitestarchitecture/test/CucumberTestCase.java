package com.neoranga55.cleanguitestarchitecture.test;

import cucumber.api.CucumberOptions;

/**
 * This class configures the Cucumber test framework and Java glue code
 */
@CucumberOptions(features = "features", // Test scenarios
        glue = {"com.neoranga55.cleanguitestarchitecture.cucumber.steps"}, // Steps definitions
        format = {"pretty", // Cucumber report formats and location to store them in phone
                "html:/mnt/sdcard/cucumber-reports/cucumber-html-report",
                "json:/mnt/sdcard/cucumber-reports/cucumber.json",
                "junit:/mnt/sdcard/cucumber-reports/cucumber.xml"
        },
        tags={"~@manual", "@login-scenarios"}
)
public class CucumberTestCase {
}
