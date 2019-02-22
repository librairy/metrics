package org.librairy.metrics.distance.simplex;

import org.librairy.metrics.distance.DistanceMeasure;
import org.librairy.metrics.distance.SimilarityMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */

public class Hellinger implements SimilarityMeasure<List<Double>>, DistanceMeasure<List<Double>> {

    private static final Logger LOG = LoggerFactory.getLogger(Hellinger.class);

    @Override
    public Double distance(List<Double> v1, List<Double> v2) {

        assert (v1.size() == v2.size());

        double sum = 0;
        for(int i=0; i<v1.size(); i++){

            double sqrtv1 = Math.sqrt(v1.get(i));
            double sqrtv2 = Math.sqrt(v2.get(i));

            double pow2 = Math.pow(sqrtv1 - sqrtv2, 2.0);
            sum += pow2;
        }

        double sqrtSum = Math.sqrt(sum);
        double multiplier = 1.0 / Math.sqrt(2.0);
        return multiplier*sqrtSum;
    }

    @Override
    public Double similarity(List<Double> v1, List<Double> v2) {
        return 1-distance(v1,v2);
    }
}
