package org.apache.spark.spark_streaming_kafka_0_10_2;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;

public class SparkSQLUtility {

	private HiveContext hsqlContext;
	

	public SparkSQLUtility(JavaSparkContext sc) {
        hsqlContext = new org.apache.spark.sql.hive.HiveContext(sc);
        //hsqlContext.setConf("hive.metastore.warehouse.dir", "/Users/sunshuhan/Documents/mum/course/BDT/BDT-spark/SparkSqlProject/spark-warehouse");
    }
	
	public void loadData(String TWITTER_JSON_PATH_100){
	
        createTable();
        loadDataLocal(TWITTER_JSON_PATH_100);
	}
	
    public void createTable() {
        hsqlContext.sql("CREATE TABLE IF NOT EXISTS twitter " +
                "(id_str String, " +
                "created_at String, " +
                "user_id String, " +
                "user_name String, " +
                "user_location String, " +
                "text String) " +
//                "PARTITIONED BY (id_str STRING) " +
                "ROW FORMAT DELIMITED FIELDS TERMINATED BY ';' LINES TERMINATED BY '\\n'");
    }
    
    public void loadDataLocal(String path) {
        hsqlContext.sql("LOAD DATA LOCAL INPATH '" + path + "' INTO TABLE twitter");
    }
}
