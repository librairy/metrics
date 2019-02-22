package org.librairy.metrics.distance.simplex;

import com.google.common.primitives.Doubles;
import org.librairy.metrics.distance.DistanceMeasure;
import org.librairy.metrics.distance.SimilarityMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */

public class JSD implements SimilarityMeasure<List<Double>>, DistanceMeasure<List<Double>> {

    private static final Logger LOG = LoggerFactory.getLogger(JSD.class);

    @Override
    public Double distance(List<Double> v1, List<Double> v2) {
        KL kl = new KL();
        assert(v1.size() == v2.size());
        List<Double> average = new ArrayList<>();
        for (int i = 0; i < v1.size(); ++i) {
            average.add((v1.get(i) + v2.get(i))/2);
        }
        return (kl.distance(v1, average) + kl.distance(v2, average))/2;
    }

    @Override
    public Double similarity(List<Double> v1, List<Double> v2) {
        return 1-distance(v1,v2);
    }

    public static void main(String[] args) {
        List<Double> v1 = Doubles.asList(0.4,0.3,0.3);
        List<Double> v2 = Doubles.asList(0.3,0.3,0.4);
        LOG.info(""+new JSD().similarity(v1,v2));
    }

}
