/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.data;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.stream.Collectors;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
@Data
public class Pair<T>{

    T i;
    T j;

    public Pair(T i, T j){
        this.i = i;
        this.j = j;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Pair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Pair other = (Pair) obj;

        return (other.getI().equals(i) && other.getJ().equals(j))
                || (other.getI().equals(j) && other.getJ().equals(i));
    }

    @Override
    public int hashCode() {
        String value = Lists.newArrayList(i, j).stream().map(el -> el.toString()).sorted((o1,o2)->o1.compareTo(o2)).collect(Collectors.joining(","));

        int hash = 3;
        hash = 53 * hash + value.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "i="+i+"/j="+j;
    }
}
