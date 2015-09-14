package com.neoranga55.cleanguitestarchitecture.cucumber;

import android.os.Bundle;

import cucumber.api.android.CucumberInstrumentationCore;

/**
 * Used in build.gradle/testInstrumentationRunner to run Cucumber tests
 */
public class CucumberTestRunner extends android.support.test.runner.AndroidJUnitRunner {

    public static final String TAG = CucumberTestRunner.class.getSimpleName();
    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        instrumentationCore.create(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
