package org.librairy.metrics.distance.simplex;

import org.librairy.metrics.distance.DistanceMeasure;
import org.librairy.metrics.distance.SimilarityMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */

public class S2JSD implements SimilarityMeasure<List<Double>>, DistanceMeasure<List<Double>> {

    private static final Logger LOG = LoggerFactory.getLogger(S2JSD.class);

    @Override
    public Double distance(List<Double> v1, List<Double> v2) {
        return Math.sqrt(2.0 * new JSD().distance(v1,v2));
    }

    @Override
    public Double similarity(List<Double> v1, List<Double> v2) {
        return 1-distance(v1,v2);
    }
}
