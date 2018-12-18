package io.github.rscai.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class ArrayListBenchmark {

  private List<Integer> list;

  @Param({"10000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000",
      "100000"})
  private int n = 0;
  private int loopO1 = 1_000_000;
  private int loopOn = 10_000;
  private Random random = new Random();


  @Setup(Level.Invocation)
  public void initList() {
    list = new ArrayList<>(n * 3);
    for (int i = 0; i < n; i++) {
      list.add(random.nextInt());
    }
  }

  @Benchmark
  public void add(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      blackhole.consume(list.add(0));
    }
  }

  @Benchmark
  public void addAtIndex() {
    for (int i = 0; i < loopOn; i++) {
      int index = random.nextInt(n);
      list.add(index, 0);
    }
  }

  @Benchmark
  public void get(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      int index = random.nextInt(n);
      blackhole.consume(list.get(index));
    }
  }

  @Benchmark
  public void removeAtIndex(Blackhole blackhole) {
    for (int i = 0; i < loopOn; i++) {
      int index = random.nextInt(list.size());
      blackhole.consume(list.remove(index));
    }
  }

  @Benchmark
  public void setAtIndex(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      int index = random.nextInt(n);
      blackhole.consume(list.set(index, 1));
    }
  }

  @Benchmark
  public void indexOf(Blackhole blackhole) {
    for (int i = 0; i < loopOn; i++) {
      int object = random.nextInt(n);
      blackhole.consume(list.indexOf(object));
    }
  }
}
