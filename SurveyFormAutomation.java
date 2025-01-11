
package com.surveyautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SurveyFormAutomation {
    private WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anant Sharma\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/forms");
        System.out.println("Browser launched and navigated to survey form page.");
    }

    public void fillForm() {
        try {
            // Fill Full Name
            WebElement fullNameField = driver.findElement(By.id("firstName"));
            fullNameField.sendKeys("Anant Sharma");

            // Fill Email
            WebElement emailField = driver.findElement(By.id("userEmail"));
            emailField.sendKeys("anantji1234@gmail.com");

            // Select Gender
            WebElement genderRadio = driver.findElement(By.xpath("//label[text()='Male']"));
            genderRadio.click();

            // Fill Mobile Number
            WebElement mobileField = driver.findElement(By.id("userNumber"));
            mobileField.sendKeys("9624346454");

            // Select Date of Birth
            WebElement dobField = driver.findElement(By.id("dateOfBirth-wrapper"));
            dobField.click();
            WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
            monthDropdown.sendKeys("May");
            WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
            yearDropdown.sendKeys("2001");
            WebElement day = driver.findElement(By.xpath("//div[text()='15']"));
            day.click();

            // Click Submit Button
            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();

            System.out.println("Form filled and submitted successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while filling the form: " + e.getMessage());
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
    public void validateRequiredFields() {
        try {
            // Clear any pre-filled fields (if necessary)
            WebElement fullNameField = driver.findElement(By.id("firstName"));
            fullNameField.clear();

            WebElement emailField = driver.findElement(By.id("userEmail"));
            emailField.clear();

            WebElement mobileField = driver.findElement(By.id("userNumber"));
            mobileField.clear();

            // Attempt to submit the form without filling required fields
            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();

            // Validate error messages for required fields
            WebElement fullNameError = driver.findElement(By.xpath("//input[@id='userName']/following-sibling::span"));
            WebElement emailError = driver.findElement(By.xpath("//input[@id='userEmail']/following-sibling::span"));
            WebElement mobileError = driver.findElement(By.xpath("//input[@id='userNumber']/following-sibling::span"));

            if (fullNameError.isDisplayed()) {
                System.out.println("Validation error displayed for Full Name: " + fullNameError.getText());
            }

            if (emailError.isDisplayed()) {
                System.out.println("Validation error displayed for Email: " + emailError.getText());
            }

            if (mobileError.isDisplayed()) {
                System.out.println("Validation error displayed for Mobile Number: " + mobileError.getText());
            }

        } catch (Exception e) {
            System.out.println("Error during validation testing: " + e.getMessage());
        }
    }

    public void verifyNavigationBetweenSteps() {
        try {
            // Navigate to Step 1 (already done in the setup)
            System.out.println("On Step 1: Personal Information");

            // Click 'Next' button to go to Step 2 (adjust the selector as needed)
            WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
            nextButton.click();

            // Wait for Step 2 to load (you can use WebDriverWait for better stability)
            Thread.sleep(2000); // Add implicit wait or WebDriverWait for production code

            // Verify that Step 2 is displayed
            WebElement step2Header = driver.findElement(By.xpath("//h2[text()='Contact Information']"));
            if (step2Header.isDisplayed()) {
                System.out.println("Navigated to Step 2: " + step2Header.getText());
            }

            // Click 'Previous' button to go back to Step 1
            WebElement prevButton = driver.findElement(By.xpath("//button[text()='Previous']"));
            prevButton.click();

            // Wait for Step 1 to load
            Thread.sleep(2000); 

            // Verify that Step 1 is displayed again
            WebElement step1Header = driver.findElement(By.xpath("//h2[text()='Personal Information']"));
            if (step1Header.isDisplayed()) {
                System.out.println("Navigated back to Step 1: " + step1Header.getText());
            }
        } catch (Exception e) {
            System.out.println("Error during navigation testing: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SurveyFormAutomation automation = new SurveyFormAutomation();
        automation.setUp();
        automation.fillForm(); // Fill out the form first
        automation.verifyNavigationBetweenSteps();  // Test navigation between steps
        automation.validateRequiredFields(); // Test validations
        automation.tearDown();
    }
}
