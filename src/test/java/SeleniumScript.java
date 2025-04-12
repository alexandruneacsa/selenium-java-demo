import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SeleniumScript {

    public JavascriptExecutor js;

    @Test
    public void firstTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        //1. Open the browser
        WebDriver driver = new ChromeDriver();

        //2. Enter the URL http://practice.automationtesting.in/
        driver.get("http://practice.automationtesting.in/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        //3. Click on Shop Menu
        driver.findElement(By.id("menu-item-40")).click();
        Thread.sleep(1000);

        //4. Now click on Home menu button
        driver.findElement(By.xpath("//*[@id=\"content\"]/nav/a")).click();
        driver.get("http://practice.automationtesting.in/");

        //5. Test whether the Home page has Three Arrivals only
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        assertEquals(3, driver.findElements(By.cssSelector(".woocommerce")).size());
        Thread.sleep(1000);

        //6. Now click on one of the images in Arrivals
        driver.findElements(By.cssSelector(".woocommerce")).get(2).click();//deoarece primele doua sunt out of stock
        Thread.sleep(1000);

        //7. Click on the ADD TO BASKET button
        js.executeScript("window.scrollBy(0,600)");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[5]/ul/li[2]/a[2]")).click();

        //8. Test whether the VIEW BASKET button is visible and click on it
        boolean isVisible = driver.findElement(By.linkText("VIEW BASKET")).isDisplayed();
        if (isVisible){
            System.out.println("Is visible");
        }
        else{
            System.out.println("Is not visible");
        }

        //9. Now user can find total and subtotal values just above the Proceed to Checkout button, test if the total is greater than the subtotal
        driver.findElement(By.linkText("VIEW BASKET")).click();
        assertNotEquals("₹350.00", driver.findElements(By.linkText("₹367.50")));

        //10. Click the PROCEED TO CHECKOUT button
        driver.findElement(By.linkText("PROCEED TO CHECKOUT")).click();

        //11. Fill in all required fields, set Cash on Delivery as your payment method, and click PLACE ORDER.
        driver.findElement(By.id("billing_first_name")).sendKeys("Alexandru");
        driver.findElement(By.id("billing_last_name")).sendKeys("Neacsa");
        driver.findElement(By.id("billing_company")).sendKeys("Test");
        driver.findElement(By.id("billing_email")).sendKeys("alex@gmail.com");
        driver.findElement(By.id("billing_phone")).sendKeys("0700000000");
        driver.findElement(By.id("billing_address_1")).sendKeys("Street");
        driver.findElement(By.id("billing_address_2")).sendKeys("1");
        driver.findElement(By.id("billing_city")).sendKeys("Pitesti");
        driver.findElement(By.id("billing_state")).sendKeys("Arges");
        driver.findElement(By.id("billing_postcode")).sendKeys("110284");
        driver.findElement(By.id("payment_method_cod")).click();
        driver.findElement(By.id("place_order")).click();

        //12. Check if the Payment method is set to Cash on Delivery in both fields
        assertEquals("Cash on Delivery", driver.findElement(By.xpath("//*[@id=\"page-35\"]/div/div[1]/ul/li[4]/strong")).getText());
        assertEquals("Cash on Delivery", driver.findElement(By.xpath("//*[@id=\"page-35\"]/div/div[1]/table/tfoot/tr[3]/td")).getText());
    }
}
