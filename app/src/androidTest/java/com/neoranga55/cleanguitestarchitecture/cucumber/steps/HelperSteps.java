package com.neoranga55.cleanguitestarchitecture.cucumber.steps;

import android.app.Instrumentation;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.EspressoException;

import com.neoranga55.cleanguitestarchitecture.util.SpoonScreenshotAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Class containing generic Cucumber test step definitions not related to specific views
 */
public class HelperSteps {

    private static Scenario scenario;

    @Before
    public static void before(final Scenario scenario) {
        HelperSteps.scenario = scenario;
        grantWritePermissionsForScreenshots();
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

    private static void grantWritePermissionsForScreenshots() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
                String appPackage = getTargetContext().getPackageName();
                instrumentation.getUiAutomation().executeShellCommand("pm grant " + appPackage
                    + " android.permission.READ_EXTERNAL_STORAGE");
                instrumentation.getUiAutomation().executeShellCommand("pm grant " + appPackage
                    + " android.permission.WRITE_EXTERNAL_STORAGE");
            } catch (Exception e) {
                throw new RuntimeException(
                    "Exception while granting external storage access to application apk", e);
            }
        }
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

    @Given("^I take a screenshot$")
    public void i_take_a_screenshot() {
        takeScreenshot("screenshot");
    }

    public static class ScreenshotException extends RuntimeException implements EspressoException {
        private static final long serialVersionUID = -1247022787790657324L;

        ScreenshotException(final String message) {
            super(message);
        }
    }

}