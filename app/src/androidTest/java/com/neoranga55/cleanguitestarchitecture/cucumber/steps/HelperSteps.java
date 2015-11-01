package com.neoranga55.cleanguitestarchitecture.cucumber.steps;

import android.support.test.espresso.EspressoException;

import com.neoranga55.cleanguitestarchitecture.util.SpoonScreenshotAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * Class containing generic Cucumber test step definitions not related to specific views
 */
public class HelperSteps {

    private static Scenario scenario;

    @Before
    public static void before(final Scenario scenario) {
        HelperSteps.scenario = scenario;
    }

    public static Scenario getScenario() {
        return HelperSteps.scenario;
    }

    @After
    public static void after() {
        if ((HelperSteps.scenario != null) && (HelperSteps.scenario.isFailed())) {
            takeScreenshot("failed");
        }
    }

    @Given("^I take a screenshot$")
    public void i_take_a_screenshot() {
        takeScreenshot("screenshot");
    }

    /**
     * Take a screenshot of the current activity and embed it in the HTML report
     *
     * @param tag Name of the screenshot to include in the file name
     */
    public static void takeScreenshot(final String tag) {
        if (scenario == null) {
            throw new ScreenshotException("Error taking screenshot: I'm missing a valid test scenario to attach the screenshot to");
        }
        SpoonScreenshotAction.perform(tag);
        final File screenshot = SpoonScreenshotAction.getLastScreenshot();
        if (screenshot == null) {
            throw new ScreenshotException("Screenshot was not taken correctly, check for failures in screenshot library");
        }
        FileInputStream screenshotStream = null;
        try {
            screenshotStream = new FileInputStream(screenshot);
            final byte fileContent[] = new byte[(int) screenshot.length()];
            final int readImageBytes = screenshotStream.read(fileContent); // Read data from input image file into an array of bytes
            if (readImageBytes != -1) {
                scenario.embed(fileContent, "image/png"); // Embed the screenshot in the report under current test step
            }
        } catch (final IOException ioe) {
            throw new ScreenshotException("Exception while reading file " + ioe);
        } finally {
            try { // close the streams using close method
                if (screenshotStream != null) {
                    screenshotStream.close();
                }
            } catch (final IOException ioe) {
                //noinspection ThrowFromFinallyBlock
                throw new ScreenshotException("Error while closing screenshot stream: " + ioe);
            }
        }
    }

    public static class ScreenshotException extends RuntimeException implements EspressoException {
        private static final long serialVersionUID = -1247022787790657324L;

        ScreenshotException(final String message) {
            super(message);
        }
    }

}