package io.github.rscai.collections.list;

import java.util.ArrayList;
import java.util.List;
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

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@Fork(1)
public class ArrayListBenchmark {

  private List<Integer> list;

  @Param({"10000", "100000", "1000000", "10000000"})
  private int n=0;

  @Setup(Level.Iteration)
  public void initList() {
    Random random = new Random();
    list = new ArrayList<>(Math.round(n * 1.2F));
    for (int i = 0; i < n; i++) {
      list.add(random.nextInt());
    }
  }

  @Benchmark
  public void measureAdd(){
    list.add(0);
  }
  @Benchmark
  public void measureAddAtIndex() {
    Random random = new Random();
    int index = random.nextInt(n);
    list.add(index, 0);
  }
  
  @Benchmark
  public void measureGet(){
    Random random = new Random();
    int index = random.nextInt(n-1);
    int one = list.get(index);
  }
  @Benchmark
  public void measureRemoveAtIndex() {
    Random random = new Random();
    int index = Math.min(random.nextInt(n-1),list.size()-1);
    int removedOne = list.remove(index);
  }
  
  @Benchmark
  public void measureSetAtIndex(){
    Random random = new Random();
    int index = random.nextInt(n-1);
    list.set(index,1);
  }
  
  @Benchmark
  public void measureIndexOf() {
    Random random = new Random();
    int object = random.nextInt(n);
    list.indexOf(object);
  }
}
