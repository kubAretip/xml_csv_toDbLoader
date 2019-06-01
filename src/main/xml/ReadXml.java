package main.xml;

import main.entity.Customer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ReadXml {

    public static List<Customer> parseXML(String fileName) {

        List<Customer> customers = new LinkedList<>();
        Customer customer = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {

            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while (xmlEventReader.hasNext()) {

                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                // jeśli napotka tag <>
                if (xmlEvent.isStartElement()) {

                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equalsIgnoreCase("person")) {
                        customer = new Customer();
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("surname")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setSurname(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("age")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("contacts")) {
                        customer.setContact(new ArrayList<>());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("phone")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.addContact(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("email")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.addContact(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("jabber")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.addContact(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("city")) {
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("persons")) {
                    } else {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.addContact(xmlEvent.asCharacters().getData());
                    }

                }

                // jeśli napotka </>
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("person")) {
                        customers.add(customer);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
