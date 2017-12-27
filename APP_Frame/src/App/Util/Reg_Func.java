package App.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg_Func {
	// 正则过滤掉汉字，返回数字，用来做相应的断言
	public static String getNum(String Msg) {
		String str = Msg;

		String reg = "[\u4e00-\u9fa5]";

		Pattern pat = Pattern.compile(reg);

		Matcher mat = pat.matcher(str);

		String repickStr = mat.replaceAll("");

		System.out.println("去中文后:" + repickStr);
		
		return repickStr;
	}
}
