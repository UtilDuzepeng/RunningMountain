package com.miaofen.xiaoying.utils;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class StringUtil {

    public static boolean isNull(String str) {
        if (str == null || str.trim().length() == 0) return true;
        else return false;
    }

    public static boolean isNumeric(String str) {
        if (!StringUtil.isNull(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static int parseInt(String str) {
        if (isNull(str)) return 0;
        else {
            str = str.trim();
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static float parseFloat(String str) {
        if (isNull(str)) return 0;
        else {
            str = str.trim();
            try {
                return Float.parseFloat(str);
            } catch (Exception e) {
                return 0;
            }
        }
    }

    // MD5加密，32位
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 把byte的数据转换成对应的kb,M,G
     *
     * @return
     */
    public static String byteNumConvert(long size) {
        long SIZE_KB = 1024;
        long SIZE_MB = SIZE_KB * 1024;
        long SIZE_GB = SIZE_MB * 1024;
        long SIZE_TB = SIZE_GB * 1024;
        if (size < SIZE_KB) {
            // return String.format("%d字节", (int) size);
            return String.format("%.2fKB", (double) size / SIZE_KB);
        } else if (size < SIZE_MB) {
            return String.format("%.2fKB", (double) size / SIZE_KB);
        } else if (size < SIZE_GB) {
            return String.format("%.2fMB", (double) size / SIZE_MB);
        } else if (size < SIZE_TB) {
            return String.format("%.2fGB", (double) size / SIZE_GB);
        } else {
            return String.format("%.2fTB", (double) size / SIZE_TB);
        }
    }

    /**
     * 格式化毫秒->00:00
     */
    public static String formatSecondTime(long millisecond) {
        if (millisecond == 0) {
            return "00:00";
        }
        millisecond = millisecond / 1000;
        long m = millisecond / 60 % 60;
        long s = millisecond % 60;
        return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
    }

    /**
     * 格式化毫秒->00:00:00
     */
    public static String formatSecondTimeDetail(long millisecond) {
        if (millisecond <= 0) {
            return "00:00:00";
        }
        millisecond = millisecond / 1000;
        long h = millisecond / 60 / 60 % 60;
        long m = millisecond / 60 % 60;
        long s = millisecond % 60;
        return (h > 9 ? h : "0" + h) + ":" + (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
    }

    /*
     * 把返回的时间格式00:00:00--->00:00取出前面两个00,如果字符串是为null则返回00:00
     */
    public static String formatTime(String time) {
        if (isNull(time)) {
            return "00:00";
        }
        String strPattern = "^[0-9][0-9]:[0-9][0-9]:[0-9][0-9]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(time);
        if (m.matches()) {
            return time.substring(3, time.length());
        }
        return time;
    }

    /*
     * 判断字符串是不是手机号码的正则表达式
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、1 55、156、185、186 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或7或8或4，其他位置的可以为0-9
     */
    public static Boolean isPhoneNum(String phone) {
        if (StringUtil.isNull(phone)) {
            return false;
        } else {
            String telRegex = "[1][34578]\\d{9}";
            return phone.matches(telRegex);
        }
    }

    /*
     * 判断字符串是不是固定电话号码
     *规则->区号3-4位，号码7-8位,可以有分机号，分机号为3-4为，格式如下："0775-85333333-123"
     *
     */
    public static Boolean isFixedPhone(String phone) {
        if (StringUtil.isNull(phone)) {
            return false;
        } else {
            String telRegex = "(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?";
            return phone.matches(telRegex);
        }
    }

    /**
     * 把对象转化成字符串：base64加密
     *
     * @param obj
     * @return
     */
    public static String objectToStringBase64En(Object obj) {
        String objStr = "";
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objStr = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (Exception e) {
            }
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e2) {
            }
        }
        return objStr;
    }

    /**
     * 字符串转化成对象：字符串base64解密
     * @param str ：字符串
     * @return：转化的对象
     */
    public static Object string2ObjectBase64De(String str) {
        if (StringUtil.isNull(str)) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, Base64.DEFAULT));
            Object obj = new ObjectInputStream(byteArrayInputStream).readObject();
            return obj;
        } catch (Exception e) {
        } finally {
            try {
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (Exception e2) {
            }
        }
        return null;
    }

    /**
     * 判断字符串是否为json格式
     *
     * @param str
     * @return
     */
    public static boolean isJson(String str) {
        if (StringUtil.isNull(str)) {
            return false;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    // sha1签名
    public static String sha1(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes());

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return "";
        }
    }

    // 获取签名MD5指纹
    public static final String getMessageDigest(byte[] byteData) {
        if (byteData == null || byteData.length == 0) {
            return "";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(byteData);

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                int byte0 = md[i];
                buf[k++] = hexDigits[(0xF & byte0 >>> 4)];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return "";
        }
    }

    //电话号码显示处理(前三位和后四位显示出来，其他位以*显示);
    public static String phoneNumberShowDeal(String phoneNum) {
        if (StringUtil.isNull(phoneNum)) {
            return "";
        }
        int length = phoneNum.length();
        if (length > 7) {
            StringBuffer buffer = new StringBuffer(phoneNum.substring(0, 3));
            for (int i = 3; i < length - 4; i++) {
                buffer.append("*");
            }
            buffer.append(phoneNum.substring(length - 4, length));
            return buffer.toString();
        }
        return phoneNum;
    }

    //银行卡号显示处理(前4位和后四位显示出来，每隔4位显示一个空格，其他位以*显示);
    public static String bankCardNumShowDeal(String bankCardNum) {
        if (StringUtil.isNull(bankCardNum)) {
            return "";
        }
        bankCardNum = bankCardNum.trim();
        int length = bankCardNum.length();
        if (length > 8) {
            StringBuffer buffer = new StringBuffer(bankCardNum.substring(0, 4) + " ");
            for (int i = 4; i < length; i++) {
                if ((i + 1) % 4 == 0) {
                    if (i >= (length - 4)) {
                        buffer.append(bankCardNum.substring(i, i + 1) + " ");
                    } else {
                        buffer.append("* ");
                    }
                } else {
                    if (i >= (length - 4)) {
                        buffer.append(bankCardNum.substring(i, i + 1) + "");
                    } else {
                        buffer.append("*");
                    }
                }
            }
            return buffer.toString();
        }
        return bankCardNum;
    }

    /**
     * 对字符串进行处理，字符串大于7则只显示前三个和最后两个，中间用省略号表示
     *
     * @param string
     * @return
     */

    public static String getStringBySubString(String string) {
        if (isNull(string)) {
            return "";
        }
        if (string.length() > 6) {
            return string.substring(0, 3) + "..." + string.substring(string.length() - 2, string.length());
        }
        return string;
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 判断邮编
     *
     * @param zipString
     * @return
     */
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }


    public static final String[] StringSplite(String str, String regularExpression) {
        if (StringUtil.isNull(str)) {
            return null;
        }
        return str.split(regularExpression);
    }

    public static String errInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    //如果text中结尾字符串为oldStr，则把结尾的oldStr替换成newStr
    public static String replaceLastStr(String text, String oldStr, String newStr) {
        if (StringUtil.isNull(text)) {
            return text;
        }
        int index = text.lastIndexOf(oldStr);
        if (text.length() == index + oldStr.length()) {
            //最后字符串是oldStr
            text = text.substring(0, index) + newStr;
        }
        return text;
    }

    public static String second2TimeStr(long second){
        long days = second / (60 * 60 * 24);
        long hours = (second % (60 * 60 * 24)) / (60 * 60);
        long minutes = (second % (60 * 60)) / (60);
        long seconds = (second % (60));
        StringBuffer buffer = new StringBuffer();

        if(days!=0){
            buffer.append(days+"天");
            buffer.append(hours+"小时");
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else if(hours!=0){
            buffer.append(hours+"小时");
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else if(minutes!=0){
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else{
            buffer.append(seconds+"秒");
        }
        return buffer.toString();

    }

    public static String millisecond2TimeStr(long millisecond){
        long days = millisecond / (1000 * 60 * 60 * 24);
        long hours = (millisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (millisecond % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (millisecond % (1000 * 60)) / 1000;
        StringBuffer buffer = new StringBuffer();

        if(days!=0){
            buffer.append(days+"天");
            buffer.append(hours+"小时");
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else if(hours!=0){
            buffer.append(hours+"小时");
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else if(minutes!=0){
            buffer.append(minutes+"分");
            buffer.append(seconds+"秒");
        }else{
            buffer.append(seconds+"秒");
        }
        return buffer.toString();

    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}


