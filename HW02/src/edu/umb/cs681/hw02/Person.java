package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LinkedList<Dose> doses = new LinkedList<>();

    public Person(String firstName,String lastName,LocalDate dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public int getAge(){
        int age = LocalDate.now().getYear() - dob.getYear();
        return age;
    }

    public AgeCat getAgeCat(){
        int age = getAge();
        if(age >= 60){
            return AgeCat.OLD;
        }
        else if(age >= 30){
            return AgeCat.MID;
        }
        else {
            return AgeCat.YOUNG;
        }
    }

    public LinkedList<Dose> getDoses(){
        return doses;
    }

    public int getVaccCount(){
        return doses.size();
    }
}
