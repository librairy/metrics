/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.librairy.metrics.data.Pair;
import org.librairy.metrics.data.Ranking;
import org.librairy.metrics.utils.Permutations;

import java.util.Set;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class RankingDistanceTest {


    @Test
    public void permutations(){

        Ranking r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);
        Ranking r2    = new Ranking().add("a",1.0).add("c",1.0).add("b",1.0);
        Ranking r3    = new Ranking().add("b",1.0).add("a",1.0).add("c",1.0);
        Ranking r4    = new Ranking().add("b",1.0).add("c",1.0).add("a",1.0);
        Ranking r5    = new Ranking().add("c",1.0).add("a",1.0).add("b",1.0);
        Ranking r6    = new Ranking().add("c",1.0).add("b",1.0).add("a",1.0);

        Set<Pair<Ranking>> pairs = new Permutations<Ranking>().sorted(Lists.newArrayList(r1, r2, r3, r4, r5, r6));

        RankingDistance<String> wordsRankMeasure = new RankingDistance<>();

        for (Pair<Ranking> pair : pairs){

            Double distance = wordsRankMeasure.calculate(pair.getI(),pair.getJ());
            System.out.println("Distance between " + pair + " = " + distance);

        }
    }

    @Test
    public void minimum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);

        Double distance = new RankingDistance<String>().calculate(r1, r1);

        Assert.assertEquals(Double.valueOf(0.0),distance);
    }


    @Test
    public void maximum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);
        Ranking<String> r2    = new Ranking().add("d",1.0).add("e",1.0).add("f",1.0);

        Double distance = new RankingDistance<String>().calculate(r1, r2);

        Assert.assertEquals(Double.valueOf(1.0),distance);
    }


}
