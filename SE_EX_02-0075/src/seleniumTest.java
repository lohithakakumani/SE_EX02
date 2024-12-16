import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;  // Import Duration

public class SeleniumTest {

    public static void main(String[] args) {
        // Set the path to ChromeDriver (make sure this path is correct)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kakum\\OneDrive\\Documents\\selenium_testing\\chromedriver-win64\\chromedriver.exe");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Open the web application (local file or server URL)
            driver.get("file:///C:/Users/kakum/OneDrive/Documents/selenium_testing/src/index.html");

            // Wait for the element with id 'a' to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for 10 seconds using Duration
            WebElement inputA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a")));
            WebElement inputB = driver.findElement(By.id("b"));
            WebElement inputC = driver.findElement(By.id("c"));

            // Enter values for a, b, and c
            inputA.sendKeys("1");
            inputB.sendKeys("-2");
            inputC.sendKeys("1");

            // Find and click the "Plot" button
            WebElement plotButton = driver.findElement(By.id("plot"));
            plotButton.click();

            // Handle the alert (plotting message)
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());  // Wait for alert to appear
            alert.accept();  // Accept the alert (click 'OK')

            // Wait for the graph to be visible
            WebElement graph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graph")));

            // Wait for a longer duration to keep the output visible (e.g., 10 seconds)
            Thread.sleep(1000000);  // Wait for 10 seconds before closing the browser

            // Validate the result (ensure the graph is displayed)
            if (graph.isDisplayed()) {
                System.out.println("Test Passed: Quadratic plot displayed successfully!");
            } else {
                System.out.println("Test Failed: Quadratic plot not displayed.");
            }

        } catch (TimeoutException e) {
            System.out.println("Element not found within the timeout period.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
