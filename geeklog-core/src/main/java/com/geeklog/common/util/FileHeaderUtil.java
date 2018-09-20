package com.geeklog.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.geeklog.common.exception.ValidatorException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 潘浩然
 * 创建时间 2018/09/20
 * 功能：获取文件头字节信息的工具类，用于判断文件类型
 */
public class FileHeaderUtil {

    private static final Map<String, String> fileTypes = new HashMap<>();

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：允许的上传文件类型
     */
    static {
        fileTypes.put("FFD8FF", "jpg");
        fileTypes.put("89504E47", "png");
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：根据文件的前几个字节判断文件类型为 jp(e)g 或 png，若都不是，抛出文件类型异常
     */
    public static String getFileType(MultipartFile file) {
        String header = getFileHeader(file);
        for (String fileHeader : fileTypes.keySet()) {
            if (header.startsWith(fileHeader)) {
                return fileTypes.get(fileHeader);
            }
        }

        throw ValidatorException.FILE_TYPE_INVALID;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：获取文件的前 8 个字节
     */
    private static String getFileHeader(MultipartFile file) {
        String value;
        try (InputStream inputStream = file.getInputStream()) {
            byte[] b = new byte[8];
            inputStream.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (IOException e) {
            throw ValidatorException.unexpected("FileHeaderUtil.getFileHeader(..) 读取流错误");
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}
