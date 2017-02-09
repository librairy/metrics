/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import org.librairy.metrics.data.Ranking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 *   Based on:
 *   Kumar, R., & Vassilvitskii, S. (2010). Generalized distances between rankings.
 *   Proceedings of the 19th International Conference on World Wide Web - WWW ’10,
 *   (3), 571. http://doi.org/10.1145/1772690.1772749
 *
 */
public class ExtendedKendallsTauSimilarity<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExtendedKendallsTauSimilarity.class);

    public Double calculate(Ranking<T> r1, Ranking<T> r2, SimilarityMeasure<T> similarityMeasure){

        Double distance = new ExtendedKendallsTauDistance<T>().calculate(r1,r2,similarityMeasure);
        double score = Math.abs(distance - 1);
        return score;

    }
}
