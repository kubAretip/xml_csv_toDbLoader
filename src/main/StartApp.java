package main;

import main.csv.ReadCsv;
import main.db.DBQuery;
import main.file.SelectFile;
import main.xml.ReadXml;

import java.io.IOException;
import java.sql.SQLException;

public class StartApp {

    public static void main(String[] args) throws IOException, SQLException {

        //wyświetlenie okienka wyboru pliku
        SelectFile selectFile = SelectFile.getInstance();
        int returnVal = selectFile.showDialog().showOpenDialog(null);

        //pobranie nazwy i rozszerzenia wybranego
        String[] file = selectFile.getSelectedOption(returnVal);
        String filePath = file[0];
        String fileExtension = file[1];

        DBQuery dbQuery = DBQuery.getInstance();

        switch (fileExtension) {
            case "xml":
                System.out.println("Load XML file ...");
                dbQuery.insertDataCustomers(ReadXml.parseXML(filePath));
                break;
            case "csv":
                System.out.println("Load CSV file ...");
                dbQuery.insertDataCustomers(ReadCsv.parseCSV(filePath));
                break;
            default:
                System.out.println("No selected file");
                break;
        }


    }
}
