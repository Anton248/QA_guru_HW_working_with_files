package ru.nikolski;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        //создаем человека
        Human petya = new Human(40, "Пётр", "Петров");
        Human olya = new Human(20, "Olga", "Ivanova");
        Human vasya = new Human(15, "Vasiliy", "Petrov");
        petya.addChild(olya);
        petya.addChild(vasya);

        //записываем человека в json
        try {
            File humanInFile = new File("src/test/resources/human.json");
            humanInFile.createNewFile();
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(humanInFile), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(osw, petya);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }




    }
}
