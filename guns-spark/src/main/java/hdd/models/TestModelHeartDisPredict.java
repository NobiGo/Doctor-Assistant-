package hdd.models;

import hdd.models.mappers.TestDataToFeatureVectorMapper;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;

import java.util.List;

/*
 * Usage : TestModelHeartDisPredict <testDataLoc> <path-to-model>
 */
public class TestModelHeartDisPredict {
    /*
     * Testing the model to predict on test data
     *
     */
    public static void main(String[] args) {
//		if(args.length < 2) throw new RuntimeException(" Please Use : TestModelHeartDisPredict <testDataLoc> <path-to-model>");

        String testDataLoc = "hdfs://192.168.71.130:9000/hdfs/data/heart_dis_test_data.csv";
        String modelStorageLoc = "hdfs://192.168.71.130:9000/hdfs/model/";
//        //测试数据地址
//        String testDataLoc = args[0];
//        //模型地址
//        String modelStorageLoc = args[1];

        System.out.println("Building the spark context and conf");
        SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
        JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");
//		JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");

        JavaRDD<String> dsLines = jctx.textFile(testDataLoc);
        JavaRDD<Vector> fRdd = dsLines.map(new TestDataToFeatureVectorMapper());

        NaiveBayesModel _model = NaiveBayesModel.load(jctx.sc(), modelStorageLoc);

        JavaRDD<Double> predictedResults = _model.predict(fRdd);
        List<Double> prl = predictedResults.collect();
        for (Double pr : prl) {
            System.out.println("Predicted Value : " + pr);
        }
    }

}
