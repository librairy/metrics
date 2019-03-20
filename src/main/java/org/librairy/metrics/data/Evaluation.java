package org.librairy.metrics.data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class Evaluation {

    private final List<String> reference;
    private final List<String> value;
    private final Date time;

    public Evaluation(List<String> reference, List<String> value){
        this.reference  = reference;
        this.value      = value;
        this.time       = new Date();
    }

    public Double getPrecision(){

        if (value.isEmpty()) return 1.0;

        if (reference.isEmpty() && (!value.isEmpty())){
            return 0.0;
        }

        Double truePositive, falsePositive;
        truePositive = falsePositive = 0.0;

        truePositive    += value.stream().filter( e -> reference.contains(e)).count();

        falsePositive   += value.stream().filter( e -> !reference.contains(e)).count();

        double positive = (Double.valueOf(truePositive) + Double.valueOf(falsePositive));

        if (positive == 0.0) return 0.0;

        return Double.valueOf(truePositive) / positive;
    }

    public Double getPrecisionAt(Integer n){

        if (value.isEmpty()) return 1.0;

        if (reference.isEmpty() && (!value.isEmpty())){
            return 0.0;
        }

        Double truePositive, falsePositive;
        truePositive = falsePositive = 0.0;

        List<String> candidateList = value.stream().limit(n).collect(Collectors.toList());

        truePositive    += candidateList.stream().filter( e -> reference.contains(e)).count();

        falsePositive   += candidateList.stream().filter( e -> !reference.contains(e)).count();

        double positive = (Double.valueOf(truePositive) + Double.valueOf(falsePositive));

        if (positive == 0.0) return 0.0;

        return Double.valueOf(truePositive) / positive;
    }

    public Double getRecall(){

        if (reference.isEmpty()){
            return 1.0;
        }

        Double truePositive, falseNegative;
        truePositive = falseNegative = 0.0;

        truePositive    += value.stream().filter( e -> reference.contains(e)).count();

        falseNegative   += reference.stream().filter( e -> !value.contains(e)).count();

        double positive = (Double.valueOf(truePositive)+ Double.valueOf(falseNegative));

        if (positive == 0.0) return 0.0;

        return Double.valueOf(truePositive) / positive;
    }

    public Double getRecallAt(Integer n){

        if (reference.isEmpty()){
            return 1.0;
        }

        Double truePositive, falseNegative;
        truePositive = falseNegative = 0.0;

        List<String> candidateList = value.stream().limit(n).collect(Collectors.toList());

        truePositive    += candidateList.stream().filter( e -> reference.contains(e)).count();

        falseNegative   += reference.stream().filter( e -> !candidateList.contains(e)).count();

        double positive = (Double.valueOf(truePositive)+ Double.valueOf(falseNegative));

        if (positive == 0.0) return 0.0;

        return Double.valueOf(truePositive) / positive;
    }

    public Double getFMeasure(){

        Double precision = getPrecision();
        Double recall = getRecall();
        if ((precision == 0) && (recall == 0)) return 0.0;
        return 2 * (precision*recall) / (precision+recall);
    }

    public Double getFMeasureAtN(Integer n){

        Double precision = getPrecisionAt(n);
        Double recall = getRecallAt(n);
        if ((precision == 0) && (recall == 0)) return 0.0;
        return 2 * (precision*recall) / (precision+recall);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "reference=" + reference +
                ", value=" + value +
                ", time=" + time +
                '}';
    }
}
