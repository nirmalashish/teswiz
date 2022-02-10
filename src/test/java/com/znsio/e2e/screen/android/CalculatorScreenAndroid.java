package com.znsio.e2e.screen.android;

import com.znsio.e2e.screen.*;
import com.znsio.e2e.tools.*;
import io.appium.java_client.*;
import org.openqa.selenium.*;

import static com.znsio.e2e.tools.Wait.*;

public class CalculatorScreenAndroid extends CalculatorScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = CalculatorScreenAndroid.class.getSimpleName();

    public CalculatorScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public CalculatorScreen handlePopupIfPresent() {
        waitFor(1);
        visually.checkWindow(SCREEN_NAME, "Calculator launched");
        MobileElement upgradeAppNotificationElement = (MobileElement) driver.findElementById("android:id/button1");
        if (null != upgradeAppNotificationElement) {
            upgradeAppNotificationElement.click();
            waitFor(1);
        }
        MobileElement gotItElement = (MobileElement) driver.findElementById("com.android2.calculator3:id/cling_dismiss");
        if (null != gotItElement) {
            gotItElement.click();
            waitFor(1);
        }
        visually.checkWindow(SCREEN_NAME, "Calculator popup handled");
        return this;
    }

    @Override
    public CalculatorScreen selectNumber(String number) {
        driver.findElement(By.id("digit" + number)).click();
        visually.checkWindow(SCREEN_NAME, "Entered number " + number);
        return this;
    }

    @Override
    public CalculatorScreen pressOperation(String operation) {
        String mappedOperation;
        switch (operation.toLowerCase()) {
            case "plus":
                mappedOperation = "plus";
                break;
            case "subtract":
                mappedOperation = "minus";
                break;
            case "multiply":
                mappedOperation = "times";
                break;
            case "divide":
                mappedOperation = "divide";
                break;
            case "equals":
                mappedOperation = "equal";
                break;
            default:
                throw new RuntimeException("Operation " + operation + " is not supported");
        }
        driver.findElement(By.id(mappedOperation)).click();
        return this;
    }
}
