package com.github.conagreen.utils;

import java.io.*;

public class FileReadUtil {
    public static byte[] readFileToByteArray(String path) {
        try {
            final File file = new File(path);
            final FileInputStream fileInputStream = new FileInputStream(file);
            final byte[] buffer = new byte[8192];
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int readBytes;

            while ((readBytes = fileInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, readBytes);
            }

            return baos.toByteArray();

        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는 도중에 문제가 발생하였습니다.");
        }
    }
}
