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
public class JensenShannonSimilarityTest {

    private static final Logger LOG = LoggerFactory.getLogger(JensenShannonSimilarityTest.class);

    @Test
    public void emptyVector(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.0, 0.0, 0.0};

        JSD jsd = new JSD();
        double result = jsd.similarity(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = jsd.distance(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("divergence: " + div);

        Assert.assertEquals(0.6534264097200273, result, 0.6534264097200273);

    }

    @Test
    public void equal(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.3, 0.5, 0.2};

        JSD jsd = new JSD();
        double result = jsd.similarity(Doubles.asList(v1), Doubles.asList(v2));

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = jsd.distance(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("divergence: " + div);

        Assert.assertEquals(1.0, result, 0.0);

    }

    @Test
    public void difference(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};

        double[] v2 = new double[]{0.2, 0.4, 0.2};
        JSD jsd = new JSD();
        double result2 = jsd.similarity(Doubles.asList(v1), Doubles.asList(v2));
        LOG.info("similarity: " + result2);

        double[] v3 = new double[]{0.3, 0.4, 0.2};
        double result3 = jsd.similarity(Doubles.asList(v1), Doubles.asList(v3));
        LOG.info("similarity: " + result3);

        Assert.assertTrue(result3 > result2);


    }
}
