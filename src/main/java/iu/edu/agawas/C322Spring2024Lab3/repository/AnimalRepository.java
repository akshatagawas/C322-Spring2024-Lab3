package iu.edu.agawas.C322Spring2024Lab3.repository;

import iu.edu.agawas.C322Spring2024Lab3.model.AnimalData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalRepository {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME = "db.txt";
    public static void appendToFile(Path path, String content) throws IOException {
        Files.write(
                path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public boolean add(AnimalData animalData) throws IOException{
        Path path = Paths.get(DATABASE_NAME);
        String data = animalData.getName() + "," +
                animalData.getPicture() + "," +
                animalData.getLocation();
        appendToFile(path, data + NEW_LINE);
        return true;
    }

    public List<AnimalData> findAll() throws IOException{
        List<AnimalData> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line: data){
            String[] words = line.split(",");
            AnimalData a = new AnimalData();
            a.setName(words[0]);
            a.setPicture(words[1]);
            a.setLocation(words[2] + "," + words[3]);
            result.add(a);
        }
        return result;
    }

    public List<AnimalData> find(String name, String picture, String location) throws IOException {
        List<AnimalData> result = new ArrayList<>();
        List<AnimalData> allData = findAll();
        for (AnimalData animalData : allData) {
            boolean matches = true;
            if (name != null && !name.equalsIgnoreCase(animalData.getName())) {
                matches = false;
            }
            if (picture != null && !picture.equalsIgnoreCase(animalData.getPicture())) {
                matches = false;
            }
            if (location != null && !location.equalsIgnoreCase(animalData.getLocation())) {
                matches = false;
            }
            if (matches) {
                result.add(animalData);
            }
        }
        return result;
    }

}
