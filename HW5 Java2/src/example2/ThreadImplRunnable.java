package example2;

import java.util.concurrent.Callable;

public class ThreadImplRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " запущен");
        try {
            throw new InterruptedException("Generation exception");
        } catch (InterruptedException e) {
            System.err.println("Поток " + Thread.currentThread().getName() + " завершился неудачно");
            Thread.currentThread().stop(e);
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершился");
    }

    private static class Test {
        public static void main(String[] args) {
            System.out.println("Поток " + Thread.currentThread().getName() + " запущен");
            Thread thread = new Thread(new ThreadImplRunnable());
            thread.start();
            System.out.println("Поток " + Thread.currentThread().getName() + " завершился");
        }
    }
}
