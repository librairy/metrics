package org.librairy.metrics.hash;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.librairy.metrics.data.Stats;
import org.librairy.metrics.data.TopicPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */

public class DensityBasedHash implements HashMeasure<List<Double>> {

    private static final Logger LOG = LoggerFactory.getLogger(DensityBasedHash.class);

    private Map<Integer, List<String>> cluster(List<Double> vector){
        try{
            Stats stats = new Stats(vector);
            Double eps = stats.getVariance();
            DistanceMeasure distanceMeasure = new MonoDimensionalDistanceMeasure();

            Map<Integer,List<String>> clusterMap = new HashMap<>();

            int minPts = 0;
            String sep = "_";

            DBSCANClusterer<TopicPoint> clusterer = new DBSCANClusterer<>(eps, minPts, distanceMeasure);


            List<TopicPoint> points = IntStream.range(0, vector.size()).mapToObj(i -> new TopicPoint("" + i, vector.get(i))).collect(Collectors.toList());
            List<Cluster<TopicPoint>> clusterList = clusterer.cluster(points);

            List<TopicPoint> groups = new ArrayList<>();
            int totalPoints = 0;
            for (Cluster<TopicPoint> cluster : clusterList) {
                Double score = (cluster.getPoints().stream().map(p -> p.getScore()).reduce((x, y) -> x + y).get()) / (cluster.getPoints().size());
                String label = cluster.getPoints().stream().map(p -> "t" + p.getId()).sorted((x, y) -> -x.compareTo(y)).collect(Collectors.joining(sep));

                totalPoints += cluster.getPoints().size();

                groups.add(new TopicPoint(label, score));
            }
            if (totalPoints < vector.size()) {
                List<TopicPoint> clusterPoints = clusterList.stream().flatMap(l -> l.getPoints().stream()).collect(Collectors.toList());
                List<TopicPoint> isolatedTopics = points.stream().filter(p -> !clusterPoints.contains(p)).collect(Collectors.toList());
                Double score = (isolatedTopics.stream().map(p -> p.getScore()).reduce((x, y) -> x + y).get()) / (isolatedTopics.size());
                String label = isolatedTopics.stream().map(p -> "t" + p.getId()).sorted((x, y) -> -x.compareTo(y)).collect(Collectors.joining(sep));
                groups.add(new TopicPoint(label, score));
            }
            Collections.sort(groups, (a, b) -> -a.getScore().compareTo(b.getScore()));
            for(int i=0;i<groups.size();i++){
                clusterMap.put(i,Arrays.asList(groups.get(i).getId().split(sep)));
            }
            return clusterMap;
        }catch (Exception e){
            LOG.error("Error creating cluster from density",e);
            return new HashMap<>();
        }
    }

    @Override
    public Map<Integer, List<String>> hash(List<Double> topicDistribution) {

        Map<Integer, List<String>> hash = new HashMap<>();

        Map<Integer, List<String>> topicPoints = cluster(topicDistribution);

        for(int i=0;i<3;i++){
            hash.put(i,topicPoints.get(i));
        }
        return hash;
    }


    private static class MonoDimensionalDistanceMeasure implements DistanceMeasure {

        @Override
        public double compute(double[] p1, double[] p2) {
            return Math.abs(p1[0] - p2[0]);
        }
    }


    public static void main(String[] args) {

        List<List<Double>> examples = new ArrayList<>();

        examples.add(Arrays.asList(
                0.2194029850746267,
                0.02039800995024874,
                0.0004975124378109449,
                0.0004975124378109449,
                0.0004975124378109449,
                0.289054726368159,
                0.19950248756218888,
                0.22935323383084558,
                0.0004975124378109449,
                0.04029850746268654
        ));

        examples.add(Arrays.asList(0.24925373134328338,
                0.05024875621890543,
                0.0004975124378109449,
                0.0004975124378109449,
                0.0004975124378109449,
                0.24925373134328338,
                0.19950248756218888,
                0.20945273631840777,
                0.0004975124378109449,
                0.04029850746268654));

        examples.add(Arrays.asList(0.26915422885572116,
                0.09004975124378102,
                0.0004975124378109449,
                0.0004975124378109449,
                0.0004975124378109449,
                0.18955223880597,
                0.19950248756218888,
                0.19950248756218888,
                0.0004975124378109449,
                0.050248756218905434));

        examples.add(Arrays.asList(0.26915422885572116,
                0.03034825870646763,
                0.0004975124378109449,
                0.0004975124378109449,
                0.0004975124378109449,
                0.2393034825870645,
                0.20945273631840777,
                0.19950248756218888,
                0.0004975124378109449,
                0.050248756218905434));

        DensityBasedHash hashAlgorithm = new DensityBasedHash();
        System.out.println(hashAlgorithm.hash(examples.get(0)));
        examples.forEach(l -> System.out.println("hash: " + hashAlgorithm.hash(l)));


    }

}
