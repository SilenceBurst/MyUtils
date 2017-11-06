package com.sign.myutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查手机号密码
 */
public class LoginUtils {
    /**
     * 判断是否是手机号
     *
     * @param phone 　手机号
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 6-15位数字、英文或下划线任意两种
     *
     * @param psd 密码
     * @return
     */
    public static boolean checkPsd(String psd) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$");
        Matcher matcher = pattern.matcher(psd);
        return matcher.matches();
    }
}
