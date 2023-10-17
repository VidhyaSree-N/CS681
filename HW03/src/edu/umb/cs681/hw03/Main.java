package edu.umb.cs681.hw03;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Car car1, car2, car3, car4,car5,car6, car7, car8, car9,car10;
        ArrayList<Car> cars;
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
        double averagePrice = cars.stream().map(car -> car.getPrice())
                .reduce(new CarPriceResultHolder(),
                        (result,price)->{
                            int numCarExamined = result.getNumCarExamined();
                            double average = result.getAverage();
                            double totalPrice = (average * numCarExamined) + price;
                            result.setAverage(totalPrice /++numCarExamined);
                            result.setNumCarExamined(numCarExamined++);
                            return result;
                        },
        (finalResult,intermediateResult)->finalResult)
        .getAverage();

        System.out.println("Average Price of Cars "+ averagePrice);
    }
}
