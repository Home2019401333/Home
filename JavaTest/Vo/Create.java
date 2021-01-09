package Vo;

import java.sql.ResultSet;
/**
 * 产生随机验证码
 * @author ASUS
 *
 */
public class Create {
	public static String CreateNumber() {
		String str=String.valueOf(DateAdd.getNum(1000,9999));
		return str;
	}
	


}
