package com.booking.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private Logger log;
    private String testName;
    private String testMethodName;

    @Override
    public void onTestStart(ITestResult result) {
        this.testMethodName = result.getMethod().getMethodName();
        log.info(String.format("-===[Starting '%s' ...]===-", testMethodName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(String.format("-===[Test '%s' PASSED]===-", testMethodName));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(String.format("-===[Test '%s' FAILED]===-", testMethodName));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn(String.format("-===[Test '%s' SKIPPED]===-", testMethodName));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        this.testName = context.getCurrentXmlTest().getName();
        this.log = LogManager.getLogger(testName);
        log.info(String.format("-===[TEST '%s' STARTED]===-", testName));
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info(String.format("-===[ALL '%s' FINISHED]===-", testName));
    }
}
