package cn.edu.scau.employee.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author chen.jiale
 * @description 加密工具
 * @date 2019/11/18 12:53
 */
public class EncryptUtil {
    public static final String ALGORITHM = "md5";

    public static final int ITERATION = 1024;

    public static String getEncryptedPassword(String salt, String password) {
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        return new SimpleHash(ALGORITHM, password, byteSource, ITERATION).toHex();
    }
}
