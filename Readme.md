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
1. A great book for learning Spark fundamentals [Learning Spark - Matei Zaharia, Patrick Wendell, Andy Konwinski, Holden Karau ](https://www.safaribooksonline.com/library/view/learning-spark/9781449359034/)
1. [Documentation of Spark 2.2](https://spark.apache.org/docs/latest/rdd-programming-guide.html)
## Example
``` scala
// Map example
val rdd = sc.parallelize(List("Hello Joe", "How are you"))
val m = rdd.map(_.split(" "))
m.collect()
// Result:
// Array[Array[String]] = Array(Array(Hello, Joe), Array(How, are, you))

// FlatMap example, it returns a Seq instead of a value
val rdd = sc.parallelize(List("Hello Joe", "How are you"))
val fm = rdd.flatMap(str=>str.split(" "))
fm.collect()
// Result:
// Array[String] = Array(Hello, Joe, How, are, you)

// Loading a file from the cluster
val text = sc.textFile("file:///home/cluster/felfassi/text.txt")

val mappers_output = text.flatMap(x => x.split("\\W+")).filter(x => x.matches("[A-Za-z]+") && x.length > 2).map(x => (x.toLowerCase,1))

val reduced_count = mappers_output.reduceByKey((a,b) => a + b)

/* Use collect() to trigger computations (transformations are lazily evaluated),
it will returns a value back to the Master node */
reduced_count.collect
// Result:
// Array[(String, Int)] = Array((intimately,2), (someone,2), (disregarded,1), (bone,17), (cosmopolitan,1), (shot,6), (dando,3), (felted,1), (envelope,5), (order,27), (apprehension,2), (chapters,2), (bromogelatine,1), (spirited,1), (behind,18), (preventing,2), (pigeon,14), (auroral,1), (been,264), (fuller,3), (spice,1), (unveiled,2), (gap,2), (accomplished,2), (flier,2), (morley,1), (ducklings,1), (semites,1), (pendent,1), (knows,11), (fowl,2), (substitute,1), (mantis,3), (camouflaged,1), (dive,2), (catcher,1), (tune,1), (tips,2), (are,956), (revelations,4), (hurled,2), (smooth,4), (consists,15), (shut,3), (luminous,15), (exhibited,2), (islands,8), (shower,3), (tuberculosis,1), (rubs,1), (infer,1), (discriminate,6), (audibly,1), (swamp,4), (robin,1), (dentition,2), (records,5), (erec...
```
