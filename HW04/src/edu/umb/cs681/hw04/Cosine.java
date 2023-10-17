package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements edu.umb.cs681.hw04.DistanceMetric {

    @Override
    public double distance(List<Double> p1, List<Double> p2) {
//        Previous CS680 Hw solution

//        double dotProduct = 0.0;
//        double magnitudeofP1 = 0.0;
//        double magnitudeofP2 = 0.0;
//
//        // calculating dot product
//        for (int i = 0; i < p1.size(); i++) {
//            dotProduct += p1.get(i) * p2.get(i);
//        }
//
//        // Calculate the magnitude of p1
//        for (double value : p1) {
//            magnitudeofP1 += Math.pow(value, 2);
//        }
//        magnitudeofP1 = Math.sqrt(magnitudeofP1);
//
//        // Calculate the magnitude of p2
//        for (double value : p2) {
//            magnitudeofP2 += Math.pow(value, 2);
//        }
//        magnitudeofP2 = Math.sqrt(magnitudeofP2);
//
//        // Calculate the cosine distance
//        double cosineDistance = dotProduct / (magnitudeofP1 * magnitudeofP2);

        double dotProduct = 0.0;
        double magnitudeofP1 = Math.sqrt(p1.stream().mapToDouble(m -> m*m).sum());
        double magnitudeofP2 = Math.sqrt(p2.stream().mapToDouble(m -> m*m).sum());

        dotProduct = IntStream.range(0, p1.size())
                .mapToDouble(i -> p1.get(i) * p2.get(i))
                .sum();

        double cosineDistance = dotProduct / (magnitudeofP1 * magnitudeofP2);

        return cosineDistance;
    }

}



