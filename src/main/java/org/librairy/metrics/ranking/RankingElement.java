package org.librairy.metrics.ranking;

/**
 * Created on 29/08/16:
 *
 * @author cbadenes
 */
public class RankingElement {

    private Double weight;

    private String id;

    public RankingElement(String id, Double weight){
        this.id     = id;
        this.weight = weight;
    }

    public String getId(){
        return this.id;
    }

    public Double getWeight(){
        return this.weight;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof RankingElement)) return false;

        return ((RankingElement)obj).getId().equalsIgnoreCase(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
