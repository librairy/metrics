/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class PairTest {


    @Test
    public void checkEquals(){

        Pair p1 = new Pair("a","b");
        Pair p2 = new Pair("b","a");

        Assert.assertEquals(p2,p1);

    }

    @Test
    public void checkHashCode(){

        Pair p1 = new Pair("a","b");
        Pair p2 = new Pair("b","a");

        Assert.assertEquals(p2.hashCode(),p1.hashCode());

    }

    @Test
    public void addElement(){

        Set<Pair> pairs = new HashSet<>();

        pairs.add(new Pair("a","b"));
        pairs.add(new Pair("b","a"));

        Assert.assertEquals(1,pairs.size());

    }
}
