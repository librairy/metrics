/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.utils;

import org.librairy.metrics.data.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class Permutations<T> {

//    public Set<Pair<T>> between(List<T> el1,List<T> el2 ){
//
//        if (el1.isEmpty()) return Collections.emptySet();
//
//        Set<Pair<T>> result = sorted(el1.get(0), el2);
//
//        result.addAll(between(el1.subList(1,el1.size()),el2));
//
//        return result;
//
//    }

    public List<Pair<T>> between(List<T> el1,List<T> el2 ){
        if (el1.isEmpty() || el2.isEmpty()) return Collections.emptyList();
        return el1.stream().flatMap(el -> combine(el, el2)).collect(Collectors.toList());
    }

    private Stream<Pair<T>> combine(T el, List<T> list){
        return list.stream().map(r -> new Pair<T>(el,r));
    }


    public Set<Pair<T>> sorted(List<T> elements){

        if (elements.isEmpty() || elements.size() == 1) return Collections.emptySet();

        //Set<Pair<T>> result = sorted(elements.get(0), elements.subList(1, elements.size()));

        Set<Pair<T>> result = sorted(elements.get(0), elements);

        result.addAll(sorted(elements.subList(1,elements.size())));

        return result;

    }

    public Set<Pair<T>> sorted(T element, List<T> elements){
        return elements.stream().map(el -> new Pair<T>(element, el)).collect(Collectors.toCollection(HashSet::new));
    }
}
