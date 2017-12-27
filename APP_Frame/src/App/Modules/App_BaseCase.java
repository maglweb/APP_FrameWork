package App.Modules;

import java.io.File;
import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import App.Util.App_common;
import App.Util.Log;
import App.Util.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class App_BaseCase {
	public AppiumDriver driver;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		File app = new File("APK/taoche.apk");
		// File app = new File("APK/taoche.apk");
		// 设置自动化相关参数
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 不需要再次安装
		capabilities.setCapability("noReset", true);
		// 不需要按照的话去掉这个，设置apk的路径
		capabilities.setCapability(MobileCapabilityType.APP,
				app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Q505T");
		// 设置系统版本
		capabilities
				.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				"Android");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				"com.taoche.yixin.app");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				"com.taoche.yixin.app.activity.home.AppstartActivity");// AppstartActivity
		capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY,
				"com.taoche.yixin.app.activity.home.AppstartActivity");
		// 支持中文输入
		capabilities.setCapability("unicodeKeyboard", "True");
		// 重置输入法为系统默认
		capabilities.setCapability("restKeyboard", "True");
		// 每次启动时覆盖session，否则第二次后运行会报错不能新建session
		capabilities.setCapability("sessionOverride", true);
		// 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名后无法正常使用
		capabilities.setCapability("noSign", "True");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		Thread.sleep(5000);
		System.out.println("启动APP");
		Log.info("启动APP");
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(5000);

		// 通过定位首页轮播图元素，判断是否为第一次安装
		/*
		 * WebElement IndexPage = driver .findElement(By .xpath(
		 * "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.ImageView"
		 * ));
		 */
		if (driver.getPageSource().contains("com.taoche.yixin.app:id/vp_guide")) {
			// 第一次安装需要执行滑动页面操作
			App_common app_common = new App_common();
			app_common.Splash_screen(driver);
			// 判断当页面中包含启动闪屏页id，等候6秒后再执行后续操作
		} else if (driver.getPageSource().contains(
				"com.taoche.yixin.app:id/ad_image")) {
			WaitUtil.sleep(6000);
		}
		// 进入首页后等待界面元素加载完成，再执行其他操作
		// 定位下部导航栏的"二手车"按钮

		WebElement tv_old_car = driver.findElement(By
				.id("com.taoche.yixin.app:id/tv_old_car"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(tv_old_car));
		WaitUtil.sleep(20000);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	// Test每个测试用例执行之前，执行log文件
	@BeforeTest
	public void BeforeTest() throws Exception {
		DOMConfigurator.configure("Log4j.xml");
	}
}
