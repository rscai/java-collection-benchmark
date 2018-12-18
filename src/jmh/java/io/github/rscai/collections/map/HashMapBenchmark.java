package io.github.rscai.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
public class HashMapBenchmark {

  private Map<Integer, Integer> map;

  @Param({"10000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000",
      "100000"})
  private int n;
  private int loopO1 = 1_000_000;

  private Random random = new Random();

  @Setup(Level.Invocation)
  public void setUp() {
    map = new HashMap<>(n * 100);
    for (int i = 0; i < n; i++) {
      map.put(random.nextInt(), random.nextInt());
    }
  }

  @Benchmark
  public void put(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      Integer key = random.nextInt();
      Integer value = random.nextInt();

      blackhole.consume(map.put(key, value));
    }
  }

  @Benchmark
  public void get(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      Integer key = random.nextInt();
      blackhole.consume(map.get(key));
    }
  }

  @Benchmark
  public void remove(Blackhole blackhole) {
    for (int i = 0; i < loopO1; i++) {
      Integer key = random.nextInt();
      blackhole.consume(map.remove(key));
    }
  }
}
