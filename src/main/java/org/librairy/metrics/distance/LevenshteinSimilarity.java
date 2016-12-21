/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class LevenshteinSimilarity implements Serializable, SimilarityMeasure<String> {

    @Override
    public Double between(String str1, String str2) {
        Instant a = Instant.now();
        int distance        = LevenshteinDistance.computeLevenshteinDistance(str1, str2);
        Double similarity   = 1.0 - (Integer.valueOf(distance).doubleValue() / Math.max(Integer.valueOf(str1.length()
        ).doubleValue(), Integer.valueOf(str2.length()).doubleValue()));
        Instant b = Instant.now();
        System.out.println("Levenhestein elapsed time: " + Duration.between(a,b).toMillis() + " msecs");
        return similarity;
    }
}