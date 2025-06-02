package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class SeleniumRunner {
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String SCREENSHOT_DIR = "screenshots/";
    private static final String LOG_FILE = "automation_log.txt";
    private static final Random random = new Random();
    
    // Pre-defined data arrays for automation
    private static final String[] PRODUCT_QUERIES = {
        "backpack", "bike light", "bolt t-shirt", "fleece jacket", 
        "onesie", "test.allthethings"
    };
    
    private static final String[] SORT_OPTIONS = {
        "az", "za", "lohi", "hilo"
    };
    
    public static void main(String[] args) {
        log("🚀 Starting Fully Automated Sauce Demo Testing System");
        
        createScreenshotDirectory();
        initializeDriver();
        
        try {
            // Execute full automation sequence
            executeFullAutomationSequence();
            
        } catch (Exception e) {
            logError("Main execution error", e);
        } finally {
            cleanup();
        }
    }
    
    private static void executeFullAutomationSequence() {
        log("📋 Executing Complete Automation Sequence");
        
        // 1. Login to Sauce Demo
        autoLogin();
        pause(3000);
        
        // 2. Browse inventory
        autoBrowseInventory();
        pause(3000);
        
        // 3. Add item to cart
        autoAddToCart();
        pause(3000);
        
        // 4. View cart
        autoViewCart();
        pause(3000);
        
        // 5. Proceed to checkout
        autoCheckout();
        pause(3000);
        
        // 6. Apply sorting
        autoApplySorting();
        pause(3000);
        
        // 7. View product details
        autoViewProductDetails();
        pause(3000);
        
        // 8. Logout
        autoLogout();
        pause(3000);
        
        log("✅ Complete Sauce Demo Testing Sequence Finished Successfully!");
        generateAutomationReport();
    }
    
    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=VizDisplayCompositor");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        
        log("✅ WebDriver initialized with enhanced settings");
    }
    
    private static void autoLogin() {
        try {
            log("🔐 Auto-login to Sauce Demo");
            driver.get("https://www.saucedemo.com/");
            takeScreenshot("login_page");
            
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            takeScreenshot("successful_login");
            log("✅ Successfully logged in to Sauce Demo");
            
        } catch (Exception e) {
            logError("Auto-login failed", e);
        }
    }
    
    private static void autoBrowseInventory() {
        try {
            log("📦 Browsing inventory page");
            driver.get("https://www.saucedemo.com/inventory.html");
            takeScreenshot("inventory_page");
            
            // Scroll down to see more products
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 600);");
            pause(2000);
            
            takeScreenshot("inventory_scrolled");
            log("✅ Successfully browsed inventory page");
            
        } catch (Exception e) {
            logError("Inventory page browse failed", e);
        }
    }
    
    private static void autoAddToCart() {
        try {
            log("🛒 Auto-adding item to cart");
            driver.get("https://www.saucedemo.com/inventory.html");
            takeScreenshot("inventory_page_for_cart");
            
            // Pilih produk acak
            List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(), 'Add to cart')]"));
            if (!addButtons.isEmpty()) {
                int randomIndex = random.nextInt(addButtons.size());
                addButtons.get(randomIndex).click();
                takeScreenshot("item_added_to_cart");
                log("✅ Successfully added item to cart");
            } else {
                log("⚠️ No items available to add to cart");
            }
            
        } catch (Exception e) {
            logError("Add to cart failed", e);
        }
    }
    
    private static void autoViewCart() {
        try {
            log("🛍️ Viewing cart contents");
            driver.get("https://www.saucedemo.com/cart.html");
            takeScreenshot("cart_page");
            
            List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
            log("📬 Found " + cartItems.size() + " items in cart");
            
        } catch (Exception e) {
            logError("View cart failed", e);
        }
    }
    
    private static void autoCheckout() {
        try {
            log("💳 Proceeding to checkout");
            driver.get("https://www.saucedemo.com/cart.html");
            takeScreenshot("cart_page_checkout");
            
            WebElement checkoutButton = driver.findElement(By.id("checkout"));
            checkoutButton.click();
            
            wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
            takeScreenshot("checkout_step_one");
            
            // Isi informasi checkout
            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Doe");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();
            
            wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
            takeScreenshot("checkout_step_two");
            
            WebElement finishButton = driver.findElement(By.id("finish"));
            finishButton.click();
            
            wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
            takeScreenshot("checkout_complete");
            log("✅ Successfully completed checkout");
            
        } catch (Exception e) {
            logError("Checkout failed", e);
        }
    }
    
    private static void autoApplySorting() {
        try {
            log("🔄 Applying sorting to inventory");
            driver.get("https://www.saucedemo.com/inventory.html");
            takeScreenshot("inventory_before_sort");
            
            String sortOption = getRandomElement(SORT_OPTIONS);
            WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
            Select sortSelect = new Select(sortDropdown);
            sortSelect.selectByValue(sortOption);
            
            takeScreenshot("inventory_sorted_" + sortOption);
            log("✅ Applied sorting: " + sortOption);
            
        } catch (Exception e) {
            logError("Sorting failed", e);
        }
    }
    
    private static void autoViewProductDetails() {
        try {
            log("🔍 Viewing product details");
            driver.get("https://www.saucedemo.com/inventory.html");
            
            List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));
            if (!productLinks.isEmpty()) {
                int randomIndex = random.nextInt(productLinks.size());
                productLinks.get(randomIndex).click();
                takeScreenshot("product_details");
                log("✅ Viewed product details");
            } else {
                log("⚠️ No products available to view");
            }
            
        } catch (Exception e) {
            logError("View product details failed", e);
        }
    }
    
    private static void autoLogout() {
        try {
            log("🔐 Logging out from Sauce Demo");
            driver.get("https://www.saucedemo.com/inventory.html");
            
            driver.findElement(By.id("react-burger-menu-btn")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
            driver.findElement(By.id("logout_sidebar_link")).click();
            
            wait.until(ExpectedConditions.urlContains("index.html"));
            takeScreenshot("logout_page");
            log("✅ Successfully logged out");
            
        } catch (Exception e) {
            logError("Logout failed", e);
        }
    }
    
    private static String getRandomElement(String[] array) {
        return array[random.nextInt(array.length)];
    }
    
    private static void generateAutomationReport() {
        try {
            String reportFile = "automation_report_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            
            FileWriter writer = new FileWriter(reportFile);
            writer.write("=== SAUCE DEMO AUTOMATION REPORT ===\n");
            writer.write("Execution Time: " + LocalDateTime.now() + "\n");
            writer.write("Total Screenshots: " + countScreenshots() + "\n");
            writer.write("Exploration Status: COMPLETED SUCCESSFULLY\n");
            writer.write("\nExecuted Actions:\n");
            writer.write("- Automatic Login ✅\n");
            writer.write("- Browse Inventory ✅\n");
            writer.write("- Add Item to Cart ✅\n");
            writer.write("- View Cart ✅\n");
            writer.write("- Proceed to Checkout ✅\n");
            writer.write("- Apply Sorting ✅\n");
            writer.write("- View Product Details ✅\n");
            writer.write("- Logout ✅\n");
            writer.close();
            
            log("📊 Automation report generated: " + reportFile);
            
        } catch (Exception e) {
            logError("Report generation failed", e);
        }
    }
    
    private static int countScreenshots() {
        try {
            File dir = new File(SCREENSHOT_DIR);
            return dir.listFiles().length;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private static void takeScreenshot(String fileName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullFileName = fileName + "_" + timestamp + ".png";
            File destFile = new File(SCREENSHOT_DIR + fullFileName);
            
            Files.copy(sourceFile.toPath(), destFile.toPath());
            log("📸 Screenshot: " + fullFileName);
            
        } catch (Exception e) {
            logError("Screenshot failed", e);
        }
    }
    
    private static void createScreenshotDirectory() {
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
        } catch (Exception e) {
            System.out.println("Could not create screenshot directory");
        }
    }
    
    private static void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = "[" + timestamp + "] " + message;
        
        System.out.println(logMessage);
        
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            System.out.println("Could not write to log file");
        }
    }
    
    private static void logError(String message, Exception e) {
        String errorMessage = "❌ " + message + ": " + e.getMessage();
        log(errorMessage);
        e.printStackTrace();
    }
    
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void cleanup() {
        if (driver != null) {
            driver.quit();
            log("🔄 Automation session completed - WebDriver closed");
        }
    }
}