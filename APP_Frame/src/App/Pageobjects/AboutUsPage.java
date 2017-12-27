package App.Pageobjects;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

import App.Util.ObjectMap;


public class AboutUsPage {
	private WebElement element = null;
	// 指定页面元素定位表达式配置文件的绝对文件路径
	private ObjectMap objectMap = new ObjectMap("config/objectMap.properties");
	private AppiumDriver driver;

	public AboutUsPage(AppiumDriver driver) {
		this.driver = driver;
	}

	// 返回页面Title
	public WebElement Title() throws Exception {
		// 使用objectMap类中的getLocator方法获取配置文件中关于Title的定位方式和定位表达式
		element = driver.findElement(objectMap
				.getLocator("taoche.AboutUsPage.Title"));
		return element;
	}

	// 返回页面左上角的返回按钮
	public WebElement BackBtn() throws Exception {
		// 使用objectMap类中的getLocator方法获取配置文件中关于BackBtn的定位方式和定位表达式
		element = driver.findElement(objectMap
				.getLocator("taoche.AboutUsPage.BackBtn"));
		return element;
	}
}
