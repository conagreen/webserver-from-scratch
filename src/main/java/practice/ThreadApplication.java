package practice;

public class ThreadApplication {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(new Task());
        final Thread thread2 = new Thread(new Task());
        // 우선 순위 설정
        thread1.setPriority(1);

        // setDaemon은 기본값이 false
        thread1.setDaemon(true);
        thread2.setPriority(10);
        thread1.start();
//        thread2.setDaemon(true);
        thread2.start();
        Thread.sleep(1);
        System.out.println("메인 - 일어남" + Thread.currentThread().getName());

    }
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("자기 전 " + Thread.currentThread().getName());
        long l = 0;
        for (int i = 1; i < 100_0000; i++) {
            l *= i;
        }
        System.out.println("자고 난 후 : " + Thread.currentThread().getName());

    }
}
