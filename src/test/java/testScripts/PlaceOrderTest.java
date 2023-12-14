package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductListPage;

public class PlaceOrderTest {
	
	
	WebDriver driver;
	LoginPage loginPage;
	ProductListPage listPage;
	CartPage cartPage;
    CheckOutPage checkOutPage;
	
	public PlaceOrderTest()
	{
		TestBase.initDriver();
		driver=TestBase.getDriver();
		loginPage= new LoginPage(driver);
		listPage=new ProductListPage(driver);
		cartPage=new CartPage(driver);
		checkOutPage=new CheckOutPage(driver);
	}
	
	@BeforeTest
	public void setUp()
	{
		TestBase.openUrl("https://www.saucedemo.com/");
		loginPage.loginIntoApp("standard_user", "secret_sauce");
	}
	
	@Test(priority = 1)
	public void validLogin()
	{
		Assert.assertTrue(listPage.isOnProducts());
	}
	
  @Test(priority = 2)
  public void addItem() {
	  
	  listPage.addToCart();
	  listPage.viewCart();
	  Assert.assertTrue(cartPage.isItemAdded());
	  //cartPage.continueShopping(); 
	  //cartPage.removeItems();
  }
  
  @Test(priority = 3)
  public void checkOutTest()
  {
	  cartPage.checkoutItems();
	  Assert.assertTrue(checkOutPage.infoPage());
  }
  
   @Test(priority = 4)
   public void provideInfo()
   {
	   checkOutPage.provideDetails();
   }
}
