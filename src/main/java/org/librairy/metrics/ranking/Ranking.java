package org.librairy.metrics.ranking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29/08/16:
 *
 * @author cbadenes
 */
public class Ranking {

    private List<RankingElement> elements;


    public Ranking(){
        this.elements = new ArrayList<>();
    }

    public Ranking add(RankingElement element){
        this.elements.add(element);
        return this;
    }

    public Ranking addAll(List<RankingElement> elements){
        this.elements.addAll(elements);
        return this;
    }

}
