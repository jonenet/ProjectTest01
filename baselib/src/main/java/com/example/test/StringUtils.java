package com.example.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者:Administrator
 * 包名:com.lz.smartcontrol.util
 * 工程名:lzsmartcontrol
 * 时间:2018/1/5 10:54
 * 说明:
 */

public class StringUtils {
    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1) {
            return "";
        }
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
            {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toUpperCase();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 自动补零
     *
     * @param num 1 开  2 关 4百分比
     * @return 命令
     */
    public static String autoCompleZore(int num) {
        final StringBuilder hexString = new StringBuilder();
        if ((num & 0xff) < 0x10)//0~F前面不零
        {
            hexString.append("0");
        }
        hexString.append(Integer.toHexString(0xFF & num));
        return hexString.toString();
    }

    /**
     * 自动补零
     *
     * @return 命令
     */
    public static String autoCompleZoreForString(String cmd, int bit) {
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bit - cmd.length(); i++) {
            hexString.append("0");
        }
        hexString.append(cmd);
        return hexString.toString();
    }

    /**
     * 取高四位
     *
     * @param data 字节
     * @return 高四位
     */
    public static int getHeight4(byte data) {//获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 取低四位
     *
     * @param data 字节
     * @return 低四位
     */
    public static int getLow4(byte data) {//获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }


    /**
     * List集合转化为String数组
     *
     * @param data
     * @return
     */
    public static String[] ListToString(ArrayList<String> data) {

        int count = data.size();
        String[] str = new String[count];
        for (int i = 0; i < count; i++) {
            str[i] = data.get(i);
        }
        return str;
    }


    /**
     * 从字符串中提取数字
     *
     * @param str
     */
    public static int getNumForString(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Integer.parseInt(m.replaceAll("").trim());
    }

    // 暂时使用这个方法，后续会在 lunznTool 1.1.2 添加
    public static boolean isEmpty(byte[] arr) {
        return null == arr || arr.length == 0;
    }
}
