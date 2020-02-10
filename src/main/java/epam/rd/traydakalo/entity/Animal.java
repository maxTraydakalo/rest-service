package epam.rd.traydakalo.entity;

import epam.rd.traydakalo.exceptions.NoSuchEmployeeException;

import java.util.function.Supplier;

public class Animal {
    public String animal = "animal";

    public void checkConcept(String check){
        System.out.println(animal);
        System.out.println(check);
        Supplier<Exception> asdf = ()->new NoSuchEmployeeException();
    }

    public void checkConceptClass(Animal animal){
        System.out.println(animal.getClass());
    }
}
