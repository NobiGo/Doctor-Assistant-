package hdd.models.mappers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;


public class TestDataToFeatureVectorMapper implements Function<String, Vector> {

    public Vector call(String arg0) throws Exception {
        String[] tokens = arg0.split(",");
        double[] darr = new double[13];
        for (int i = 0; i < 12; i++) {
            darr[i] = Double.parseDouble(tokens[i]);
        }

        Vector fv = new DenseVector(darr);
        return fv;
    }

}
