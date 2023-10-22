package edu.umb.cs681.hw06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityDivision implements Runnable {
    private List<List<String>> matrix;
    public CityDivision(List<List<String>> matrix){
        this.matrix = matrix;
    }
    public void run(){
        // DATA Processing 2
        System.out.println("Thread 2 running");
        System.out.println("Data processing 2: Separating data by city");
        Map<String, Long> cityFood = matrix.stream()
                .collect(Collectors.groupingBy(l -> l.get(21), Collectors.counting()));

        cityFood.forEach((city, count) -> {
            System.out.println("City: " + city + " - Number of food establishments: " + count);
        });
    }
}
