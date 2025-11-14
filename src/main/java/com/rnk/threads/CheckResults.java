package com.rnk.threads;

import java.util.concurrent.*;

public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> result = service.submit(() -> {
            for (int i = 0; i < 1000000; i++)
                counter++;
        });
        try {
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        } finally {
            service.shutdown();
        }
    }
}
