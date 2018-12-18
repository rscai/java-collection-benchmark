package io.github.rscai.collections.set;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
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
public class TreeSetBenchmark {


  private Set<Integer> set;
  private Set<Integer> anotherSet;

  @Param({"10000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000",
      "100000"})
  private int n;
  private int loopOlogn = 1_000_000;
  private int anotherSetSize = 100;
  private Random random = new Random();

  @Setup(Level.Invocation)
  public void setUp() {
    set = new TreeSet<>();
    for (int i = 0; i < n; i++) {
      set.add(random.nextInt());
    }
    anotherSet = new TreeSet<>();
    for (int i = 0; i < anotherSetSize; i++) {
      anotherSet.add(random.nextInt());
    }
  }

  @Benchmark
  public void add(Blackhole bh) {
    for (int i = 0; i < loopOlogn; i++) {
      bh.consume(set.add(random.nextInt()));
    }
  }

  @Benchmark
  public void contains(Blackhole bh) {
    for (int i = 0; i < loopOlogn; i++) {
      bh.consume(set.contains(random.nextInt()));
    }
  }

  @Benchmark
  public void remove(Blackhole bh) {
    for (int i = 0; i < loopOlogn; i++) {
      bh.consume(set.remove(random.nextInt()));
    }
  }

  @Benchmark
  public void retainAll(Blackhole bh) {
    for (int i = 0; i < loopOlogn; i++) {
      bh.consume(set.retainAll(anotherSet));
    }
  }

}
