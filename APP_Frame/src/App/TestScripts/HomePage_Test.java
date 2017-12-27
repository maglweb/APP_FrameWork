package App.TestScripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import App.Modules.App_BaseCase;
import App.Util.App_common;
import App.Util.Constant;


public class HomePage_Test extends App_BaseCase {
	public static Logger logger = Logger.getLogger(HomePage2_Test.class);
	App_common app_common = new App_common();
	// ListPage listPage = new ListPage(driver);
	Constant constant = new Constant();

	// 首页冲屏广告测试
	@Test(priority = 1)
	public void PopUpPic_Test() {
		HomePage homePage = new HomePage(driver);
		try {
			if (homePage.CloseAd().isDisplayed()) {
				Assert.assertTrue(homePage.CloseAd().isEnabled());
				homePage.CloseAd().click();
				Log.info("存在首页冲屏广告");
				Reporter.log("存在首页冲屏广告");
			}
		} catch (Exception e) {
			Log.error("失败：" + e);
			Reporter.log("失败：" + e);
		}
	}

	// 首页轮播图测试
	@Test(priority = 2)
	public void FlushPic_Test() throws Exception {
		HomePage homePage = new HomePage(driver);
		try {
			Assert.assertTrue(homePage.Flush_Pic().isEnabled());
			Log.info("首页轮播图验证成功");
			Reporter.log("首页轮播图验证成功");
		} catch (AssertionError e) {
			Log.error("首页轮播图验证失败" + e);
			Reporter.log("首页轮播图验证失败" + e);
		}
	}

	// 首页地理位置测试
	@Test(priority = 3)
	public void Location_Test() throws Exception {
		HomePage homePage = new HomePage(driver);
		SelectCityPage selectCityPage = new SelectCityPage(driver);
		try {
			// 验证当前位置信息是北京
			Assert.assertTrue(homePage.Location().getText().equals("北京"));
			// 进入选择位置界面
			homePage.Location().click();
			// 断言选择城市界面信息
			Assert.assertTrue(selectCityPage.Title().getText().equals("城市"));
			Assert.assertTrue(selectCityPage.BackBtn().isEnabled());
			// 断言当前选中的城市是"北京"
			Assert.assertTrue(selectCityPage.Selected_City().getText()
					.equals("北京"));
			Log.info("选择位置信息验证成功");
			Reporter.log("选择位置信息验证成功");
		} catch (AssertionError e) {
			Log.error("选择位置信息验证失败");
			Reporter.log("选择位置信息验证失败");
		}
		// 选择当前定位的城市
		selectCityPage.Selected_City().click();
	}

	// 点击首页输入框，对跳转的页面进行验证
	public void SearchInput_Test() throws Exception {
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		homePage.SearchInput().click(); // 点击搜索输入框
		WaitUtil.sleep(3000);
		try {
			// 跳转到搜索页面，验证界面元素
			Assert.assertTrue(searchPage.Input().getText().equals("请输入品牌或车型"));
			Assert.assertTrue(searchPage.CancelBtn().isEnabled());
			Assert.assertTrue(searchPage.HotSearchGridView().isDisplayed());
			// 输入框中输入"宝马"
			searchPage.Input().sendKeys("宝马");
			WaitUtil.sleep(2000);
			searchPage.BMW_X1().click(); // 进入搜索界面的宝马x1界面
			WaitUtil.sleep(3000);
			SelectProductPage selectProductPage = new SelectProductPage(driver);
			selectProductPage.BackBtn().click();
			searchPage.CancelBtn().click(); // 点击搜索界面的取消按钮，回到首页
			Log.info("搜索页面验证成功");
			Reporter.log("搜索页面验证成功");
		} catch (AssertionError e) {
			Reporter.log("搜索页面验证失败");
			Log.error("搜索页面验证失败");
		}
	}

	// 验证糖豆
	@Test(priority = 5)
	public void SugarBanner_Test() throws Exception {
		HomePage homePage = new HomePage(driver);
		try {
			Assert.assertTrue(homePage.SugarBanner().isDisplayed());
			Log.info("首页显示糖豆区域");
			Reporter.log("首页显示糖豆区域");
		} catch (AssertionError e) {
			Log.error("首页没显示糖豆区域");
			Reporter.log("首页显示糖豆区域");
		}
	}

