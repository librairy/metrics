package org.librairy.metrics.data;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */

public class EvaluationTest {

    private static final Logger LOG = LoggerFactory.getLogger(EvaluationTest.class);

    @Test
    public void q1(){

        List<String> reference  = Arrays.asList("A","E","I","O","U");

        List<String> values     = Arrays.asList("1","A","3","4","E","6","7","8","9","10");

        Evaluation evaluation = new Evaluation(reference, values);


        Double p = evaluation.getPrecision();
        LOG.info("Precision: " + p);
        Assert.assertEquals(Double.valueOf(0.2),p);

        Double ap = evaluation.getAveragedPrecision();
        LOG.info("AveragedPrecision: " + ap);
        Assert.assertEquals(Double.valueOf(0.45),ap);


    }

    @Test
    public void q2(){

        List<String> reference  = Arrays.asList("A","E","I","O","U");

        List<String> values     = Arrays.asList("A","A","E","4","E","6","7","8","9","10");

        Evaluation evaluation = new Evaluation(reference, values);


        Double p = evaluation.getPrecision();
        LOG.info("Precision: " + p);
        Assert.assertEquals(Double.valueOf(0.4),p);

        Double ap = evaluation.getAveragedPrecision();
        LOG.info("AveragedPrecision: " + ap);
        Assert.assertEquals(Double.valueOf(0.95),ap);


    }

}
