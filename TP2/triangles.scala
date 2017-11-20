val array = Array((1,4), (2,1), (2,5), (3,2), (4,2), (5,3))
val g = sc.parallelize(array)
// g.collect()
val gInverse = g.map(x => (x._2,x._1))
// Array[(Int, Int)] = Array((4,1), (1,2), (5,2), (2,3), (2,4), (3,5))
val opentriangles= g.join(gInverse)
// res2: Array[(Int, (Int, Int))] = Array((4,(2,1)), (1,(4,2)), (5,(3,2)), (2,(1,3)), (2,(1,4)), (2,(5,3)), (2,(5,4)), (3,(2,5)))

val trianglesToClose = opentriangles.map(x => (x._2 , x))
// trianglesToClose.collect()
// res4: Array[((Int, Int), (Int, (Int, Int)))] = Array(((2,1),(4,(2,1))), ((4,2),(1,(4,2))), ((3,2),(5,(3,2))), ((1,3),(2,(1,3))), ((1,4),(2,(1,4))), ((5,3),(2,(5,3))), ((5,4),(2,(5,4))), ((2,5),(3,(2,5))))
val triangles = trianglesToClose.join(g.map(x => (x, 1))).map(x => (x._2._1, x._2._2))

triangles.count()
