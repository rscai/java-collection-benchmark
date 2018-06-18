package io.github.rscai.collections.list.concurrent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class CopyOnWriteArrayListBenchmark {

  private List<Integer> list;

  private final int n = 1000000;


  @Setup(Level.Iteration)
  public void initList() {
    Random random = new Random();
    Integer[] array = new Integer[n];
    for (int i = 0; i < n; i++) {
      array[i] = random.nextInt();
    }

    list = new CopyOnWriteArrayList<>(array);
  }

  @Benchmark
  @Group("producer_consumer")
  public void produce(Blackhole blackhole) {
    Random random = new Random();
    final int value = random.nextInt();
    final int index = random.nextInt(list.size());
    list.add(index, value);
    blackhole.consume(list.remove(random.nextInt(list.size())));
  }

  @Benchmark
  @Group("producer_consumer")
  public void consume(Blackhole blackhole) {
    Random random = new Random();
    final int index = random.nextInt(list.size());
    blackhole.consume(list.get(index));
  }
}