	// 验证首页新增糖豆跳转逻辑-帮您贷款
	@Test(priority = 6)
	public void Sugar_HelpCredit() throws Exception {
		HomePage homePage = new HomePage(driver);
		SugarPage sugarPage = new SugarPage(driver);
		try {
			if (homePage.Sugar_HelpCredit().isDisplayed()) {
				homePage.Sugar_HelpCredit().click();
				WaitUtil.sleep(3000);
				Assert.assertTrue(sugarPage.CouponPage_Title().isDisplayed());
				Assert.assertTrue(sugarPage.CouponPage_Back().isDisplayed());
				sugarPage.CouponPage_Back().click();
				Log.info("首页帮您贷款糖豆验证成功");
				Reporter.log("首页帮您贷款糖豆验证成功");
			}
		} catch (AssertionError e) {
			Log.error("首页帮您贷款糖豆验证失败:" + e);
			Reporter.log("首页帮您贷款糖豆验证失败:" + e);
		}
	}

	// 验证首页新增糖豆跳转逻辑-二手车估价
	@Test(priority = 7)
	public void Sugar_Evaluate() throws Exception {
		HomePage homePage = new HomePage(driver);
		EvaluatePage evaluatePage = new EvaluatePage(driver);
		try {
			if (homePage.Sugar_Evaluate().isDisplayed()) {
				homePage.Sugar_Evaluate().click();
				WaitUtil.sleep(3000);
				Assert.assertTrue(evaluatePage.Imv_buy().isDisplayed());
				Assert.assertTrue(evaluatePage.Submit().isEnabled());
				evaluatePage.BackBtn().click();
				Log.info("首页二手车估价糖豆验证成功");
				Reporter.log("首页二手车估价糖豆验证成功");
			}
		} catch (AssertionError e) {
			Log.error("首页二手车估价糖豆验证失败：" + e);
			Reporter.log("首页二手车估价糖豆验证失败：" + e);
		}
	}

	// 验证首页新增糖豆跳转逻辑-卖车
	@Test(priority = 8)
	public void Sugar_SaleCar() throws Exception {
		HomePage homePage = new HomePage(driver);
		SaleCarPage saleCarPage = new SaleCarPage(driver);
		try {
			if (homePage.Sugar_SaleCar().isDisplayed()) {
				homePage.Sugar_SaleCar().click();
				WaitUtil.waitWebelement_ById(driver,
						"com.taoche.yixin.app:id/base_tv_center");
				Assert.assertTrue(saleCarPage.Title().isDisplayed());
				Assert.assertTrue(saleCarPage.BackBtn().isEnabled());
				saleCarPage.BackBtn().click();
				Log.info("首页高价卖车糖豆验证成功");
				Reporter.log("首页高价卖车糖豆验证成功");
			}
		} catch (AssertionError e) {
			Log.error("首页高价卖车糖豆验证失败：" + e);
			Reporter.log("首页高价卖车糖豆验证失败：" + e);
		}
	}

	// 验证首页新增糖豆跳转逻辑-领券中心
	@Test(priority = 9)
	public void Sugar_Coupon() throws Exception {
		HomePage homePage = new HomePage(driver);
		CouponPage couponPage = new CouponPage(driver);
		try {
			if (homePage.Sugar_Coupon().isDisplayed()) {
				homePage.Sugar_Coupon().click();
				WaitUtil.sleep(3000);
				Assert.assertTrue(couponPage.Title().isDisplayed());
				Assert.assertTrue(couponPage.BackBtn().isEnabled());
				couponPage.BackBtn().click();
				Log.info("首页领券中心糖豆验证成功");
				Reporter.log("首页领券中心糖豆验证成功");
			}
		} catch (AssertionError e) {
			Log.error("首页领券中心糖豆验证失败：" + e);
			Reporter.log("首页领券中心糖豆验证失败： " + e);
		}
	}

