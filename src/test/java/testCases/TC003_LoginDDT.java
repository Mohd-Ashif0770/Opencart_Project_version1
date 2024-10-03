package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

/*Data is valid - login success - test pass - logout
- login failed - test fail

Data is invalid - login success - test fail - logout
-login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different package
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("**** TC003_LoginDDT Started*****");
		try {
		//HomePage
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//Login
		LoginPage lp=new LoginPage(driver);
     	lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid")) {
			
			if(targetPage==true) {
				
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else {
				
				Assert.assertTrue(false);
			}
		}
		
       if(exp.equalsIgnoreCase("Invalid")) {
			
			if(targetPage==true) {
				
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else {
				
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			
			Assert.fail();
		}
		
       logger.info("****finished TC003_LoginDDT *****");
	}
}
