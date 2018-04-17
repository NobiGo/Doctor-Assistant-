package hdd.models;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkConfAndCtxBuilder {
    private JavaSparkContext sCtx;

    public SparkConfAndCtxBuilder() {

    }

    /**
     * //模型在本地可以成功生成
     * public JavaSparkContext loadSimpleSparkContext(String appName, String appType) {
     * SparkConf conf = new SparkConf().setAppName(appName).setMaster(appType).set("spark.sql.warehouse.dir", "file:///");
     * sCtx = new JavaSparkContext(conf);
     * return sCtx;
     * }
     **/

    /**
     * //模型可以保存在HDFS上并进行调用
     * public JavaSparkContext loadSimpleSparkContext(String appName, String appType) {
     * SparkConf conf = new SparkConf().setAppName(appName).setMaster(appType).set("spark.sql.warehouse.dir", "file:///");
     * sCtx = new JavaSparkContext(conf);
     * return sCtx;
     * }
     **/
    public JavaSparkContext loadSimpleSparkContext(String appName, String appType) {
        String[] value = {"C:\\Users\\dx\\Desktop\\HrtDisDetection\\classes\\artifacts"};
//      SparkConf conf = new SparkConf().setAppName(appName).setMaster(appType).set("spark.sql.warehouse.dir", "hdfs://192.168.71.130:9000/hdfs").setJars(value);
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(appType).set("spark.sql.warehouse.dir", "file:///").set("spark.jars", "file:///D:/bishe/Doctor-Assistant/out/artifacts/guns_spark_jar/guns-spark.jar");
        sCtx = new JavaSparkContext(conf);
        return sCtx;
    }

    public void closeCtx() {
        sCtx.close();
    }
}
