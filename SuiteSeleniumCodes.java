package org.jesus.suites;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SuiteSeleniumCodes {
  WebDriver driver;
  @BeforeClass
  public void setup(){
    System.setProperty("webdriver.chrome.driver","C:\\automation\\chromedriver.exe");
    driver=new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    driver.manage().window().maximize(); //MAXIMIZE
    //implicit WAIT
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  //ACTIONS, AJAX, dropdownlist dynamic
    //action.dragAndDrop(source,target).build().perform();

    @Test(enabled = true)
    public void alerts_Popups_mouseover() throws InterruptedException {
        WebElement mouseover=driver.findElement(By.xpath("//button[text()='Mouse Hover']"));
        Actions action=new Actions(driver);
        action.moveToElement(mouseover).perform();
        WebElement target=driver.findElement(By.xpath("//a[text()='Reload']"));
        action.moveToElement(target).click().build().perform();
        driver.findElement(By.id("alertbtn")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.id("name")).sendKeys("Jesus");
        driver.findElement(By.id("alertbtn")).click();
        String textAlert=driver.switchTo().alert().getText();
        System.out.println("AlertText="+textAlert);
        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().accept();

    }

    @Test(enabled = false)
    public void tabHandles() throws InterruptedException {
        driver.findElement(By.id("opentab")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            driver.switchTo().window(window);
            String title = driver.getTitle();
            System.out.println("Window1 " + title);
            driver.close();
        }
    }

    @Test(enabled = false)
    public void windowHandles() throws InterruptedException {
        driver.findElement(By.id("openwindow")).click();
        Set<String> windowHandles= driver.getWindowHandles();
        for (String window:windowHandles) {
            driver.switchTo().window(window);
            String title=driver.getTitle();
            System.out.println("Window1 "+title);
            driver.close();
        }



    }
    @Test(enabled = false)
    public void Frames() throws InterruptedException {

      //FAIL dur frame driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
      driver.switchTo().frame("courses-iframe");
      WebElement element=driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
      element.click();
      Thread.sleep(2000);
      driver.navigate().back();
      driver.switchTo().parentFrame();



    }

  @Test(enabled = false)
  public void TablesHandling(){
      // Table Iterate all rows and cells
      WebElement table=driver.findElement(By.name("courses"));
      //Iterate rows
      List<WebElement> rows=table.findElements(By.tagName("tr"));
      int i=0;
      for (WebElement row:rows){
          i++;
          System.out.println("row"+i);
          List<WebElement>cells=row.findElements(By.tagName("td"));
          for (WebElement cell:cells) {
              System.out.println(cell.getText());
          }
      }

      //Obtain a sum of all prices method 1
      WebElement tableDescipted= driver.findElement(By.xpath("//legend[contains(text(),'Web Table Example')]/following-sibling::table"));
      List<WebElement> rowstable=tableDescipted.findElements(By.tagName("tr"));
      i=0;
      int sumVariable=0;
      for (WebElement row:rowstable) {
          if (i==0){
              i=1;
              continue;}
          String cellValue=row.findElement(By.xpath("td[3]")).getText();
          System.out.println(cellValue);
          sumVariable+=Integer.valueOf(cellValue);
      }
      System.out.println("The value of all sumcourses is="+sumVariable);

      //Obtain a sum of all prices method 2
      sumVariable=0;
      List<WebElement> prices= driver.findElements(By.xpath("//legend[contains(text(),'Web Table Example')]/following-sibling::table//td[3]"));
      for (WebElement price:prices) {
          sumVariable+=Integer.valueOf(price.getText());
      }
      System.out.println("The value of all sumcourses Method 2 is="+sumVariable);

      //Obtain a sum of all prices method 3
      sumVariable=0;
      List<WebElement> listCity=driver.findElements(By.xpath("//legend[contains(text(),'Web Table Fixed header')]/following-sibling::div/table/tbody/tr/td[3]"));
      i=1;
      for (WebElement city:listCity) {
          if(city.getText().equals("Chennai")){
              WebElement price=driver.findElement(By.xpath("//legend[contains(text(),'Web Table Fixed header')]/following-sibling::div/table/tbody/tr["+i+"]/td[4]"));
              sumVariable+=Integer.valueOf(price.getText());
          }
          i++;
      }
      System.out.println("The value of all sumcourses Method 3 is="+sumVariable);


    }

    @Test(enabled = false)
  public void checkbox_droplist_Radio_Checkbox(){
    driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    driver.manage().window().maximize(); //MAXIMIZE
    //implicit WAIT
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    WebElement dropdownList=driver.findElement(By.id("dropdown-class-example"));
    Select options= new Select(dropdownList);

    options.selectByValue("option2");
    options.selectByIndex(2);
    options.selectByVisibleText("Option3");
    List<WebElement> listOptions= options.getOptions();
    for (WebElement element:listOptions) {
        element.click();
    }

    //RadioButton list
    List<WebElement> radios=driver.findElements(By.className("radioButton"));
    int size=radios.size();
    System.out.println("size radios="+size);
    for (WebElement radio:radios) {
        radio.click();
        String radioName=radio.getAttribute("value");
      System.out.println(radioName);
    }
    //Radiobutton direct
    driver.findElement(By.xpath("//input[@value='radio2']")).click();

  }

  @Test (enabled = false)
  public void TestCase_forLists() throws InterruptedException {
    System.out.println("test");
    driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    driver.manage().window().maximize(); //MAXIMIZE
    //implicit WAIT
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    String title=driver.getTitle();
    System.out.println(title);
    //List
    List<WebElement> Lista= driver.findElements(By.xpath("//button[contains(text(),'ADD')]"));
    //foreach element
    for (WebElement Element:Lista)
         {
           Element.click();
           //Explicit Wait
           WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
           wait.until(ExpectedConditions.elementToBeClickable(Element));
           //wait.until(ExpectedConditions.elementToBeClickable(ElementBy.id("someid")));
           Thread.sleep(1000);
         }

    }

  @AfterTest
  public void CloseDriver(){
    driver.close();
    driver.quit();
  }

}