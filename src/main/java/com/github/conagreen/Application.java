package com.github.conagreen;

import com.github.conagreen.webserver.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public static void main(String[] args) {

        // 포트 8080번에 대기하는 서버소켓 생성
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            Socket connection;

            /*
            accept()가 호출되면 프로그램은 여기서 실행을 멈추고 클라이언트 포트가 8080번으로 연결할 때까지 무한 대기.
            클라이언트가 연결되면 accept() 메소드는 Socket 객체를 반환한다.
            반환된 연결은 java.net.Socket 객체 형태로 반환되며 클라이언트에서 사용한 것과 같다.
            */
            while ((connection = serverSocket.accept()) != null) {
                final Thread handlerThread = new Thread(new RequestHandler(connection));
                handlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
