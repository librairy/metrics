/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.librairy.metrics.data.Pair;
import org.librairy.metrics.data.Ranking;
import org.librairy.metrics.utils.Permutations;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class ExtendedKendallsTauDistanceTest {

    @Test
    public void permutations(){

        Ranking r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);
        Ranking r2    = new Ranking().add("a",1.0).add("c",1.0).add("b",1.0);
        Ranking r3    = new Ranking().add("b",1.0).add("a",1.0).add("c",1.0);
        Ranking r4    = new Ranking().add("b",1.0).add("c",1.0).add("a",1.0);
        Ranking r5    = new Ranking().add("c",1.0).add("a",1.0).add("b",1.0);
        Ranking r6    = new Ranking().add("c",1.0).add("b",1.0).add("a",1.0);
        Ranking r7    = new Ranking().add("e",1.0).add("f",1.0).add("g",1.0);


        SimilarityMeasure similarityMeasure = (w1, w2) -> (w1.equals(w2)? 1.0 : 0.0);

        Set<Pair<Ranking>> pairs = new Permutations<Ranking>().sorted(Lists.newArrayList(r1, r2, r3, r4, r5, r6, r7));

        ExtendedKendallsTauDistance<String> wordsRankMeasure = new ExtendedKendallsTauDistance<>();

        for (Pair<Ranking> pair : pairs){

            Double distance = wordsRankMeasure.calculate(pair.getI(),pair.getJ(),similarityMeasure);
            System.out.println("Distance between " + pair + " = " + distance);

        }
    }

    @Test
    public void minimum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);

        Double distance = new ExtendedKendallsTauDistance<String>().calculate(r1, r1, (w1, w2) -> 1.0);

        Assert.assertEquals(Double.valueOf(0.0),distance);
    }


    @Test
    public void maximum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);
        Ranking<String> r2    = new Ranking().add("d",1.0).add("e",1.0).add("f",1.0);

        Double distance = new ExtendedKendallsTauDistance<String>().calculate(r1, r2, (w1, w2) -> (w1.equals(w2)? 1.0 : 0.0));

        Assert.assertEquals(Double.valueOf(1.0),distance);
    }

    @Test
    public void testTime(){

        Random random = new Random();

        Ranking<String> r1 = new Ranking<>();
        Ranking<String> r2 = new Ranking<>();

        IntStream.range(0,100).forEach(i ->{
            r1.add(RandomStringUtils.randomAlphanumeric(10), random.nextDouble());
            r2.add(RandomStringUtils.randomAlphanumeric(10), random.nextDouble());
        });

        Instant a = Instant.now();
        Double distance = new ExtendedKendallsTauDistance<String>().calculate(r1, r2, new LevenshteinSimilarity());
        Instant b = Instant.now();
        System.out.println("distance: " + distance);
        System.out.println("elapsed time: " + Duration.between(a,b).toMillis() + " msecs");

    }

}
