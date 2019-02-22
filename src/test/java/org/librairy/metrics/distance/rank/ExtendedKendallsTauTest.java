/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance.rank;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.librairy.metrics.Permutations;
import org.librairy.metrics.data.Pair;
import org.librairy.metrics.data.Ranking;
import org.librairy.metrics.distance.SimilarityMeasure;
import org.librairy.metrics.distance.text.Levenshtein;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class ExtendedKendallsTauTest {

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

        ExtendedKendallsTau<String> wordsRankMeasure = new ExtendedKendallsTau<>(similarityMeasure);

        for (Pair<Ranking> pair : pairs){

            Double distance = wordsRankMeasure.distance(pair.getI(),pair.getJ());
            System.out.println("Distance similarity " + pair + " = " + distance);

        }
    }

    @Test
    public void minimum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);

        Double distance = new ExtendedKendallsTau<String>((w1, w2) -> 1.0).distance(r1, r1);

        Assert.assertEquals(Double.valueOf(0.0),distance);
    }


    @Test
    public void maximum(){

        Ranking<String> r1    = new Ranking().add("a",1.0).add("b",1.0).add("c",1.0);
        Ranking<String> r2    = new Ranking().add("d",1.0).add("e",1.0).add("f",1.0);

        Double distance = new ExtendedKendallsTau<String>((w1, w2) -> (w1.equals(w2)? 1.0 : 0.0)).distance(r1, r2);

        Assert.assertEquals(Double.valueOf(1.0),distance);
    }

    @Test
    public void testTime(){

        Random random = new Random();

        Ranking<String> r1 = new Ranking<>();
        Ranking<String> r2 = new Ranking<>();

        IntStream.range(0,100).forEach(i ->{
            r1.add("asdasd"+random.nextInt() , random.nextDouble());
            r2.add("asdasd"+random.nextInt(), random.nextDouble());
        });

        Instant a = Instant.now();
        Double distance = new ExtendedKendallsTau<String>(new Levenshtein()).distance(r1, r2);
        Instant b = Instant.now();
        System.out.println("distance: " + distance);
        System.out.println("elapsed time: " + Duration.between(a,b).toMillis() + " msecs");

    }

    @Test
    public void sample(){

        Ranking r1    = new Ranking()
                .add("cell",0.00331400711188639)
                .add("content",0.0015733913531514457)
                .add("mouse",0.0012848762840690605)
                .add("west",9.359398354193688E-4)
                .add("antigen",8.371448195540803E-4)
                .add("prod",8.014036195613543E-4)
                .add("amazonaws",7.728747000382398E-4)
                .add("store",7.566799842029915E-4)
                .add("image",7.193596445638932E-4)
                .add("expression",7.074286713122537E-4)
                ;
        Ranking r2    = new Ranking()
                .add("s0264999316304941",0.018418884835828077)
                .add("s0264999316301079",0.011634094651950934)
                .add("s0264999313001429",0.011064523790205583)
                .add("s0264999312003628",0.008795251049155456)
                .add("politician",8.752760484520667E-4)
                .add("gifgif322147altimg1",6.745127351755857E-4)
                .add("content",5.580198364837636E-4)
                .add("rate",5.265150156554328E-4)
                .add("store",5.053231727756484E-4)
                ;


        Double score = new ExtendedKendallsTau<String>(new Levenshtein()).distance(r1, r2);

        System.out.println("Score: " + score);

    }

}
