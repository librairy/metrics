/*
 * Copyright (c) 2017. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import org.librairy.metrics.data.Ranking;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public interface RankingSimilarityMetric<T> {

    Double calculate(Ranking<T> r1, Ranking<T> r2);
}
