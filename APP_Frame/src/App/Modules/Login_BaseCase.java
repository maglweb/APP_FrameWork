package App.Modules;


import io.appium.java_client.AppiumDriver;
import App.Util.Log;
import App.Util.WaitUtil;
import Yixin.Taoche.Pageobjects.HomePage;
import Yixin.Taoche.Pageobjects.LoginPage;
import Yixin.Taoche.Pageobjects.MinePage;


public class Login_BaseCase {
	public static void execute(AppiumDriver driver, String userName,
			String passWord) throws Exception {
		HomePage homePage = new HomePage(driver);
		MinePage minePage = new MinePage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// 定位首页下方导航栏"我的按钮"，点击进入个人中心页面
		homePage.Mine().click();
		// 验证个人中心界面是否已登录状态
		if (driver.getPageSource().contains("请登录")) {
			minePage.Please_login().click();
			// 通过账号密码登录
			loginPage.Zhanghao_login().click();
			WaitUtil.sleep(1000);
			loginPage.password_tel().sendKeys(userName);
			loginPage.new_password().sendKeys(passWord);
			loginPage.loginbtn().click();
			WaitUtil.sleep(1000);
			//Assert.assertTrue(driver.getPageSource().contains("易鑫用户"));
		} else {
			Log.info("用户已登录");
			System.out.println("用户已登录");
			//Assert.assertTrue(driver.getPageSource().contains("易鑫用户"));
		}
	}
}
