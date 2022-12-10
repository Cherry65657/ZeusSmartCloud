package org.zss.airiliveeventapi.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 继承自Spring util的工具类，减少jar依赖
 *
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
    public static final String METHOD_NAME = "interfaceId";
    public static final String START_TIME = "request_start_time";
    public static final String SHIRO_FILTER = "shiro_filter";
    public static final String REQUEST_FROM = "from";

    public static final String FROM_XCX = "wxxcx";
    public static final String FROM_WEB = "web";

    //微信公众号模板编号
    public static final String WEATHER_NOTES = "OPENTM206953808";
    public static final String METEOROLOGIC_NOTES = "TM00982";
    public static final String METEOROLOGIC_REMOVE = "OPENTM207432227";

    public static SimpleDateFormat dateFormatChinese = new SimpleDateFormat("yyyy年MM月dd日 E HH时");
    public static SimpleDateFormat dateFormatChineseWarn = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分");
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     *
     * @param cs the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(final CharSequence cs) {
        return !StringUtils.isNotBlank(cs);
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     * <p>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     * not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.hasText(cs);
    }

    /**
     * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     *
     * @param coll  the {@code Collection} to convert
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }

    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     *
     * @param arr   the array to display
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Object[] arr, String delim) {
        return StringUtils.arrayToDelimitedString(arr, delim);
    }

    /**
     * 生成uuid
     *
     * @return UUID
     */
    public static String getUUId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成 aesKey
     *
     * @return aesKey
     */
    public static String getAESKey() {
        String ku = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder newStr = new StringBuilder();
        // 创建一个Random用以生成伪随机数，也可采用Math.random()来实现
        Random r = new Random();
        for (int j = 0; j < 43; j++) {
            int r2 = r.nextInt(ku.length());
            newStr.append(ku.charAt(r2));
        }
        return newStr.toString();
    }

    public static String getDateWithWeek(Date date) {
        return dateFormatChinese.format(date);
    }

    public static String getDateWithWarn(Date date) {
        return dateFormatChineseWarn.format(date);
    }
    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param c 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    private static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0;
    }

    public static int pictogramLength(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (char aC : c) {
            len++;
            if (!isLetter(aC)) {
                len++;
            }
        }
        return len;
    }
    
    /**
     * 获取文件夹的大小的方法
     *
     * 
     * 
     */
    public static  long  getFileSize(File f)  
            throws  Exception  
        {  
            long  size =  0 ;  
            File flist[] = f.listFiles();  
            for  ( int  i =  0 ; i < flist.length; i++)  
            {  
                if  (flist[i].isDirectory())  
                {  
                    size = size + getFileSize(flist[i]);  
                } else   
                {  
                    size = size + flist[i].length();  
                }  
            }  
            return  size;  
        }
    
    
}
