# Java Collections Benchmark

## List

## General List Implementation

./gradlew benchmark -PjmhInclude=ArrayListBenchmark,LinkedListBenchmark -PjmhResultsFile=list_general.txt

### Concurrent List Implementation

#### 1 Consumer 1 Producer

```shell
./gradlew benchmark -PjmhInclude=CopyOnWriteArrayListBenchmark,SynchronizedListBenchmark,VectorBenchmark -PjmhThreadGroups=1,1 -PjmhResultsFile=list_concurrent_c1_p1.txt
```

#### 3 Consumer 1 Producer

```shell
./gradlew benchmark -PjmhInclude=CopyOnWriteArrayListBenchmark,SynchronizedListBenchmark,VectorBenchmark -PjmhThreadGroups=3,1 -PjmhResultsFile=list_concurrent_c3_p1.txt
```

#### 7 Consumer 1 Producer 

```shell
./gradlew benchmark -PjmhInclude=CopyOnWriteArrayListBenchmark,SynchronizedListBenchmark,VectorBenchmark -PjmhThreadGroups=7,1 -PjmhResultsFile=list_concurrent_c7_p1.txt
```