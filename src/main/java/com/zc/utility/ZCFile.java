/**
 * @title File.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午4:09:54
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ZCFile {
    /**
     * 读取文件夹及子文件夹下所有的文件
     *
     * @param filePath
     *            文件夹路径
     */
    public static List<File> ReadAllFile(String filePath) {
        File f = null;
        f = new File(filePath);
        File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。
        List<File> list = new ArrayList<File>();
        for (File file : files) {
            if (file.isDirectory()) {
                // 如何当前路劲是文件夹，则循环读取这个文件夹下的所有文件
                ReadAllFile(file.getAbsolutePath());
            } else {
                list.add(file);
            }
        }
        return list;
    }

    public static List<String> ReadTextFile(String pathname) throws Exception {
        File file = new File(pathname);
        return ReadTextFile(file);
    }

    public static List<String> ReadTextFile(File file) throws Exception {
        List<String> contents = new ArrayList<String>();

        InputStreamReader reader = new InputStreamReader(new FileInputStream(
                file));
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while (line != null) {
            contents.add(line);
            line = br.readLine();
        }

        return contents;
    }

    public static List<File> readResourceFile(String path) throws IOException {
        return readResourceFile(path, null);
    }

    public static List<File> readResourceFile(String path, String extension)
            throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        path = String.format("classpath*:%s", path);
        if (extension != null) {
            path = String.format("%s/*.%s", path, extension);
        }

        Resource[] resources = resourcePatternResolver.getResources(path);
        List<File> result = new ArrayList<File>();
        for (Resource r : resources) {
            System.out.println(r.getFilename());
            result.add(r.getFile());
        }

        return result;
    }

    public static File getResourceFile(String path) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        path = String.format("classpath*:%s", path);
        Resource resource = resourcePatternResolver.getResource(path);
        return resource.exists() ? resource.getFile() : null;
    }

    public static InputStream ReadFileToStream(String pathname)
            throws FileNotFoundException {
        File file = new File(pathname);
        InputStream targetStream = new FileInputStream(file);
        return targetStream;
    }
}
