package agh.ics.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public record FileMenager(String filename) {

    public FileMenager(String filename){
        this.filename = filename;
        try (PrintWriter output = new PrintWriter(new FileWriter(filename))) {
            output.println("AllAnimals, AllPlants, FreeSpots, MostPopularGenotype, AverageEnergyAlive, AverageEnergyDead");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dayStats(Object[] statistics){
        String data = Arrays.toString(statistics);
        data = data.substring(0, data.length() - 1);

        StringBuilder stringBuilder = new StringBuilder(); //takie cuś do łączenia stringów

        for (int i = 0; i < data.length(); i++ ){
            char curr = data.charAt(i);
            if (curr != ' '){
                stringBuilder.append(curr);
            }
        }

        try (PrintWriter output = new PrintWriter(new FileWriter(filename, true))){
            output.println(stringBuilder);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }


}
