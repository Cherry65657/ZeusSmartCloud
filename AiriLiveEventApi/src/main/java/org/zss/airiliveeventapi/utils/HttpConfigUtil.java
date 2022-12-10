package org.zss.airiliveeventapi.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class HttpConfigUtil {

    /**
     * 链接allConfigUrl获取数据
     * 
     * @param allConfigUrl
     *            需要获取数据的url
     *            "http://10.155.89.55/cimiss-web/api?userId=BEHK_XXZX_NXD&pwd=nxd123456&interfaceId=getSurfEleInRegionByTimeRange&dataCode=SURF_CHN_MUL_HOR&timeRange=[20181115000000,20181115020000]&adminCodes=460000&elements=Station_Name,Province&dataFormat=json"
     * @return
     */
    public static String getHttpResponse(String allConfigUrl,Boolean isSleep) {
        BufferedReader in = null;
        StringBuffer result = null;
        try {

            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();

            URLConnection connection = url.openConnection();
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();

            result = new StringBuffer();
            // 读取URL的响应

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// **注意点3**，需要此格式
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            // return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (result == null) {
            return null;
        }
        return result.toString();
    }

    public static boolean downloadFile(String urlPath, String downloadDir, String fileFullName) {
        BufferedInputStream bin = null;
        OutputStream out = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设置超时
            httpURLConnection.setConnectTimeout(1000 * 5);
            // 设置请求方式，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 建立链接从请求中获取数据
            bin = new BufferedInputStream(httpURLConnection.getInputStream());
            // 指定存放位置(有需求可以自定义)
            String path = downloadDir + File.separatorChar + fileFullName;
            File file = new File(path);
            // 校验文件夹目录是否存在，不存在就创建一个目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            out = new FileOutputStream(file);
            int size = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                out.write(buf, 0, size);
            }
            httpURLConnection.disconnect();// 关闭连接，释放资源，该方法会彻底关闭长连接，释放网络资源
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("GRB2文件下载失败！");
            return false;
        } finally {
            try {
                // 关闭资源
                if (bin != null) {
                    bin.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    /**
     * @param url
     *            网址
     * @param param
     *            参数
     * @return 连接失败，返回null
     */
    public static String sendPost(String url, String param) {
        StringBuffer result = null;// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null; // 获取输出流，往缓冲区写入数据
        try {
            // 创建URL对象
            URL connURL = new URL(url);

            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();

            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive"); // 设置连接的状态，建立持久化连接，Keep-Alive；close短连接
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)"); // 设置浏览器属性
            httpConn.setRequestProperty("Accept-Charset", "utf-8"); // 设置编码语言

            // 设置传送的内容是可序列化的java对象，即键值对
            httpConn.setRequestProperty("Content-type", "application/json");// 设置请求格式/json

            // 设置POST方式相关属性
            // 设定请求的方法为"POST"，默认是GET
            httpConn.setRequestMethod("POST");

            // 设置是否从HttpURLConnection读入，默认情况下是true;
            httpConn.setDoInput(true);

            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpConn.setDoOutput(true);

            // Post请求不能使用缓存,需设置成false
            httpConn.setUseCaches(false);

            // 防止网络异常，设置连接主机超时（单位：毫秒）
            httpConn.setConnectTimeout(10000);

            // 防止网络异常，设置从主机读取数据超时（单位：毫秒）
            httpConn.setReadTimeout(10000);

            // 设置文件请求的长度
            // httpConn.setRequestProperty("Content-Length",
            // param.getBytes().length + "");

            // 获取HttpURLConnection对象对应的输出流， 该方法已经隐含调用httpConn.connect()连接方法；
            // httpConn.connect();
            out = new PrintWriter(httpConn.getOutputStream());

            // post方式发送请求参数
            out.write(param);

            // 释放输出流的缓冲，同时关闭输出流对象，不再写入数据 ，释放网络资源
            out.flush();

            /**
             * 获取响应状态码，更多状态码请百度 (200)服务器成功返回; (404)请求网页不存在; (503)服务不可用
             */
            int code = httpConn.getResponseCode();
            if (code == 200) {
                result = new StringBuffer();
                // 定义BufferedReader输入流来读取URL的响应，设置编码方式
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                String line = null;
                // 读取返回的内容
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
            httpConn.disconnect();// 关闭连接，释放资源，该方法会彻底关闭长连接，释放网络资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close(); // 关闭输出流，释放网络资源
                }
                if (in != null) {
                    in.close(); // 关闭输入流，释放网络资源
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (result == null) {
            return null;
        }
        return result.toString();
    }

}
