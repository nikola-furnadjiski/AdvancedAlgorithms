package com.company.solvingAlgorithmicProblems;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) throws Exception {

        Person persons[] = new Person[3];
        persons[0] = new Person("Name 1", 100);
        persons[1] = new Person("Name 2", 300);
        persons[2] = new Person("Name 3", 200);

        Person.COMP = 1;
        Arrays.sort(persons);

        for (int i = 0;i<3;i++) {
            System.out.println(persons[i].name+"\t"+persons[i].salary);
        }

        Person.COMP = 2;
        Arrays.sort(persons);

        for (int i = 0;i<3;i++) {
            System.out.println(persons[i].name+"\t"+persons[i].salary);
        }

    }

}

class Person implements Comparable<Person> {

    static int COMP;
    String name;
    int salary;

    Person (String name, int salary) {
        this.name = name;
        this.salary = salary;
        COMP = 1;
    }

    @Override
    public int compareTo(Person o) {
        if (COMP == 1) {
            if (salary < o.salary) {
                return -1;
            }

            if (salary > o.salary) {
                return 1;
            }
        } else if (COMP == 2) {
            return name.compareTo(o.name);
        }

        return 0;
    }

}