package practice;

import java.io.*;

public class PracStream {
    public static void main(String[] args) {
        final File origin = new File("crushed-duck.jpg");
        try (final FileInputStream fis = new FileInputStream(origin);
             final FileOutputStream fos = new FileOutputStream("crushed-duck-copied.jpg")) {
            final byte[] buffer = new byte[9192];
            int result = 0;

            /**
             * Reads up to <code>buffer.length</code> bytes of data from this input
             * stream into an array of bytes. 더 이상 읽어들일 data가 없으면 -1 return
             */
            while ((result = fis.read(buffer)) != -1) {
                // 남는 바이트 만큼   출력
                fos.write(buffer);
                System.out.println("read bytes: " + result + "bytes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
