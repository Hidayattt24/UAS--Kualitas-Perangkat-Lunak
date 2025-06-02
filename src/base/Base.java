package base;

import java.io.File;
import java.net.URL;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {
    private static AndroidDriver<AndroidElement> driver;
    private static WebDriverWait wait;

    public static void main(String[] args) throws Exception {
        setupDriver();
        try {
            testOnboardingAndRegistration();
            testPrayerTimesFeature();
            testJournalFeature();
            testMonthlyCalendar();
            testSettingsAndLogout();
            
            System.out.println("All tests completed successfully!");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void setupDriver() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        File appDir = new File("src");
        File app = new File(appDir, "taubatku.apk");
        
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "hp");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723"), cap);
        wait = new WebDriverWait(driver, 30);
    }

    private static void testOnboardingAndRegistration() throws Exception {
        // 1. Splash Screen
        System.out.println("Waiting for Splash Screen...");
        Thread.sleep(5000);
        
        // 2. Welcome Screens
        System.out.println("Testing Welcome Screens...");
        for(int i = 1; i <= 3; i++) {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_next")));
            nextButton.click();
            Thread.sleep(1000);
        }
        
        // 3. Get Started & Signup
        System.out.println("Testing Get Started Screen...");
        WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_signup")));
        signupButton.click();
        
        // 4. Registration
        System.out.println("Testing Registration...");
        Random rand = new Random();
        String randomNum = String.valueOf(rand.nextInt(10000));
        String username = "testuser" + randomNum;
        String email = "testuser" + randomNum + "@test.com";
        String password = "password123";
        
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("emailInput")));
        emailInput.sendKeys(email);
        
        WebElement usernameInput = driver.findElementById("usernameInput");
        usernameInput.sendKeys(username);
        
        WebElement passwordInput = driver.findElementById("passwordInput");
        passwordInput.sendKeys(password);
        
        WebElement btnSignUp = driver.findElementById("btnSignUp");
        btnSignUp.click();
        
        // 5. Success Screen
        System.out.println("Verifying success screen and auto-navigation...");
        WebElement welcomeMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcomeMessage")));
        Assert.assertTrue(welcomeMessage.isDisplayed());
        Thread.sleep(3000); // Wait for auto-navigation
    }

    private static void testPrayerTimesFeature() throws Exception {
        System.out.println("Testing Prayer Times Feature...");
        
        // Verify Home Screen Elements
        WebElement welcomeText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcomeText")));
        Assert.assertTrue(welcomeText.getText().contains("Assalamu'alaikum"));
        
        // Verify Prayer Time Cards
        String[] prayerTimes = {"Fajr", "Sunrise", "Dhuhr", "Asr", "Maghrib", "Isha"};
        for (String prayer : prayerTimes) {
            String cardId = prayer.toLowerCase() + "Card";
            WebElement card = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cardId)));
            Assert.assertTrue(card.isDisplayed());
        }
        
        // Test Hadith Feature
        System.out.println("Testing Hadith Feature...");
        WebElement hadithCard = wait.until(ExpectedConditions.elementToBeClickable(By.id("hadithCard")));
        hadithCard.click();
        
        // Verify Hadith Detail Screen
        WebElement backButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("backButton")));
        Assert.assertTrue(backButton.isDisplayed());
        backButton.click();
    }

    private static void testJournalFeature() throws Exception {
        System.out.println("Testing Journal Feature...");
        
        // Navigate to Journal
        WebElement journalNav = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav_journal")));
        journalNav.click();
        
        // Create Journal
        WebElement fabAdd = wait.until(ExpectedConditions.elementToBeClickable(By.id("fab_add")));
        fabAdd.click();
        
        // Fill Journal Details
        WebElement titleInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("et_title")));
        titleInput.sendKeys("Test Journal Entry");
        
        WebElement contentInput = driver.findElementById("et_content");
        contentInput.sendKeys("This is a test journal content.");
        
        WebElement saveButton = driver.findElementById("btn_save");
        saveButton.click();
        
        // Verify Journal Created
        Thread.sleep(2000);
        WebElement journalTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("journal_title")));
        Assert.assertEquals(journalTitle.getText(), "Test Journal Entry");
        
        // Delete Journal
        WebElement deleteButton = driver.findElementById("btn_delete");
        deleteButton.click();
        
        // Confirm Deletion
        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        confirmDelete.click();
        Thread.sleep(1000);
    }

    private static void testMonthlyCalendar() throws Exception {
        System.out.println("Testing Monthly Calendar...");
        
        // Navigate to Prayer Times
        WebElement prayerNav = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav_prayer")));
        prayerNav.click();
        
        // Open Calendar
        WebElement calendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("monthlyCalendarButton")));
        calendarButton.click();
        
        // Verify Calendar Elements
        WebElement monthYearText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("monthYearText")));
        Assert.assertTrue(monthYearText.isDisplayed());
        
        // Check Calendar Grid
        WebElement calendarGrid = driver.findElementById("calendarRecyclerView");
        Assert.assertTrue(calendarGrid.isDisplayed());
        
        // Return to Main Screen
        WebElement backButton = driver.findElementById("backButton");
        backButton.click();
    }

    private static void testSettingsAndLogout() throws Exception {
        System.out.println("Testing Settings and Logout...");
        
        // Navigate to Settings
        WebElement settingsNav = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav_settings")));
        settingsNav.click();
        
        // Verify Settings Elements
        WebElement profileImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("profile_image")));
        Assert.assertTrue(profileImage.isDisplayed());
        
        // Logout Process
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_logout")));
        logoutButton.click();
        
        // Confirm Logout
        WebElement confirmLogout = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        confirmLogout.click();
        
        // Verify Return to Login Screen
        WebElement googleSignInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("googleSignInButton")));
        Assert.assertTrue(googleSignInButton.isDisplayed());
        
        System.out.println("Logout successful!");
    }
}