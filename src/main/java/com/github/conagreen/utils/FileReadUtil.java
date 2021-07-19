package com.github.conagreen.utils;

import java.io.*;

public class FileReadUtil {
    public static byte[] readFileFromClasspath(String path) {
        InputStream in = null;

        try {
            in = FileReadUtil.class.getClassLoader().getResourceAsStream(path);
            if (in != null) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final byte[] buffer = new byte[65535];
                int readBytes;

                while ((readBytes = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, readBytes);
                }
                return baos.toByteArray();
            }
            return null;
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
