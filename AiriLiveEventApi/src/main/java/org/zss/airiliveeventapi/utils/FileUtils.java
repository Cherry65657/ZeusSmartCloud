package org.zss.airiliveeventapi.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 本地文件处理类
 */
public class FileUtils extends FileCopyUtils {
    private final static String ROOT = "D:\\activityUpload\\upimg\\";

    public static String saveFile(MultipartFile file) {
        return saveFile(ROOT, file);
    }

    public static String saveFile(String path, MultipartFile file) {
        try {
            File dsFolder = new File(path);
            if (!dsFolder.exists()) {
                dsFolder.mkdirs();
            }
            String currTime = StringUtils.getUUId();
            String filePath = currTime
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File uploadFile = new File(path + File.separator + filePath);
            FileCopyUtils.copy(file.getBytes(), uploadFile);
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }
    
    public static String saveFiles(String path, MultipartFile file) {
        try {
            SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddhhmmss");
            File dsFolder = new File(path);
            if (!dsFolder.exists()) {
                dsFolder.mkdirs();
            }
            String currTime = StringUtils.getUUId();
            String filePath = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + "-"
                + ff.format(new Date()) + "-" + currTime
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            filePath = filePath.replace(" ", "");
            File uploadFile = new File(path + File.separator + filePath);
            FileCopyUtils.copy(file.getBytes(), uploadFile);
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }

    public static long saveFileWithName(String path, MultipartFile file) throws Exception {
        File dsFolder = new File(path);
        if (!dsFolder.exists()) {
            dsFolder.mkdirs();
        }

        File uploadFile = new File(path + File.separator + file.getOriginalFilename());
        FileCopyUtils.copy(file.getBytes(), uploadFile);

        return uploadFile.lastModified();
    }

    /**
     * 保存文件file到路径path下, 文件名称为newName
     * 
     * @param path
     * @param newName
     * @param file
     * @return
     * @throws Exception
     */
    public static long saveFileWithNewName(String path, String newName, MultipartFile file) throws IOException {
        File dsFolder = new File(path);
        if (!dsFolder.exists()) {
            dsFolder.mkdirs();
        }

        File uploadFile = new File(path + File.separator + newName); // newName:bg1.jpg
        FileCopyUtils.copy(file.getBytes(), uploadFile);

        return uploadFile.lastModified();
    }

    public static void discoverFile(String path, MultipartFile file) throws Exception {
        File uploadFile = new File(path);
        FileCopyUtils.copy(file.getBytes(), uploadFile);
    }

    public static void discoverFile(String path, String content) throws Exception {
        File uploadFile = new File(path);
        PrintStream ps = new PrintStream(uploadFile, "UTF-8");
        ps.println(content);
        ps.close();
    }

    public static String saveStringToHtml(String path, String content) {
        try {
            File dsFolder = new File(path);
            if (!dsFolder.exists()) {
                dsFolder.mkdirs();
            }
            String fileName = StringUtils.getUUId() + ".html";
            File uploadFile = new File(path + File.separator + fileName);
            PrintStream ps = new PrintStream(uploadFile, "UTF-8");
            ps.println(content);
            ps.close();
            return fileName;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + File.separator + fileName);
        file.delete();
    }

    public static String readFile(File file) {
        StringBuilder result = new StringBuilder();
        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    result.append(lineTxt);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 复制整个文件夹内容
     * 
     * @param oldPath
     *            原文件路径 如：c:/fqf
     * @param newPath
     *            复制后路径 如：f:/fqf/ff
     * @return boolean
     * @throws IOException
     */
    public static void copyFolder(String oldPath, String newPath, boolean bSameDir) throws IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            File dsFolder = new File(newPath); // 如果文件夹不存在 则建立新文件夹

            if (!dsFolder.exists()) {
                dsFolder.mkdirs();
            }
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;

            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) { // 测试字符串(oldPath)是否以指定的后缀"/"结束
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    input = new FileInputStream(temp);
                    // File uploadFile = new File();
                    output = new FileOutputStream(newPath + "\\" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }

                }
                if (bSameDir == false && temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i], bSameDir);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        } finally {
            if (output != null) {
                output.flush();
                output.close();
            }
            if (input != null) {
                input.close();
            }
        }
    }

    public static void deleteFolder(String pathDir) throws IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            File dsFolder = new File(pathDir); // 如果文件夹不存在 则建立新文件夹

            if (!dsFolder.exists()) {
                return;
            }
            File a = new File(pathDir);
            String[] file = a.list();
            File temp = null;

            for (int i = 0; i < file.length; i++) {
                if (pathDir.endsWith(File.separator)) { // 测试字符串(oldPath)是否以指定的后缀"/"结束
                    temp = new File(pathDir + file[i]);
                } else {
                    temp = new File(pathDir + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    temp.delete();

                }

            }
            dsFolder.delete();
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        } finally {
            if (output != null) {
                output.flush();
                output.close();
            }
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * 
     * @param path
     *            文件路径
     * @param content
     *            文件内容
     * @param fileFormat
     *            文件格式后缀名，如：".html"，".txt"
     * @return
     */
    public static String saveStringToFile(String path, String content, String fileFormat) {
        try {
            File dsFolder = new File(path);
            if (!dsFolder.exists()) {
                dsFolder.mkdirs();
            }
            String fileName = StringUtils.getUUId() + fileFormat;
            File uploadFile = new File(path + File.separator + fileName);
            PrintStream ps = new PrintStream(uploadFile, "UTF-8");
            ps.println(content);
            ps.close();
            return fileName;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 复制文件
     * 
     * @param srcPath
     *            源文件
     * @param targetPath
     *            复制后存放的路径
     * @throws Exception
     */
    public static void copyFile(String srcPath, String targetPath) throws Exception {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        if (!srcFile.exists()) {
            throw new Exception("文件不存在！");
        }
        if (!srcFile.isFile()) {
            throw new Exception("不是文件！");
        }
        if (targetFile.exists()) {
            throw new Exception("文件已存在！");
        }
        // 判断目标路径是否是目录
        File targetDirectory = targetFile.getParentFile();
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }
        // 获取源文件的文件名
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(targetFile);
            // 从in中批量读取字节，放入到buf这个字节数组中，
            // 从第0个位置开始放，最多放buf.length个 返回的是读到的字节的个数
            byte[] buf = new byte[8 * 1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                System.out.println("关闭输入流错误！");
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.out.println("关闭输出流错误！");
                e.printStackTrace();
            }
        }
    }

    public static boolean isNow(Date date) {
        // 当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        // 获取今天的日期
        String nowDay = sf.format(now);
        // 对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 写入文件内容
     * 
     * @param filePath
     *            文件路径
     * @param content
     *            文件内容
     * @param append
     *            false表示以覆盖形式写文件 ,true表示以追加形式写文件
     * @param charsetName
     *            编码，如：UTF-8
     * @return
     */
    public static boolean writeFileContent(String filePath, String content, boolean append, String charsetName) {
        FileOutputStream output = null;
        OutputStreamWriter out = null;
        try {
            File finalFile = new File(filePath);
            if (!finalFile.getParentFile().exists()) {
                finalFile.getParentFile().mkdirs();
            }
            // 构造函数中的第二个参数false表示以覆盖形式写文件
            output = new FileOutputStream(finalFile, append);
            out = new OutputStreamWriter(output, charsetName);
            out.write(content.toString());
            out.close();
            output.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 读取文件内容
     * 
     * @param filePath
     *            文件路径
     * @param charsetName
     *            编码，如：UTF-8
     * @return
     */
    public static String readFileContent(String filePath, String charsetName) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer content = new StringBuffer();
        try {
            File finalFile = new File(filePath);
            if (!finalFile.exists()) {
                return null;
            }
            fis = new FileInputStream(finalFile);
            isr = new InputStreamReader(fis, charsetName);
            br = new BufferedReader(isr);
            String row = null;
            while ((row = br.readLine()) != null) {
                content.append(row);
            }
            br.close();
            isr.close();
            fis.close();
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void deleteFiles(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public  static long saveFileWithDateName(String path, MultipartFile file,Date date) throws Exception{
        File dsFolder = new File(path);
        if (!dsFolder.exists()) {
            dsFolder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = fileName.replace(" ","");
        File uploadFile = new File(path + File.separator + sdf.format(date) + fileName);
        FileCopyUtils.copy(file.getBytes(), uploadFile);
        return uploadFile.lastModified();
    }

}
