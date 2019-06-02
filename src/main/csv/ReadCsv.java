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

    public static List<Customer> parseCSV(String path) throws IOException {

        List<Customer> customers = new LinkedList<>();

        Customer customer;

        try (FileInputStream inputStream = new FileInputStream(path); Scanner scanner = new Scanner(inputStream, "UTF-8")) {

            while (scanner.hasNextLine()) {

                try {
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

                    if (line.length > 3) {
                        customer.setContact(new ArrayList<>());
                        for (int i = 4; i < line.length; i++) {
                            customer.getContact().add(line[i]);
                        }
                    }

                    customers.add(customer);

                } catch (ArrayIndexOutOfBoundsException e) {
                    //catch empty line
                }
            }

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }

        }

        return customers;
    }


}
