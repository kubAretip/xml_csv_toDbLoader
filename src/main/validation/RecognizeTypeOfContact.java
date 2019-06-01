package main.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecognizeTypeOfContact {

    private static RecognizeTypeOfContact recognizeTypeOfContact = null;

    private static final String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String jabberRegex = "[^[a-zA-Z0-9_-]*$]{5,15}";

    private static final Pattern emailPattern = Pattern.compile(emailRegex);
    private static final Pattern jabberPattern = Pattern.compile(jabberRegex);

    private Map<String, String> preparedContacts = new HashMap<>();


    private RecognizeTypeOfContact() {

    }

    public void recognize(List<String> contacts) {
        preparedContacts.clear();
        for (String item : contacts) {
            if (recognizeEmail(item))
                preparedContacts.put(item, ContactType.EMAIL.getName());
            else if (recognizePhoneNumber(item))
                preparedContacts.put(item, ContactType.PHONE.getName());
            else if (recognizeJabber(item))
                preparedContacts.put(item, ContactType.JABBER.getName());
            else
                preparedContacts.put(item, ContactType.UNKNOWN.getName());
        }
    }

    private boolean recognizePhoneNumber(String item) {
        return (item.replaceAll("\\s+", "").length() == 9 && !recognizeEmail(item) && recognizeInt(item));
    }

    private boolean recognizeEmail(String item) {

        Matcher matcher = emailPattern.matcher(item);
        return matcher.matches();
    }

    private boolean recognizeInt(String item) {
        return item.replaceAll("\\s+", "").chars().allMatch(Character::isDigit);
    }


    private boolean recognizeJabber(String item) {

        Matcher matcher = jabberPattern.matcher(item);
        return matcher.matches();

    }


    public Map<String, String> getPreparedContacts() {
        return preparedContacts;
    }


    public static RecognizeTypeOfContact getInstance() {
        if (recognizeTypeOfContact == null) {
            recognizeTypeOfContact = new RecognizeTypeOfContact();
        }
        return recognizeTypeOfContact;
    }


}
