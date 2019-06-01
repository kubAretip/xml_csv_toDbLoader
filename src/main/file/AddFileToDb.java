package main.file;

import main.csv.ReadCsv;

import main.db.DBQuery;

import main.xml.ReadXml;

import java.io.IOException;
import java.sql.SQLException;



public class AddFileToDb {

    private AddFileToDb() {
    }

    public static void load(String[] file) throws IOException, SQLException {

        DBQuery dbQuery = DBQuery.getInstance();
        String filePath = file[0];
        String fileExtension = file[1];


        if (fileExtension.equals("csv")) {
           dbQuery.insertDataCustomers(ReadCsv.parseCsv(filePath));
        }
        if (fileExtension.equals("xml")) {
            dbQuery.insertDataCustomers(ReadXml.parseXML(filePath));
        }

    }

}