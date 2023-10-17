package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.IntStream;

public class Manhattan implements edu.umb.cs681.hw04.DistanceMetric {

    @Override
    public double distance(List<Double> p1, List<Double> p2) {
        if (p1.size() != p2.size()) {
            throw new IllegalArgumentException("The two points must have the same number of dimensions.");
        }

//        Previous CS680 HW solution

//        double manhattanDistance = 0;
//        //Calculates the manhattan distance
//        for(int i = 0 ; i<p1.size(); i++){
//            manhattanDistance = manhattanDistance + Math.abs(p1.get(i) - p2.get(i));
//        }

        double manhattanDistance = IntStream
                .range(0, p1.size())
                .mapToDouble(i -> Math.abs(p1.get(i)-p2.get(i)))
                .sum();

        return manhattanDistance;
    }
}
