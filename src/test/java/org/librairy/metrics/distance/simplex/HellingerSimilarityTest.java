/*
 * Copyright (c) 2017. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance.simplex;

import com.google.common.primitives.Doubles;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class HellingerSimilarityTest {

    private static final Logger LOG = LoggerFactory.getLogger(HellingerSimilarityTest.class);

    @Test
    public void emptyVector(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.0, 0.0, 0.0};

        Hellinger hellinger = new Hellinger();
        double result = hellinger.similarity(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = hellinger.distance(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("distance: " + div);

        Assert.assertEquals(0.29289321881345254, result, 0.29289321881345254);

    }

    @Test
    public void equal(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.3, 0.5, 0.2};

        Hellinger hellinger = new Hellinger();
        double result = hellinger.similarity(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = hellinger.distance(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("distance: " + div);

        Assert.assertEquals(1.0, result, 0.0);

    }

    @Test
    public void difference(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};

        double[] v2 = new double[]{0.2, 0.4, 0.2};
        Hellinger hellinger = new Hellinger();
        double result2 = hellinger.similarity(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("similarity: " + result2);

        double[] v3 = new double[]{0.3, 0.4, 0.2};
        double result3 = hellinger.similarity(Doubles.asList(v1), Doubles.asList(v3));
        LOG.info("similarity: " + result3);

        Assert.assertTrue(result3 > result2);


    }
}
