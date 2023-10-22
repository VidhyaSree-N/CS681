package edu.umb.cs681.hw06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LicenseStatus implements Runnable {
    private List<List<String>> matrix;
    public LicenseStatus(List<List<String>> matrix){
        this.matrix = matrix;
    }
    public void run(){
        // DATA Processing 1
        System.out.println("Thread 1 running");
        System.out.println("Data processing 1 : Licenses that are active");
        List<List<String>> licenseStatus = matrix.stream()
                .filter(l -> l.get(8).equalsIgnoreCase("active"))
                .collect(Collectors.toList());
        System.out.println("Number of licenses that are active : " + licenseStatus.size());

        // Calculating the percentage of active license status
        double average = (double) licenseStatus.size() / matrix.size() * 100;
        System.out.println("Percentage of Active licenses :" + average);
    }
}
