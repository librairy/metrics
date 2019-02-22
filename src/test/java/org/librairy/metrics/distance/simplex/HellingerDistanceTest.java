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
public class HellingerDistanceTest {

    private static final Logger LOG = LoggerFactory.getLogger(HellingerDistanceTest.class);

    @Test
    public void emptyVector(){


        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.0, 0.0, 0.0};

        Hellinger hellinger = new Hellinger();
        double result = hellinger.distance(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("distance: " + result);

        Assert.assertEquals(0.7071067811865475, result, 0.0);

    }

    @Test
    public void equal(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.3, 0.5, 0.2};

        Hellinger hellinger = new Hellinger();
        double result = hellinger.distance(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("distance: " + result);

        Assert.assertEquals(0.0, result, 0.0);

    }

    @Test
    public void difference(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};

        double[] v2 = new double[]{0.2, 0.4, 0.2};
        Hellinger hellinger = new Hellinger();
        double result2 = hellinger.distance(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("distance: " + result2);

        double[] v3 = new double[]{0.3, 0.4, 0.2};
        double result3 = hellinger.distance(Doubles.asList(v1), Doubles.asList(v3));
        LOG.info("distance: " + result3);

        Assert.assertTrue(result3 < result2);


    }
}
