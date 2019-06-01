package main.csv;

import main.entity.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class ReadCsv {

    private ReadCsv() {
    }

    //mapowanie - zwaca listę obiektów Customer
    public static List<Customer> parseCsv(String path) throws IOException {


        List<Customer> customers = new LinkedList<>();

        FileInputStream inputStream = null;
        Scanner scanner = null;
        Customer customer = null;


        try {
            inputStream = new FileInputStream(path);
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNextLine()) {

                    String[] line = scanner.nextLine().split(",");
                    customer = new Customer();

                    customer.setName(line[0]);
                    customer.setSurname(line[1]);

                    try {
                        Integer age = null;
                        if (line[2].equals(""))
                            customer.setAge(age);
                        else
                            customer.setAge(Integer.parseInt(line[2]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    customer.setContact(new ArrayList<>());
                    for (int i = 4; i < line.length; i++) {
                        customer.getContact().add(line[i]);
                    }

                    customers.add(customer);

            }

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }

        return customers;
    }


}
