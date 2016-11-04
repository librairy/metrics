/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import org.librairy.metrics.data.Ranking;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class RankingDistance<T> {


    public Double calculate(Ranking<T> r1, Ranking<T> r2) {

        Integer n = r1.getElements().size();

        Double maxValue = Math.pow(n,2);

        Double aggregatedCost = 0.0;
        for (T element: r1.getElements()){

            if (!r2.exist(element)) {
                aggregatedCost += 0.0;
                continue;
            }

            Integer k = r1.getPosition(element);
            Integer j = r2.getPosition(element);

            aggregatedCost += n-Math.abs(k-j);
        }

        double similarity = aggregatedCost / maxValue;

        return 1-similarity;

    }

}