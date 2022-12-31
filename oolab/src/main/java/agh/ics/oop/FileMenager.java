package agh.ics.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public final class FileMenager {
    private String filename;

    public FileMenager() {
        this.filename = "oolab/src/main/resources/defaultfile.csv";
    }

    public void setFileName(String filename) throws IOException {
        this.filename = filename;
        PrintWriter output = new PrintWriter(new FileWriter(filename));
        output.println("AllAnimals;AllPlants;FreeSpots;MostPopularGenotype;AverageEnergyAlive;AverageEnergyDead");
    }

    public void dayStats(Object[] statistics) {
        StringBuilder stringBuilder = new StringBuilder(); //takie cuś do łączenia stringów
        for (int i=0;i<statistics.length;i++){
            String data = statistics[i].toString();
            for (int j = 0; j < data.length(); j++) {
                char curr = data.charAt(j);
                stringBuilder.append(curr);
            }
            stringBuilder.append(";");
        }
        try (PrintWriter output = new PrintWriter(new FileWriter(filename, true))) {
            output.println(stringBuilder);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FileMenager) obj;
        return Objects.equals(this.filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename);
    }

    @Override
    public String toString() {
        return "FileMenager[" +
                "filename=" + filename + ']';
    }


}
