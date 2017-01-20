/*
 * Copyright (c) 2017. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.similarity;

import org.junit.Assert;
import org.junit.Test;
import org.librairy.metrics.distance.JensenShannonDivergence;
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

        double result = JensenShannonSimilarity.apply(v1, v2);

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = JensenShannonDivergence.apply(v1, v2);
        LOG.info("divergence: " + div);

        Assert.assertEquals(0.0, result, 0.0);

    }

    @Test
    public void equal(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};
        double[] v2 = new double[]{0.3, 0.5, 0.2};

        double result = JensenShannonSimilarity.apply(v1, v2);

        LOG.info("v1: " + v1);
        LOG.info("v2: " + v2);
        LOG.info("similarity: " + result);

        double div = JensenShannonDivergence.apply(v1, v2);
        LOG.info("divergence: " + div);

        Assert.assertEquals(1.0, result, 0.0);

    }

    @Test
    public void difference(){

        double[] v1 = new double[]{0.3, 0.5, 0.2};

        double[] v2 = new double[]{0.2, 0.4, 0.2};
        double result2 = JensenShannonSimilarity.apply(v1, v2);
        LOG.info("similarity: " + result2);

        double[] v3 = new double[]{0.3, 0.4, 0.2};
        double result3 = JensenShannonSimilarity.apply(v1, v3);
        LOG.info("similarity: " + result3);

        Assert.assertTrue(result3 > result2);


    }
}
