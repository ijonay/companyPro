/** 
 * @title ZC_SFTPClient.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月24日 上午11:41:37 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ZC_SFTPClient {
    private final static String loginName = "hyl";
    private final static String password = "5imBiGELpUj3";
    private final static String host = "192.168.1.100";
    private final static Integer port = 22;
    public final static ChannelSftp sftp = getChannel(loginName, password,
            host, port);

    public static ChannelSftp getChannel(String loginName, String password,
            String host, Integer port) {
        ChannelSftp sftp = null;
        JSch jsch = new JSch();
        try {
            Session sshSession = jsch.getSession(loginName, host, port);
            sshSession.setPassword(password);
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return sftp;
    }

    public static Boolean uploadFile(String sourceFilePath, String targetFileUri) {
        Boolean result = true;
        if (sftp == null) {
            result = false;
        }
        try {
            if (!sftp.isConnected())
                sftp.connect();
            sftp.put(sourceFilePath, targetFileUri);
        } catch (Exception e) {
            result = false;
        } finally {
            sftp.exit();
            sftp.disconnect();
        }

        return result;
    }

    public static InputStream downloadFile(String targetFileUri) {
        InputStream inputStream = null;
        if (sftp != null) {
            try {
                inputStream = sftp.get(targetFileUri);
            } catch (SftpException e) {
            }
        }
        return inputStream;
    }

    public static Boolean deleteFile(String targetFileUri) {
        Boolean result = true;
        if (sftp == null) {
            result = false;
        }
        try {
            sftp.rm(targetFileUri);
        } catch (SftpException e) {
            result = false;
        }
        return result;
    }

}
