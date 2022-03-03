package com.github.conagreen.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class WebServer implements Runnable {

    @Override
    public void run() {
        final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(5);
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.SECONDS, blockingQueue);
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            Socket connection;
            while ((connection = serverSocket.accept()) != null) {
                threadPoolExecutor.execute(new RequestHandler(connection));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
