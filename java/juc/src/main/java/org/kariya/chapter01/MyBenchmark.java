package org.kariya.chapter01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Fork   
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class MyBenchmark {
    static int[] ARRAY = new int[1_0000_0000];

    static {
        Arrays.fill(ARRAY, 1);
    }

    @Benchmark
    public int c() throws ExecutionException, InterruptedException {
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 4; i++) {
                sum += array[i];
            }
            return sum;
        });
        FutureTask<Integer> t2 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 4; i++) {
                sum += array[i];
            }
            return sum;
        });
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 4; i++) {
                sum += array[i];
            }
            return sum;
        });
        FutureTask<Integer> t4 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 4; i++) {
                sum += array[i];
            }
            return sum;
        });
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        return t1.get() + t2.get() + t3.get() + t4.get();
    }

    @Benchmark
    public int d() throws ExecutionException, InterruptedException {
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum += array[i];
            }
            return sum;
        });
        new Thread(t1).start();
        return t1.get();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(MyBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
