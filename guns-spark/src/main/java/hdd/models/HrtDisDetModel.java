package hdd.models;

import hdd.HdfsUtils.HdfsUtils;
import hdd.models.mappers.DataToModelAdapterMapper;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.junit.Test;

/*
 * Heart Dis Detection model
 *
 * This class uses a Navie Bayes model and stores it.
 * Uses the UCI Heart Disease Dataset for cleveland with 14 attributes
 *
 */
public class HrtDisDetModel {

    public HrtDisDetModel() {

    }

    /*
     * Usage : HrtDisDetModel <training-data-path> <path-to-store-generated-model>
     */


    /**
     * public static void main(String[] args) {
     * //		if(args.length < 2) throw new RuntimeException(" Please Use : HrtDisDetModel <training-data-path> <path-to-store-generated-model>");
     * <p>
     * //		 模型在本地可以成功生成
     * <p>
     * args[0] = "file:///C:/Users/dx/Desktop/HrtDisDetection/src/resources/full_data_cleaned.csv";
     * args[1] = "file:///C:/Users/dx/Desktop/HrtDisDetection/src/resources/model/";
     * String trainDataLoc = args[0];
     * String modelStorageLoc = args[1];
     * <p>
     * System.out.println("Building the spark context and conf");
     * SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
     * JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");
     * <p>
     * JavaRDD<String> dsLines = jctx.textFile(trainDataLoc);
     * JavaRDD<LabeledPoint> _modelTrainData = dsLines.map(new DataToModelAdapterMapper());
     * <p>
     * NaiveBayesModel _model = NaiveBayes.train(_modelTrainData.rdd());
     * _model.save(jctx.sc(), modelStorageLoc);
     * <p>
     * ctxBuilder.closeCtx();
     * }
     **/


    /**
     * //模型可以保存在HDFS上，并进行测试
     * public static void main(String[] args) {
     * //		if(args.length < 2) throw new RuntimeException(" Please Use : HrtDisDetModel <training-data-path> <path-to-store-generated-model>");
     * args[0] = "hdfs://192.168.71.130:9000/hdfs/data/heart_dis_training_data.csv";
     * args[1] = "hdfs://192.168.71.130:9000/hdfs/model/";
     * //若文件已存在，则删除
     * try {
     * new HdfsUtils().deleteFile(args[1]);
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * String trainDataLoc = args[0];
     * String modelStorageLoc = args[1];
     * <p>
     * System.out.println("Building the spark context and conf");
     * SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
     * JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");
     * <p>
     * JavaRDD<String> dsLines = jctx.textFile(trainDataLoc);
     * JavaRDD<LabeledPoint> _modelTrainData = dsLines.map(new DataToModelAdapterMapper());
     * <p>
     * NaiveBayesModel _model = NaiveBayes.train(_modelTrainData.rdd());
     * _model.save(jctx.sc(), modelStorageLoc);
     * <p>
     * ctxBuilder.closeCtx();
     * }
     **/
    @Test
    public  String   NaiveBayesModelFunction() {
        //		if(args.length < 2) throw new RuntimeException(" Please Use : HrtDisDetModel <training-data-path> <path-to-store-generated-model>");
        String[] args = new String[2];
        args[0] = "hdfs://192.168.71.130:9000/hdfs/data/heart_dis_training_data.csv";
        args[1] = "hdfs://192.168.71.130:9000/hdfs/model/";
        //若文件已存在，则删除
        try {
            new HdfsUtils().deleteFile(args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String trainDataLoc = args[0];
        String modelStorageLoc = args[1];

        System.out.println("Building the spark context and conf");
        SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
        JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "spark://192.168.71.130:7077");

        JavaRDD<String> dsLines = jctx.textFile(trainDataLoc);
        JavaRDD<LabeledPoint> _modelTrainData = dsLines.map(new DataToModelAdapterMapper());

        NaiveBayesModel _model = NaiveBayes.train(_modelTrainData.rdd());
        _model.save(jctx.sc(), modelStorageLoc);
        ctxBuilder.closeCtx();
       return "NaiveBayesModel - 模型训练成功";
    }
}
