package edu.umb.cs681.hw06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String [] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        Path path = Paths.get("food_establishments.csv");
        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> matrix =
                    lines.map(line -> {
                        return Stream.of(line.split(","))
                                .map(value -> value.substring(0))
                                .collect(Collectors.toList());
                    })
                            .collect(Collectors.toList());

            LicenseStatus licenseStatus = new LicenseStatus(matrix);
            CityDivision cityDivision = new CityDivision(matrix);
            PassFail passFail = new PassFail(matrix);

            Thread thread1 = new Thread(licenseStatus);
            Thread thread2 = new Thread(cityDivision);
            Thread thread3 = new Thread(passFail);

            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
