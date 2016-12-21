/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import com.google.common.util.concurrent.AtomicDouble;
import org.librairy.metrics.data.Pair;
import org.librairy.metrics.data.Ranking;
import org.librairy.metrics.utils.Permutations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


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

        return 1 + new ExtendedKendallsTauDistance<T>().calculate(r1,r2,similarityMeasure);

    }
}
