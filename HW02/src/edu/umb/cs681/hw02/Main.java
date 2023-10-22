package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = generateRandomPeople();
        System.out.println("Number of people :" + people.size());

        // Calculate the vaccination rate for each age category
        Map<AgeCat, Double> vaccinationRateByAgeCategory = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAgeCat,
                        Collectors.averagingDouble(person -> person.getVaccCount() >= 3 ? 100.0 : 0.0)
                ));

        // Calculate the average number of vaccinations administered in each age category
        Map<AgeCat, Double> averageVaccinationsByAgeCategory = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAgeCat,
                        Collectors.averagingInt(Person::getVaccCount)
                ));

        // Calculate the average age of people who have never been vaccinated
        Map<Boolean, Double> averageAgeOfUnvaccinated = people.stream()
                .collect(Collectors.partitioningBy(person -> person.getVaccCount() == 0,
                        Collectors.averagingInt(Person::getAge)
                ));

        System.out.println("Vaccination Rate by Age Category: " + vaccinationRateByAgeCategory);
        System.out.println("Average Vaccinations by Age Category: " + averageVaccinationsByAgeCategory);
        System.out.println("Average Age of Unvaccinated: " + averageAgeOfUnvaccinated.get(true));
    }

    public static List<Person> generateRandomPeople() {
        Random random = new Random();
        //Randomnly generates num of people
        int numPeople = random.nextInt(2000) + 1000;

        return IntStream.range(0, numPeople)
                .mapToObj(i -> {
                    String firstName = "FirstName" + i;
                    String lastName = "LastName" + i;
                    LocalDate dob = LocalDate.of(1950 + random.nextInt(70), 1 + random.nextInt(12), 1 + random.nextInt(28));

                    Person person = new Person(firstName, lastName, dob);

                    int numVaccinations = random.nextInt(5);
                    IntStream.range(0, numVaccinations)
                            .forEach(j -> person.getDoses().add(new Dose("VaccName", "LotNum" + j, LocalDate.now(), "InjecSite")));

                    return person;
                })
                .collect(Collectors.toList());
    }
}
