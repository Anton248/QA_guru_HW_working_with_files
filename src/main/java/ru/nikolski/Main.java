package ru.nikolski;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //создаем человека
        Human petya = new Human(40, "Petr", "Petrov");
        Human olya = new Human(20, "Olga", "Ivanova");
        Human vasya = new Human(15, "Vasiliy", "Petrov");
        petya.addChild(olya);
        petya.addChild(vasya);

        //записываем человека в json
        try {
            File humanInFile = new File("src/test/resources/human.json");
            humanInFile.createNewFile();
            FileWriter fw = new FileWriter(humanInFile);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fw, petya);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
