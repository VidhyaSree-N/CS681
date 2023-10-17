package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int numPoints = 1003;
        int numDimensions = 101;

        List<List<Double>> points = generateRandomPoints(numPoints, numDimensions);
        List<List<Double>> distanceMatrix = Distance.matrix(points, new Euclidean());
        System.out.println(distanceMatrix.size());
    }

    private static List<List<Double>> generateRandomPoints(int numPoints, int numDimensions) {
        Random random = new Random();
        List<List<Double>> points = new ArrayList<>();

        IntStream.range(0,numPoints).forEach(i -> {
            List<Double> point = IntStream.range(0, numDimensions)
                    .mapToObj(j -> random.nextDouble())
                    .collect(Collectors.toList());
            points.add(point);
        });

        return points;
    }
}
