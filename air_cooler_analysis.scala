import org.apache.spark.SparkContext;

import org.apache.spark.SparkConf;

object air_cooler_analysis {

  def main(args:Array[String]) {

    val sparkConf = new SparkConf().setAppName("Air_Cooler_analysis").setMaster("local")

    val sc = new SparkContext(sparkConf)

/* 

   Use case 1:- 

Find out the countries where buildings fitted with the air coolers manufactured by the company are giving a delta temperature(Actual temperature - Temperature set) is greater than 5 

*/


 val buildingRDD = sc.textFile("/home/hadoop/Learnbay/Spark/building.csv")

 val hvacRDD = sc.textFile("/home/hadoop/Learnbay/Spark/HVAC.csv")

 val build_header = buildingRDD.first()

 val hvac_header = hvacRDD.first()

 val build_fnl = buildingRDD.filter(x=>x!=build_header).map(x=>x.split(",")).map(x=>(x(0),x(4)))

 val hvac_fnl = hvacRDD.filter(x=>x!=hvac_header).map(x=>x.split(",")).map(x=>(x(6),x(2).toInt-x(3).toInt)).filter(x=>x._2>5)

 val join = build_fnl.join(hvac_fnl).groupBy(x=>x._2._1)

 val counts = join.map(x=>(x._1,x._2.toList.length)).sortBy(x=>x._2).take(2)

 sc.parallelize(Seq(counts)).saveAsTextFile("/home/hadoop/Learnbay/Spark/countries_greater_than_5degree")


/* 

   Use case 2:- 

Find out the countries where buildings fitted with the air coolers manufactured by the company are giving a delta temperature(Actual temperature - Temperature set) is lesser than 5 

*/




 val hvac_fnl_case2 = hvacRDD.filter(x=>x!=hvac_header).map(x=>x.split(",")).map(x=>(x(6),x(2).toInt-x(3).toInt)).filter(x=>x._2<5)

 val join_case2 = build_fnl.join(hvac_fnl_case2).groupBy(x=>x._2._1)

 val counts_case2 = join_case2.map(x=>(x._1,x._2.toList.length)).sortBy(x=>x._2).take(2)

 sc.parallelize(Seq(counts_case2)).saveAsTextFile("/home/hadoop/Learnbay/Spark/countries_lesser_than_5degree")

}

}
