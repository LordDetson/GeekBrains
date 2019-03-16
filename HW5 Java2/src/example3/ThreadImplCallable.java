package example3;

import example2.ThreadImplRunnable;

import java.util.concurrent.Callable;

public class ThreadImplCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("Поток " + Thread.currentThread().getName() + " запущен");
        try {
            throw new InterruptedException("Generation exception");
        } catch (InterruptedException e) {
            System.err.println("Поток " + Thread.currentThread().getName() + " завершился неудачно");
            Thread.currentThread().stop(e);
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершился");
        return null;
    }

    private static class Test {
        public static void main(String[] args) {
            System.out.println("Поток " + Thread.currentThread().getName() + " запущен");
            Thread threadCallable = new Thread(() -> {
                try {
                    new ThreadImplCallable().call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, "threadCallable");
            Thread threadRunnable = new Thread(new ThreadImplRunnable(), "threadRunnable");
            threadCallable.start();
            threadRunnable.start();
            System.out.println("Поток " + Thread.currentThread().getName() + " завершился");
        }
    }
}