	// 默认打开首页定位在"分期"，切换"二手车"Tab下
	@Test(priority = 10)
	public void Switch_oldCar() throws Exception {
		HomePage homePage = new HomePage(driver);
		try {
			// 验证分期Tab下，查看全部链接按钮显示
			Assert.assertTrue(homePage.Checkall().isDisplayed());
			// 切换到二手车Tab下
			homePage.Tab_oldcar().click();
			Log.info("首页默认定位在分期验证成功");
			Reporter.log("首页默认定位在分期验证成功");
		} catch (AssertionError e) {
			Log.error("首页默认定位在分期验证失败");
			Reporter.log("首页默认定位在分期验证失败");
		}
	}

	// 测试首页点击每个品牌跳转页面
	@Test(priority = 11)
	public void Index_PinPai() throws Exception {
		Log.startTestCase("启动APP");
		Log.startTestCase("进入APP首页");
		// 遍历首页品牌信息，分别点击每个品牌验证跳转界面是否正确
		for (int i = 0; i < constant.Pinpai.length; i++) {
			ListPage listPage = new ListPage(driver);
			driver.findElement(
					By.xpath("//android.widget.TextView[@text=" + "'"
							+ constant.Pinpai[i] + "'" + "]")).click();
			// System.out.println("当前定位的元素是:" + constant.Pinpai[i]);
			WaitUtil.sleep(3000);
			try {
				System.out.println("当前定位的元素是:" + constant.Pinpai[i]);
				// 当点击"更多"时，跳转到的是品牌筛选页面
				if (constant.Pinpai[i] == "更多") {
					// listPage.HotBrand();
					// 如果点击的是"更多"则进入品牌筛选
					Assert.assertTrue(listPage.HotBrand().isDisplayed());
					listPage.Close_Brand().click();
					WaitUtil.sleep(2000);
					listPage.BackBtn().click();
				} else {
					WebElement checkedItem = driver.findElement(By
							.xpath("//android.widget.TextView[contains(@text, "
									+ "'" + constant.Pinpai[i] + "'" + ")]"));
					WaitUtil.sleep(3000);
					Assert.assertTrue(listPage.Page_list().size() > 0);
					Assert.assertTrue(checkedItem.isDisplayed());
					listPage.BackBtn().click();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// listPage.BackBtn().click();
		}
	}

	// 测试首页点击快速搜索时，跳转界面
	@Test(priority = 12)
	public void QuickSearch() throws Exception {
		// app_common.swipeToUp(driver, 2000);
		for (int i = 0; i < constant.QuickSearch.length; i++) {
			driver.findElement(
					By.xpath("//android.widget.TextView[@text=" + "'"
							+ constant.QuickSearch[i] + "'" + "]")).click();
			System.out.println("当前定位的元素是:" + constant.QuickSearch[i]);
			WaitUtil.sleep(3000);
			try {
				WebElement checkedItem = driver.findElement(By
						.xpath("//android.widget.TextView[contains(@text, "
								+ "'" + constant.QuickSearch[i] + "'" + ")]"));
				WaitUtil.sleep(3000);
				System.out.println("当前定位的元素是:" + constant.QuickSearch[i]);
				Assert.assertTrue(checkedItem.isDisplayed());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ListPage listPage = new ListPage(driver);
			listPage.BackBtn().click();
		}
	}

	// 首页验证浮球并点击
	@Test(priority = 13)
	public void FloatBall_Test() throws Exception {
		HomePage homePage = new HomePage(driver);
		FloatBallPage floatBallPage = new FloatBallPage(driver);
		try {
			// 验证首页"浮球"按钮显示
			Assert.assertTrue(homePage.FloatBall().isDisplayed());
			// 进入浮球游戏界面
			homePage.FloatBall().click();
			Assert.assertTrue(floatBallPage.Title().isDisplayed());
			floatBallPage.BackBtn().click();
		} catch (AssertionError e) {
			Log.error("首页浮球验证失败：" + e);
			Reporter.log("首页浮球验证失败：" + e);
		}
	}
}
