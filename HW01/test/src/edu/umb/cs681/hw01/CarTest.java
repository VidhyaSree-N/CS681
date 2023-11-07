package edu.umb.cs681.hw01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    private Car car1, car2, car3, car4,car5,car6, car7, car8, car9,car10;
    private ArrayList<Car> cars;

    @BeforeEach
    public void init() {
        car1 = new Car("Toyota", "Camry", 31000, 2015, 28000);
        car2 = new Car("Honda", "Civic", 53000, 2010, 38000);
        car3 = new Car("Ford", "Fiesta", 26000, 2003, 65000);
        car4 = new Car("Chevrolet", "Malibu", 43000, 2014, 25000);
        car5 = new Car("Audi", "Senta", 35000, 2018, 22000);
        car6 = new Car("Kia", "Granach", 80000, 2006, 15000);
        car7 = new Car("BMW", "Original", 45000, 2020, 68000);
        car8 = new Car("Ferrari", "Og", 50000, 2009, 75000);
        car9 = new Car("Swift", "Canni", 40000, 2018, 45000);
        car10 = new Car("Creta", "Sword", 35500, 2019, 62000);
        cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        cars.add(car6);
        cars.add(car7);
        cars.add(car8);
        cars.add(car9);
        cars.add(car10);
    }

    @Test
    public void SortByMileage() {
        Collections.sort(cars, (Car o1, Car o2)-> (o1.getMileage() - o2.getMileage()));
        assertEquals(car3, cars.get(0));
        assertEquals(car1, cars.get(1));
        assertEquals(car5, cars.get(2));
    }

    @Test
    public void SortByPrice() {
        Collections.sort(cars, (Car o1, Car o2)-> (int) (o1.getPrice() - o2.getPrice()));
        assertEquals(car6, cars.get(0));
    }

    @Test
    public void SortByYear() {
        Collections.sort(cars, (Car o1, Car o2)-> (o1.getYear() - o2.getYear()));
        assertEquals(car3, cars.get(0));
    }

    @Test
    public void SortByPareto() {
        for (Car car : cars) {
            car.setDominationCount(cars);
        }
        Collections.sort(cars, (Car o1, Car o2)-> (o1.getDominationCount() - o2.getDominationCount()));
        assertEquals(car8, cars.get(0));
    }

    @Test
    public void SortByMileageTest() {
        Collections.sort(cars,Comparator.comparing( Car::getMileage) );
        assertEquals(car3, cars.get(0));
        assertEquals(car1, cars.get(1));
        assertEquals(car5, cars.get(2));
    }

    @Test
    public void SortByPriceTest() {
        Collections.sort(cars,Comparator.comparing( Car::getPrice ) );
        assertEquals(car6, cars.get(0));
    }

    @Test
    public void SortByYearTest() {
        Collections.sort(cars,Comparator.comparing( Car::getYear ) );
        assertEquals(car3, cars.get(0));
    }

    @Test
    public void SortByParetoTest() {
        for (Car car : cars) {
            car.setDominationCount(cars);
        }
        Collections.sort(cars,Comparator.comparing( Car::getDominationCount ) );
        assertEquals(car8, cars.get(0));
    }

    @Test
    public void ReverseSortByMileageTest() {
        //Using .reversed method
        Collections.sort(cars, Comparator.comparing(Car::getMileage).reversed());
        assertEquals(car6, cars.get(0));
    }

    @Test
    public void ReverseSortByPriceTest() {
        //Using .reversed method
        Collections.sort(cars, Comparator.comparing(Car::getPrice).reversed());
        assertEquals(car8, cars.get(0));
    }

    @Test
    public void ReverseSortByYearTest() {
        //Using .reverseorder method
        Collections.sort(cars,Comparator.comparing(Car::getYear,Comparator.reverseOrder()) );
        assertEquals(car7, cars.get(0));
    }

    @Test
    public void ReverseSortByParetoTest() {
        //Using .reverseorder method
        for (Car car : cars) {
            car.setDominationCount(cars);
        }
        Collections.sort(cars,Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder()) );
        assertEquals(car7, cars.get(4));
    }
    @Test
    public void priceSort() {
        int threshold = 30000;
        //Separating cars HIGH and LOW based on threshold mileage
        Map<String, List<Car>> carsGrouped = cars.stream().collect(Collectors.groupingBy(car -> car.getPrice() > threshold ? "HIGH" : "LOW"));

        List<Car> highCars = carsGrouped.get("HIGH");
        List<Car> lowCars = carsGrouped.get("LOW");
        DoubleSummaryStatistics highStats = highCars.stream().collect(Collectors.summarizingDouble( Car::getPrice));
        DoubleSummaryStatistics lowStats = lowCars.stream().collect(Collectors.summarizingDouble(Car::getPrice));

        double highAverage = highStats.getAverage();
        double lowAverage = lowStats.getAverage();

        double highHighest = highStats.getMax();
        double lowHighest = lowStats.getMax();

        double highLowest = highStats.getMin();
        double lowLowest = lowStats.getMin();
        long highCount = highCars.size();
        long lowCount = lowCars.stream().count();

        System.out.println("Sorting based on Price : Threshold " + threshold);
        System.out.println("High group cars : " + highCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Low group cars : " + lowCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Average of High group cars : "+highAverage);
        System.out.println("Average of low group cars : "+lowAverage);
        System.out.println("Highest of High group cars : "+highHighest);
        System.out.println("lowest of high group cars : "+highLowest);
        System.out.println("Highest of Low group cars : "+lowHighest);
        System.out.println("Lowest of low group cars : "+lowLowest);
        System.out.println("Count of High group cars : "+highCount);
        System.out.println("Count of low group cars : "+lowCount);

        assertEquals(6, highCount);
        assertEquals(4, lowCount);
        assertEquals(58833.333333333336, highAverage);
        assertEquals(22500.0, lowAverage);
        assertEquals(75000.0, highHighest);
        assertEquals(28000.0, lowHighest);
        assertEquals(38000.0, highLowest);
        assertEquals(15000.0, lowLowest);
    }
    @Test
    public void yearSort() {
        int threshold = 2010;
        //Separating cars HIGH and LOW based on threshold mileage
        Map<Boolean, List<Car>> carsGrouped = cars.stream().collect(Collectors.partitioningBy(car -> car.getYear() > threshold));

        List<Car> highCars = carsGrouped.get(true);
        List<Car> lowCars = carsGrouped.get(false);

        DoubleSummaryStatistics highStats = highCars.stream().collect(Collectors.summarizingDouble( Car::getYear));
        DoubleSummaryStatistics lowStats = lowCars.stream().collect(Collectors.summarizingDouble(Car::getYear));

        double highAverage = highStats.getAverage();
        double lowAverage = lowStats.getAverage();

        double highHighest = highStats.getMax();
        double lowHighest = lowStats.getMax();

        double highLowest = highStats.getMin();
        double lowLowest = lowStats.getMin();
        long highCount = highCars.size();
        long lowCount = lowCars.stream().count();

        System.out.println("Sorting based on Year : Threshold "+threshold);
        System.out.println("High group cars : " + highCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Low group cars : " + lowCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Average of High group cars : "+highAverage);
        System.out.println("Average of low group cars : "+lowAverage);
        System.out.println("Highest of High group cars : "+highHighest);
        System.out.println("lowest of high group cars : "+highLowest);
        System.out.println("Highest of Low group cars : "+lowHighest);
        System.out.println("Lowest of low group cars : "+lowLowest);
        System.out.println("Count of High group cars : "+highCount);
        System.out.println("Count of low group cars : "+lowCount);

        assertEquals(6, highCount);
        assertEquals(4, lowCount);
        assertEquals(2017.3333333333333, highAverage);
        assertEquals(2007, lowAverage);
        assertEquals(2020, highHighest);
        assertEquals(2010, lowHighest);
        assertEquals(2014, highLowest);
        assertEquals(2003, lowLowest);
    }

    @Test
    public void mileageSort() {
        int threshold = 40000;
        //Separating cars HIGH and LOW based on threshold mileage
        Map<String, List<Car>> carsGrouped = cars.stream().collect(Collectors.groupingBy(car -> car.getMileage() > threshold ? "HIGH" : "LOW"));

        List<Car> highCars = carsGrouped.get("HIGH");
        List<Car> lowCars = carsGrouped.get("LOW");

        DoubleSummaryStatistics highStats = highCars.stream().collect(Collectors.summarizingDouble( Car::getMileage));
        DoubleSummaryStatistics lowStats = lowCars.stream().collect(Collectors.summarizingDouble(Car::getMileage));

        double highAverage = highStats.getAverage();
        double lowAverage = lowStats.getAverage();

        double highHighest = highStats.getMax();
        double lowHighest = lowStats.getMax();

        double highLowest = highStats.getMin();
        double lowLowest = lowStats.getMin();
        long highCount = highCars.size();
        long lowCount = lowCars.stream().count();

        System.out.println("Sorting based on mileage : Threshold "+ threshold);
        System.out.println("High group cars : " + highCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Low group cars : " + lowCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Average of High group cars : "+highAverage);
        System.out.println("Average of low group cars : "+lowAverage);
        System.out.println("Highest of High group cars : "+highHighest);
        System.out.println("lowest of high group cars : "+highLowest);
        System.out.println("Highest of Low group cars : "+lowHighest);
        System.out.println("Lowest of low group cars : "+lowLowest);
        System.out.println("Count of High group cars : "+highCount);
        System.out.println("Count of low group cars : "+lowCount);

        assertEquals(5, highCount);
        assertEquals(5, lowCount);
        assertEquals(54200.0, highAverage);
        assertEquals(33500.0, lowAverage);
        assertEquals(80000.0, highHighest);
        assertEquals(40000.0, lowHighest);
        assertEquals(43000.0, highLowest);
        assertEquals(26000.0, lowLowest);
    }
    @Test
    public void paretoSort() {
        for (Car car : cars) {
            car.setDominationCount(cars);
        }
        int threshold = 3;
        //Separating cars HIGH and LOW based on threshold mileage
        Map<String, List<Car>> carsGrouped = cars.stream().collect(Collectors.groupingBy(car -> car.getDominationCount() > threshold ? "HIGH" : "LOW"));

        List<Car> highCars = carsGrouped.get("HIGH");
        List<Car> lowCars = carsGrouped.get("LOW");

        DoubleSummaryStatistics highStats = highCars.stream().collect(Collectors.summarizingDouble( Car::getDominationCount));
        DoubleSummaryStatistics lowStats = lowCars.stream().collect(Collectors.summarizingDouble(Car::getDominationCount));

        double highAverage = highStats.getAverage();
        double lowAverage = lowStats.getAverage();

        double highHighest = highStats.getMax();
        double lowHighest = lowStats.getMax();

        double highLowest = highStats.getMin();
        double lowLowest = lowStats.getMin();
        long highCount = highCars.size();
        long lowCount = lowCars.stream().count();

        System.out.println("Sorting based on DominationCount : Threshold "+threshold);
        System.out.println("High group cars : " + highCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Low group cars : " + lowCars.stream().map(Car :: getMake).collect(Collectors.toList()));
        System.out.println("Average of High group cars : "+highAverage);
        System.out.println("Average of low group cars : "+lowAverage);
        System.out.println("Highest of High group cars : "+highHighest);
        System.out.println("lowest of high group cars : "+highLowest);
        System.out.println("Highest of Low group cars : "+lowHighest);
        System.out.println("Lowest of low group cars : "+lowLowest);
        System.out.println("Count of High group cars : "+highCount);
        System.out.println("Count of low group cars : "+lowCount);

        assertEquals(9, highCount);
        assertEquals(1, lowCount);
        assertEquals(8.444444444444445, highAverage);
        assertEquals(3.0, lowAverage);
        assertEquals(9, highHighest);
        assertEquals(3, lowHighest);
        assertEquals(6, highLowest);
        assertEquals(3, lowLowest);
    }
}

