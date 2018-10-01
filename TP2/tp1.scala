// data
val cust_file = sc.textFile("hdfs:///home/hadoop/Customer.txt")
val order_file = sc.textFile("hdfs:///Order.txt")

/* q1:
SELECT name FROM Customer WHERE month(startDate)=7
*/
val names = cust_file.filter(_.contains("/07/")).map(_.split(",")(2)).collect

/* q2:
SELECT DISTINCT name FROM Customer WHERE month(startDate) = 7
*/
// In order to print only the first name we can add another split within the same map
val names = cust_file.filter(_.contains("/07/")).map(_.split(",")(2).split(" ")(0)).distinct.collect

/* q3:
SELECT O.cid, SUM(total), COUNT(DISTINCT total) FROM Order O GROUP BY O.cid
*/
// An array can't be reduced, we need to define the key and the value of the RDD,
// here the key is cid (1st col) and the value is total (2nd col)
val total_per_cust = order_file.map(_.split(",")).map(a => (a(0), a(1).toInt)).reduceByKey((a, b) => a + b)

// And here we count the number of unique record per cid:
// First I create an RDD where the key is the pair (cid, total), and the value 1
val cid_total = order_file.map(_.split(",")).map(a => ((a(0), a(1)), 1)).distinct
// This way we can sum the unique totals associated with the key - the map extract the cid as a key from the pair (cid, total), and the 1 as a value.
val count_cid_total = cid_total.map(a => (a._1._1, a._2)).reduceByKey((a, b) => a + b)
// We join the two RDDs in order to have the cid, sum(total), count(distinct total)
val total_count_per_cust = total_per_cust.join(count_cid_total).collect

/* q4:
SELECT C.cid, O.total FROM Customer C, Order O WHERE C.name LIKE ‘O%’ and C.cid=O.cid
*/
// After filtering the names starting with 'O',
// I create a key value pair with the key: cid and value: name in order to use joins
// because the two RDD must have the same signature: [String, String]
val cust_map = cust_file.map(_.split(",")).filter(x => x(2).startsWith("O")).map(a => (a(0), a(2))).distinct
val order_map = order_file.map(_.split(",")).map(a => (a(0), a(1)))
// We join the two RDD on the key
val cust_orders = cust_map.join(order_map)
cust_orders.collect()

/* q5:
SELECT C.cid, O.total FROM Customer C LEFT OUTER JOIN ON Order O ON C.cid=O.cid WHERE C.name LIKE ‘O%’ and C.cid=O.cid
*/
val cust_map = cust_file.map(_.split(",")).filter(x => x(2).startsWith("O")).map(a => (a(0), a(2))).distinct
val order_map = order_file.map(_.split(",")).map(a => (a(0), a(1)))
val cust_orders = cust_map.leftOuterJoin(order_map)
cust_orders.foreach(println(_))

