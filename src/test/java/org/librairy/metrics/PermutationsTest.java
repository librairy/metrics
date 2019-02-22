/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.librairy.metrics.Permutations;
import org.librairy.metrics.data.Pair;

import java.util.Set;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class PermutationsTest {

    @Test
    public void combinateTest(){

        Set<Pair<String>> result = new Permutations<String>().sorted(Lists.newArrayList("a", "b", "c", "d", "e"));

        System.out.println(result);

        Set<Pair<String>> reference = Sets.newHashSet(
                new Pair("a","a"),
                new Pair("a","b"),
                new Pair("a","c"),
                new Pair("a","d"),
                new Pair("a","e"),
                new Pair("b","b"),
                new Pair("b","c"),
                new Pair("b","d"),
                new Pair("b","e"),
                new Pair("c","c"),
                new Pair("c","d"),
                new Pair("c","e"),
                new Pair("d","d"),
                new Pair("d","e")
        );

        Assert.assertEquals(reference,result);

    }

}
