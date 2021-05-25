The Cost of indirection
=======================

A demonstration of the overhead of heap allocated objects.

Java
----
* You might need to increase the JVM heap size or decrease the number of points in the code for this to work.
* The Heap-Values for `InlinePoints` and `InlineArrayListPoints` are too large because for some reason the intermediary `ArrayList<Point>` doesn't get garbage collected. Subtract `8226MiB` from those values to get a more accurate estimate.

```
java$ ./gradlew build # or gradlew.bat on Windows?
java$ cd build/classes/java/main
java/build/classes/java/main$ java Main
```

NOTE: I have no clue about Gradle and how to do this probably. But I also don't care in case of this project.

```
ForLoopPoints: Average length: 1.5000004E7 (0.512267976s) (ca. 8226MiB)
StreamPoints: Average length: 1.5000004E7 (0.461607211s) (ca. 8226MiB)
InlinePoints: Average length: 1.5000004E7 (0.149729715s) (ca. 10522MiB)
InlineArrayListPoints: Average length: 1.5000004E7 (0.408262281s) (ca. 15100MiB)
```

Rust
----
* Don't forget to compile in release mode, otherwise this will be really slow!

`rust$ cargo run --release`

```
Point: 8B
Box<Point>: 8B
Box<dyn DynamicPoint>: 16B

StructPoints: Average length: 15000004 (0.139791539s) (ca. 2288MiB)
BoxedPoints: Average length: 15000004 (0.407481124s) (ca. 4577MiB)
DynamicPoints: Average length: 15000004 (0.581427538s) (ca. 6866MiB)
```
