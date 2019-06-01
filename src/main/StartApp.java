package main;

import main.file.AddFileToDb;
import main.file.SelectFile;

import java.io.IOException;
import java.sql.SQLException;


public class StartApp {

    public static void main(String[] args) throws IOException, SQLException {

        //wyświetlenie okienka wyboru pliku
        SelectFile selectFile = SelectFile.getInstance();
        int returnVal = selectFile.showDialog().showOpenDialog(null);

        //pobranie nazwy i rozszerzenia wybranego pliku [0]-nazwa pliku [1]-rozszerzenie
        String[] file = selectFile.getSelectedOption(returnVal);


        switch (file[1]) {
            case "xml":
                System.out.println("Load XML file ...");
                AddFileToDb.load(file);
                System.out.println("Loading complete");
                break;
            case "csv":
                System.out.println("Load CSV file ...");
                AddFileToDb.load(file);
                System.out.println("Loading complete");
                break;
            default:
                System.out.println("No selected file");
                break;
        }


    }
}
