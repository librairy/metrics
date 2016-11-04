/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import org.librairy.metrics.data.Pair;
import org.librairy.metrics.data.Ranking;
import org.librairy.metrics.utils.Permutations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 *   Based on:
 *   Kumar, R., & Vassilvitskii, S. (2010). Generalized distances between rankings.
 *   Proceedings of the 19th International Conference on World Wide Web - WWW ’10,
 *   (3), 571. http://doi.org/10.1145/1772690.1772749
 *
 */
public class ExtendedKendallsTauDistance<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExtendedKendallsTauDistance.class);

    public Double calculate(Ranking<T> r1, Ranking<T> r2, SimilarityMeasure<T> similarityMeasure){

        Double distance = 0.0;

        Set<Pair<T>> elements = new Permutations<T>().between(r1.getElements(), r2.getElements());

        for (Pair<T> pair: elements){

            T i = pair.getI();
            T j = pair.getJ();

            if (!r1.exist(i) || !r1.exist(j) || !r2.exist(i) || !r2.exist(j)) {
                distance += 1.0;
                continue;
            }

            Integer d1 = r1.getPosition(j)-r1.getPosition(i);
            Integer d2 = r2.getPosition(j)-r2.getPosition(i);

            // check pairwise inversions
            if ((d1*d2) < 0 ){

                LOG.debug("Pairwise inversion: " + pair);

                // Element Weights
                Double wi   = elementWeightOf(i, r1, r2);
                Double wj   = elementWeightOf(j, r1, r2);
                LOG.debug("\t -> Element Weight:: [wi=" + wi +",wj=" + wj+"] = " + (wi*wj));

                // Position weights
                Double pi   = costOfMoving(i, r1, r2);
                Double pj   = costOfMoving(j, r1, r2);
                LOG.debug("\t -> Position Weight:: [pi= " + pi + ",pj="+pj+"] = " + (pi*pj));

                // Element Similarities
                Double Dij  = similarityMeasure.between(i,j);
                Double sij  = 1.0 - Dij;
                LOG.debug("\t -> Element Similarity = " + Dij);

                distance += (wi+wj+pi+pj+sij)/5.0;
            }

        }

        return distance/elements.size();
    }


    private Double elementWeightOf(T element, Ranking r1, Ranking r2){
        return (r1.getWeight(element)+r2.getWeight(element))/2.0;
    }

    private Double costOfMoving(T element, Ranking r1, Ranking r2){

        Integer i           = r1.getPosition(element);
        Integer sigmai      = r2.getPosition(element);

        if (i == sigmai) return 0.0;


        Integer maxDisplacement = r1.getElements().size();
        Double pi           = positionWeightOf(i,maxDisplacement);
        Double psigmai      = positionWeightOf(sigmai,maxDisplacement);

        return Math.abs(pi-psigmai);
    }

    private static Double positionWeightOf(Integer position, Integer maxDisplacement){
        if (position == 1) return Double.valueOf(maxDisplacement-1);

        Double accumulatedCost = 0.0;

        for (int j = 1; j<=(position-1); j++){
            accumulatedCost += swapCostOf(j,maxDisplacement);
        }

        return accumulatedCost;
    }

    private static Double swapCostOf(Integer j, Integer maxDisplacement){
        return ((maxDisplacement-j)/2.0);
    }

}
