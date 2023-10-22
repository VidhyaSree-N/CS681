package edu.umb.cs681.hw06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassFail implements Runnable  {
    private List<List<String>> matrix;
    public PassFail(List<List<String>> matrix){
        this.matrix = matrix;
    }
    public void run(){
        // DATA Processing 3
        System.out.println("Thread 3 running");
        System.out.println("Data Processing 3: Those who passed and failed inspection test");
        Map<Boolean, List<List<String>>> violationResult = matrix.stream().collect(Collectors.partitioningBy(v -> v.get(17).equalsIgnoreCase("pass")));

        List<List<String>> inspectionPassed = violationResult.get(true);
        List<List<String>> inspectionFailed = violationResult.get(false);

        System.out.println("Number of food establishments passed inspection: " + inspectionPassed.size());
        System.out.println("Number of food establishments failed inspection: " + inspectionFailed.size());
    }
}
