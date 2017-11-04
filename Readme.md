# dauphine-slpbd-tp
Correction des TPs du cours Systèmes langages et paradigmes big data du M2 MIAGE de l'Université Paris-Dauphine

# Programming environment tips
Here are some tips so you quickly setup your environment:
- Connection through ssh to the Unix cluster
``` bash
ssh -i path_to_your_private_key -p 993 userXY@www.lamsade.dauphine.fr
```
- Alias it, add to your ~/.bashrc or ~/.bash_profile file (do not forget to source it 😉)
``` bash
alias cluster="ssh -i path_to_your_private_key -p 993 userXY@www.lamsade.dauphine.fr"
```
- Copying a file `Order.txt` located in the current directory using scp
``` bash
scp -i path_to_your_private_key -P 993 Order.txt userXY@www.lamsade.dauphine.fr:.
```
- Alias it, add to your ~/.bashrc or ~/.bash_profile file (do not forget to source it 😉)
``` bash
alias local_2_cluster="scp -i path_to_your_private_key -P 993 $1 userXY@www.lamsade.dauphine.fr:/home/cluster/userXY/$2"
# Usage
local_2_cluster local_file remote_directory
```
- (Bonus) - Creating a directory on the cluster
``` bash
alias mkdir_cluster="ssh -i path_to_your_private_key -p 993 userXY@www.lamsade.dauphine.fr 'mkdir `echo $1`'"
# Usage, the directory will be created undex ~/userXY/
mkdir_cluster directory_name
```
- Launching spark-shell
Once you are connected to the cluster (using `cluster` command, freshly aliased), run the spark interactive shell using:
``` bash
spark-shell
```
---
# Spark using Scala overview
1. [Here is a quick introduction, great for a rapid hands on](https://dzone.com/refcardz/apache-spark)
1. [Here is the full documentation of Spark 2.2](https://spark.apache.org/docs/latest/rdd-programming-guide.html)
## Example
``` scala
// Map example
val rdd = sc.parallelize(List("Hello Joe", "How are you"))
val fm = rdd.map(_.split(" "))
fm.collect()
// Result:
// Array[Array[String]] = Array(Array(Hello, Joe), Array(How, are, you))

// FlatMap example, it returns a Seq instead of a value
val rdd = sc.parallelize(List("Hello Joe", "How are you"))
val fm = rdd.flatMap(str=>str.split(" "))
fm.collect()
// Result:
// Array[String] = Array(Hello, Joe, How, are, you)

// Loading a file from the cluster
val orders = sc.textFile("file:///home/cluster/felfassi/text.txt")

val mappers_output = text.flatMap(x => x.split("\\W+")).lower.filter(x => x.matches("[A-Za-z]+") && x.length > 2).map(x => (x,1))

val reduced_count = mappers_output.reduceByKey((a,b) => a + b)

/* Use collect() to trigger computations (transformations are lazily evaluated),
it will returns a value back to the Master node */
reduced_count.collect
```
