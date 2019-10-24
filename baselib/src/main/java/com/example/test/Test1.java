package com.example.test;

import java.util.concurrent.Semaphore;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.test
 * ProjectName: ProjectTest01
 * Date: 2019/8/1 17:35
 */
public class Test1 {

    static String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    static String[] s2 = {"十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿"};

    static byte[] bytes = new byte[]{102, 111, 114, 32, 99, 104, 101, 99, 107, 40, 73, 70, 32, 70, 105, 108, 116, 101, 114, 41, 32, 80, 65, 83, 83, 102, 111, 114, 32, 99, 104, 101, 99, 107, 40, 86, 67, 79, 32, 67, 117, 114, 114, 101, 110, 116, 41, 32, 80, 65, 83, 83, 82, 83, 83, 73, 32, 67, 97, 108, 105, 98, 114, 97, 116, 105, 111, 110, 32, 112, 114, 111, 99, 101, 100, 117, 114, 101, 32, 64, 83, 84, 66, 32, 115, 116, 97, 116, 101, 32, 80, 65, 83, 83, 86, 67, 79, 32, 99, 97, 108, 105, 98, 114, 97, 116, 105, 111, 110, 32, 112, 114, 111, 99, 101, 100, 117, 114, 101, 32, 64, 83, 84, 66, 32, 115, 116, 97, 116, 101, 32, 80, 65, 83, 83, 102, 111, 114, 32, 99, 104, 101, 99, 107, 40, 86, 67, 79, 32, 66, 97, 110, 100, 41, 32, 80, 65, 83, 83, 116, 114, 97, 110, 115, 109, 105, 116, 95, 49, 46, 54, 83, 80, 73, 32, 80, 65, 83, 83};
// 666F7220636865636B2849462046696C74657229
// 2050415353666F7220636865636B2856434F2043757272656E7429
// 2050415353525353492043616C6962726174696F6E2070726F6365647572652040535442207374617465
// 205041535356434F2063616C6962726174696F6E2070726F6365647572652040535442207374617465
// 2050415353666F7220636865636B2856434F2042616E6429
// 20504153537472616E736D69745F312E36535049
// 2050415353

    //    A5FC770B00000000011B0B0200F07017E89C3F0272863F0272863F0272863F0272863F0272863F0272863F0272863F0272863F0226823F0226823F0226823F0226823F0226823F0226823F0226823F0226823F0272863F0272863F0272863F0272863F0272863F0272863F0272863F0272863F0226823F0226823F0226823F0226823F0226823F0226823F0226823F0226823F0272863F0226823F0272863F0272863F0272863F0272863F0272863F0272863F0226823F0272863F0226823F0226823F0226823F0226823F0226823F0226823F0272863F0226823F0226823F0272863F0272863F0272863F0272863F0272863F0226823F0272863F027286F6CE
    private static long[] mHits1 = new long[4];
    private static Semaphore semaphore;
    private static void showLog(){
        try {
            semaphore.acquire();
            System.out.println("xqxinfo 线程:"+Thread.currentThread().getName()+"执行了一个acquire请求操作");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程休眠1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        System.out.println("xqxinfo 线程:"+Thread.currentThread().getName()+"执行了一个release请求操作");
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
//            System.arraycopy(mHits1, 1, mHits1, 0, mHits1.length - 1);
//            mHits1[mHits1.length - 1] = SystemClock.uptimeMillis();
//            System.out.println(Arrays.toString(mHits1));
//            SystemClock.sleep(300);
//            if (mHits1[0] >= (SystemClock.uptimeMillis() - 2000)) {
//                System.out.println("========="+Arrays.toString(mHits1));
//            }
//        }

        semaphore = new Semaphore(5);

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    showLog();
                }
            }).start();
        }
        System.out.println(0x30000000);
    }

//        System.out.println(StringUtils.toHexString(bytes));
//        byte[] a5s = StringUtils.hexStringToBytes("A5");
//        for (byte a5 : a5s) {
//            System.out.println(a5);
//        }

    private static String toChinese(String string) {
        StringBuilder result = new StringBuilder();
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            String s3 = s1[num];
            if (i != n - 1 && num != 0) {
                String s = s2[n - 2 - i];
                if (n == 2 && "一".equals(s3)) {
                    result.append(s);
                } else {
                    result.append(s3).append(s);
                }
            } else {
                if (n > 2 && i == n - 1 && "零".equals(s3)) {
                } else {
                    result.append(s3);
                }

            }
        }
        return result.toString();
    }


}
