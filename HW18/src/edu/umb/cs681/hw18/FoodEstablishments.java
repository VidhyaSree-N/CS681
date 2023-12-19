package edu.umb.cs681.hw18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodEstablishments {
    public static void main(String [] args){
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        Path path = Paths.get("food_establishments.csv");
        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix =
                    lines.parallel()
                            .map( line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0))
                                .collect( Collectors.toList() ); })
                                .collect( Collectors.toList() );
            // DATA Processing 1
            System.out.println("data processing 1 : Licenses that are active");
            List<List<String>> licenseStatus = matrix.parallelStream()
                    .filter(l -> l.get(8).equalsIgnoreCase("active"))
                    .collect(Collectors.toList());
            System.out.println("Number of licenses that are active : " + licenseStatus.size());

            // Calculating the percentage of active license status
            double average = (double) licenseStatus.size() / matrix.size() * 100;
            System.out.println("Percentage of Active licenses :" + average);
            // DATA Processing 2
            System.out.println("");
            System.out.println("Data processing 2: Separating data by city");
            Map<String, Long> cityFood = matrix.parallelStream()
                    .collect(Collectors.groupingBy(l -> l.get(21), Collectors.counting()));

            cityFood.forEach((city, count) -> {
                System.out.println("City: " + city + " - Number of food establishments: " + count);
            });
            // DATA Processing 3
            System.out.println("");
            System.out.println("Data Processing 3: Those who passed and failed inspection test");
            Map<Boolean, List<List<String>>> violationResult = matrix.parallelStream().collect(Collectors.partitioningBy(v -> v.get(17).equalsIgnoreCase("pass")));

            List<List<String>> inspectionPassed = violationResult.get(true);
            List<List<String>> inspectionFailed = violationResult.get(false);

            System.out.println("Number of food establishments passed inspection: " + inspectionPassed.size());
            System.out.println("Number of food establishments failed inspection: " + inspectionFailed.size());

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
