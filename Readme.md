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
# Scala overview
1. [Here is a quick introduction, great for a rapid hands on](https://dzone.com/refcardz/apache-spark)
1. [Here is the full documentation of Spark 2.2](https://spark.apache.org/docs/latest/rdd-programming-guide.html)
## Example
``` scala
/* Word count example :
- Reading a file copied in your Unix session on the cluster (using the scp alias defined above):
*/
// Loading a file from the cluster
val orders = sc.textFile("file:///home/cluster/felfassi/text.txt")

val mappers_output = text.flatMap(x => x.split("\\W+")).lower.filter(x => x.matches("[A-Za-z]+") && x.length > 2).map(x => (x,1))

val reduced_count = mappers_output.reduceByKey((a,b) => a + b)

// Use collect() to trigger computations (transformations are lazily evaluated), it will returns a value back to the Master node
reduced_count.collect
```
