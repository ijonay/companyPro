/**
 * @title PackageHelper.java
 * @author zhuzhzh
 * @date：2016年6月20日 下午5:49:48
 * package帮助类
 */
package com.zc.utility;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class PackageHelper {

    /**
     * 获取项目路径
     *
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月20日 下午5:50:46
     */
    public static String getProjectPath() {

        String path = System.getProperty("user.dir");
        PackageHelper packageHelper = new PackageHelper();

        String path1 = PackageHelper.class.getResource("/").getFile().toString();

        if (path1.indexOf("/") == 0) {
            path1 = path1.substring(1).replace("/", "\\");
            path1=path1.substring(0,path1.lastIndexOf("\\target"));
        }


        return path1;

    }

    /**
     * 获取资源文件夹路径地址
     *
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月15日 下午12:13:35
     */
    public static String getResourcePath() {

        return PackageHelper.class.getClassLoader().getResource("").getPath();

    }

    /**
     * 获取资源文件路径
     *
     * @param resourcePath
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:44:33
     */
    public static String getResourceFile(String resourcePath) {

        URL url = PackageHelper.class.getClassLoader()
                .getResource(resourcePath);

        if (url == null) {
            throw new RuntimeException("文件不存在！");
        }

        return url.getPath();
    }

    /**
     * 获取资源文件路径 当前项目的资源路径地址（非生成后资源地址）
     *
     * @param resourcePath
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:48:59
     */
    public static String getResourceFileByTarget(String resourcePath) {
        return getResourcePathByTarget(null, resourcePath);
    }

    /**
     * 获取资源文件路径 当前项目的资源路径地址（非生成后资源地址）
     *
     * @param resourceTarget
     * @param resourcePath
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月22日 上午11:48:59
     */
    public static String getResourcePathByTarget(String resourceTarget,
                                                 String resourcePath) {

        if (resourceTarget == null) {
            resourceTarget = "src/main/resources";
        }

        String projectPath = PackageHelper.getProjectPath();

        resourceTarget = resourceTarget.replaceAll("/", "\\\\");

        resourcePath = resourcePath.replaceAll("/", "\\\\");

        String path = projectPath + "\\" + resourceTarget + "\\" + resourcePath;

        return path;
    }

    public static String getPackageAbsolutePath(String targetPath,
                                                String packageName) {

        targetPath = targetPath.replaceAll("/", "\\\\");
        packageName = packageName.replaceAll("/", "\\\\").replaceAll("\\.",
                "\\\\");

        String path = getProjectPath() + "\\" + targetPath;

        path += "\\" + packageName;

        return path;

    }

    /**
     * 判断package地址是否存在
     *
     * @param targetPath  指定项目路径下
     * @param packageName
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月20日 下午6:32:22
     */
    public static boolean existsPackage(String targetPath, String packageName) {

        String path = getPackageAbsolutePath(targetPath, packageName);

        File file = new File(path);

        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            throw new RuntimeException("非法地址路径！");
        }
        return false;
    }

    /**
     * 创建package路径地址 如果存在则不进行任何操作
     *
     * @param targetPath  指定项目路径下
     * @param packageName
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月20日 下午6:32:40
     */
    public static void createPackage(String targetPath, String packageName) {

        if (existsPackage(targetPath, packageName)) {
            return;
        }

        runProcess(targetPath, packageName, (path, file) -> {

            if (!file.exists()) {
                file.mkdirs();
            }

            return true;

        });

    }

    public static void delPackage(String targetPath, String packageName) {
        delPackage(targetPath, packageName, false);
    }

    public static void delPackage(String targetPath, String packageName,
                                  boolean flag) {

        targetPath = targetPath.replaceAll("/", "\\\\");
        packageName = packageName.replaceAll("/", "\\\\");

        String path = getProjectPath() + "\\" + targetPath;

        String[] paths = packageName.split("\\.");

        List<String> pathList = new ArrayList<>();

        for (String strPath : paths) {

            pathList.add(path = path + "\\" + strPath);

        }

        int startIndex = pathList.size() - 1;

        for (int i = startIndex; i > -1; i--) {

            String strPath = pathList.get(i);

            File file = new File(strPath);

            if (file.exists() && file.isDirectory()) {

                if (startIndex == i) {

                    if (flag) {
                        recursiveDelFile(file);
                    } else {

                        boolean includeDir = true;

                        for (File nextFile : file.listFiles()) {

                            if (nextFile.isDirectory()) {
                                includeDir = false;
                                break;
                            }

                        }

                        if (includeDir) {
                            recursiveDelFile(file);
                        }

                    }

                } else {

                    if (file.listFiles().length < 1) {
                        recursiveDelFile(file);
                    }

                }

            } else if (file.exists() && !file.isDirectory()) {

                throw new RuntimeException("package名称格式不正确！");

            }

        }

    }

    private static void recursiveDelFile(File file) {

        if (file.isDirectory()) {

            File files[] = file.listFiles();

            if (files.length > 0) {

                for (File childFile : files) {
                    recursiveDelFile(childFile);
                }

            }

        }

        file.delete();

    }

    // private static boolean includeFiles(File curFile) {
    //
    // for (File file : curFile.listFiles()) {
    //
    // if (file.isDirectory()) {
    //
    // return true;
    //
    // }
    //
    // }
    //
    // return false;
    //
    // }

    private static boolean runProcess(String targetPath, String packageName,
                                      BiPredicate<PathModel, File> defFunc) {

        targetPath = targetPath.replaceAll("/", "\\\\");
        packageName = packageName.replaceAll("/", "\\\\");

        String path = getProjectPath() + "\\" + targetPath;

        File targetFile = new File(path);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String[] paths = packageName.split("\\.");

        PathModel pathModel = new PathModel();

        pathModel.setPaths(paths);

        Integer i = 0;

        for (String strPath : paths) {

            path = path + "\\" + strPath;

            File file = new File(path);

            pathModel.setIndex(i);
            pathModel.setPath(strPath);

            boolean flag = defFunc.test(pathModel, file);

            if (!flag) {
                return flag;
            }

            i++;
        }

        return true;

    }

}

class PathModel {

    private Integer index;
    private String paths[];
    private String path;

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the paths
     */
    public String[] getPaths() {
        return paths;
    }

    /**
     * @param paths the paths to set
     */
    public void setPaths(String paths[]) {
        this.paths = paths;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}
