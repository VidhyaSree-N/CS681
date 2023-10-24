package edu.umb.cs681.hw04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMetrixTest {
    private int numPoints = 1000;
    private int numDimensions = 100;
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

    @Test
    public void DistanceMetrixTest(){
        List<List<Double>> points = generateRandomPoints(numPoints, numDimensions);
        List<List<Double>> distanceMatrix = Distance.matrix(points, new Euclidean());
        List<List<Double>> distanceMatrix2 = Distance.matrix(points, new Manhattan());
        List<List<Double>> distanceMatrix3 = Distance.matrix(points, new Cosine());
        assertEquals(2000,distanceMatrix.size());
        assertEquals(2000,distanceMatrix2.size());
        assertEquals(2000,distanceMatrix3.size());
    }

}
