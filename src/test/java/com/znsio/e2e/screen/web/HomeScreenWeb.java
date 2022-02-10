package com.znsio.e2e.screen.web;

import com.znsio.e2e.screen.*;
import com.znsio.e2e.tools.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;

public class HomeScreenWeb extends HomeScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(HomeScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = HomeScreenWeb.class.getSimpleName();
    private final By loginFormLinkText = By.linkText("Form Authentication");

    public HomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    @Override
    public LoginScreen selectLogin() {
        driver.findElement(loginFormLinkText).click();
        return LoginScreen.get();
    }

    @Override
    public HomeScreen goBack() {
        LOGGER.info("Skipping this step for Web");
        return this;
    }

    @Override
    public EchoScreen selectEcho() {
        LOGGER.info("Skipping this step for Web");
        return EchoScreen.get();
    }
}
