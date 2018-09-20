package com.geeklog.common.util;

import java.io.IOException;
import java.io.InputStream;

import com.geeklog.common.exception.FTPException;
import com.geeklog.common.exception.ValidatorException;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 潘浩然
 * 创建时间 2018/09/20
 * 功能：ftp 工具类，封装 apache commons net 包下的 FtpClient
 */
public class FtpClient {

    private static final String IP = System.getenv("FTP_IP");

    private static final int PORT = Integer.parseInt(System.getenv("FTP_PORT"));

    private static final String USERNAME = System.getenv("FTP_USER");

    private static final String PASSWORD = System.getenv("FTP_PWD");

    private FTPClient innerFtpClient = new FTPClient();

    public FtpClient() {
        innerFtpClient.enterLocalPassiveMode();
    }

    public void login() {
        boolean success;
        try {
            innerFtpClient.connect(IP, PORT);
            success = innerFtpClient.login(USERNAME, PASSWORD);
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected("FtpClient.login() == false, code: " + innerFtpClient.getReplyCode()));
    }

    public void changeDirectory(String path) {
        boolean success;
        try {
            success = innerFtpClient.changeWorkingDirectory(path);
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected(
                "FtpClient.changeDirectory(..) == false, code: " + innerFtpClient.getReplyCode()));
    }

    public void setFileType(int fileType) {
        boolean success;
        try {
            success = innerFtpClient.setFileType(fileType);
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected(
                "FtpClient.setFileType(..) == false, code: " + innerFtpClient.getReplyCode()));
    }

    public void storeFile(String filename, MultipartFile file) {
        boolean success;
        try (InputStream inputStream = file.getInputStream()) {
            success = innerFtpClient.storeFile(filename, inputStream);
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected(
                "FtpClient.storeFile(..) == false, code: " + innerFtpClient.getReplyCode()));
    }

    public void logout() {
        boolean success;
        try {
            success = innerFtpClient.logout();
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected(
                "FtpClient.logout() == false, code: " + innerFtpClient.getReplyCode()));
    }

    public void deleteFile(String filename) {
        boolean success;
        try {
            success = innerFtpClient.deleteFile(filename);
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        }
        Validator.isTrue(success, ValidatorException.unexpected(
                "FtpClient.deleteFile(..) == false, code: " + innerFtpClient.getReplyCode()));
    }
}
