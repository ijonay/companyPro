/** 
* @title FileHepler.java
* @author zhuzhzh
* @date：2016年6月22日 上午10:47:58  
* 文件帮助类 
*/
package com.zc.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    /**
     * 读取文件流
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:07:48
     */
    public static BufferedReader getFileReader(String path)
            throws FileNotFoundException {

        String filter = "file://";

        if (path.contains(filter)) {
            path = path.replace(filter, "");
        }

        File file = new File(path);

        if (!file.exists()) {
            throw new RuntimeException("文件不存在！");
        }

        InputStreamReader streamReader = new InputStreamReader(
                new FileInputStream(file));

        BufferedReader bufferedReader = new BufferedReader(streamReader);

        return bufferedReader;

    }

    /**
     * 获取资源文件
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:20:33
     */
    public static BufferedReader getResourceReader(String path)
            throws FileNotFoundException {

        InputStream inputStream = FileHelper.class.getClassLoader()
                .getResourceAsStream(path);

        if (inputStream == null) {
            throw new RuntimeException("文件不存在！");
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));

        return bufferedReader;

    }

    /**
     * 读取资源文件内容（适用于小文件读取，大文件读取尽量使用getFileReader的方式）
     * 
     * @param path
     * @return
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:21:23
     */
    public static List<String> getResourceContent(String path)
            throws IOException {

        List<String> list = new ArrayList<>();

        BufferedReader br = getResourceReader(path);

        String line = null;

        while ((line = br.readLine()) != null) {

            list.add(line);

        }

        return list;

    }

    /**
     * 读取文件内容（适用于小文件读取，大文件读取尽量使用getFileReader的方式）
     * 
     * @param path
     * @return
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:08:09
     */
    public static List<String> getFileContent(String path) throws IOException {

        List<String> list = new ArrayList<>();

        BufferedReader br = getFileReader(path);

        String line = null;

        while ((line = br.readLine()) != null) {

            list.add(line);

        }

        return list;

    }

    /**
     * 写入文件
     * 
     * @param path
     * @param list
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:04:52
     */
    public static File write(String path, String content) {
        return write(path, new ArrayList<String>() {
            private static final long serialVersionUID = 1L;
            {
                add(content);
            }
        }, false);
    }

    /**
     * 写入文件
     * 
     * @param path
     * @param list
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:04:52
     */
    public static File write(String path, String content, Boolean append)
            throws IOException {
        return write(path, new ArrayList<String>() {

            private static final long serialVersionUID = 1L;

            {
                add(content);
            }
        }, append);
    }

    /**
     * 写入文件
     * 
     * @param path
     * @param list
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:04:52
     */
    public static File write(String path, List<String> list)
            throws IOException {
        return write(path, list, false);
    }

    /**
     * 写入文件
     * 
     * @param path
     * @param list
     * @param append
     * @throws IOException
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:07:35
     */
    public static File write(String path, List<String> list, Boolean append) {

        File file = new File(path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file, append));

            int i = 0;
            for (String str : list) {

                bw.write(str);

                if (i != list.size() - 1)
                    bw.newLine();

                i++;
            }

            bw.flush();
            bw.close();

            return file;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    /**
     * 写入临时文件 注意：temp目录的清理工作当前方法暂时不涉及！
     * 
     * @param exName
     *            扩展名称 如：xml、html...
     * @param content
     *            需要写入的内容
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月18日 上午11:22:27
     */
    public static File writeToTempFile(String exName, String content) {

        String md5Str = EncryptHelper.encryptToMD5(content);

        String tempPath = PackageHelper.getResourcePath() + "temp/";

        File tempFilePath = new File(tempPath);

        if (!tempFilePath.exists()) {

            tempFilePath.mkdirs();

        }

        String filePath = tempPath + md5Str + "." + exName;

        File file = new File(filePath);

        if (!file.exists()) {

            file = FileHelper.write(filePath, content);

        }

        return file;

    }

}
