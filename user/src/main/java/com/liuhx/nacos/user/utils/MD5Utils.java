package com.liuhx.nacos.user.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static final int KEY = 20200713;
    public static String STAR_KEY = "";

    public static String makeSHA(byte[] input) {
        String shaStr = "";
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("SHA1");
            md5.reset();
            md5.update(input);

            byte[] m = md5.digest();//加密
            shaStr = byteArrayToHexString(m);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return shaStr;
    }


    public static String makeStarMd5Name(Long starId) {
        StringBuffer videoname = new StringBuffer();
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update((String.valueOf(starId + STAR_KEY)).getBytes());
            byte[] m = md5.digest();//加密
            String ms = byte2hex(m);
            videoname.append(ms.substring(0, 6));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoname.toString();
    }


    public static String byte2hex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String temp = Integer.toHexString(((int) data[i]) & 0xFF);
            for (int t = temp.length(); t < 2; t++) {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }


    public static String makeMD5Name(String input) {
        StringBuffer videoname = new StringBuffer();
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update((String.valueOf(input + KEY)).getBytes());
            byte[] m = md5.digest();//加密
            String ms = byte2hex(m);
            videoname.append(ms);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return videoname.toString();
    }

    /**
     * 将输入的字符串md5加密
     *
     * @param input
     * @return
     */
    public static String getMd5String(String input) {
        if (input == null) {
            return null;
        }
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(input.getBytes());
            byte[] m = md5.digest();//加密
            String ms = byte2hex(m);
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }

    /**
     * 判断用户输入的密码是否和数据库保存的密码相同
     *
     * @return
     */
    public static boolean isSameValue(String input, String pwdDB) {
        if (input == null || input.equals("")) {
            return false;
        }
        String md5pwd = getMd5String(input.trim());
        if (md5pwd.equals(pwdDB)) {
            return true;
        }
        return false;
    }

    public static final int LEN = 6;

    public static String getSongCode(long modifyTime) {
        long ts = modifyTime + MD5Utils.KEY;
        return MD5Utils.getMd5String(String.valueOf(ts)).substring(0, LEN) + modifyTime;
    }


    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest()
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }


    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return hexDigits[iD1] + hexDigits[iD2];
    }
}
