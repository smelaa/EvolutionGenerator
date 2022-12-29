package agh.ics.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class OptionsParser {
    public SimulationVar parse(String filePath) throws FileNotFoundException {
        try {
            Paths.get(filePath);
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(";");
            ArrayList<String> variables = new ArrayList<String>();
            while (scanner.hasNext()) {
                variables.add(scanner.next());
            }
            return new SimulationVar(variables);

        } catch (InvalidPathException | NullPointerException | FileNotFoundException ex) {
            throw new FileNotFoundException();
        }

    }

}
