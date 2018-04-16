package tdt4140.gr1812.app.core.models.signup;

import java.util.HashMap;
import org.json.JSONObject;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.helpers.Method;

public class SignUpModel {


    private static String text;


    // Method to sign up a user, with backend connection
    public static boolean signupUser(int phonenumber, String password, String sport,
            String firstName, String surname, int maxpulse, int weight, Gender gender) {
        try {
            text = "";
            checkFirstName(firstName);
            checkLastName(surname);
            checkPhoneNumber(String.valueOf(phonenumber));
            checkSport(sport);
            checkWeight(String.valueOf(weight));
            checkMaxPulse(String.valueOf(maxpulse));
               
            //String genderString = gender == gender.FEMALE ? "female" : "male";
            String genderString = "";

            if (gender == gender.FEMALE) {
                genderString = "female";
            }
            else if (gender == gender.MALE) {
                genderString = "male";
            }
            else {
                genderString = "other";
            }
            

            HashMap requestParam = new HashMap<String, String>();
            requestParam.put("username", String.valueOf(phonenumber));
            requestParam.put("password", password);
            requestParam.put("sport", sport);
            requestParam.put("firstname", firstName);
            requestParam.put("surname", surname);
            requestParam.put("maxpulse", String.valueOf(maxpulse));
            requestParam.put("weight", String.valueOf(weight));
            requestParam.put("gender", genderString);

            JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "signup");

            if (response.get("status").equals("success")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if the first name is valid
    public static void checkFirstName(String firstName) {
        firstName.toLowerCase();
        char[] chars = firstName.toCharArray();

        for (char c : chars) {

            if (Character.isLetter(c) || (c == ' ') || (c == '-')) {
            } else {
                text = "Navnet kan kun inneholde bokstaver.";
                throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
            }
        }
        if (firstName == "") {
            text = "Du m� skrive inn et navn.";
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
    }

    // Method to check if the last name is valid
    public static void checkLastName(String surName) {
        surName.toLowerCase();
        char[] chars = surName.toCharArray();

        for (char c : chars) {

            if (Character.isLetter(c) || (c == ' ') || (c == '-')) {
            } else {
                text = "Navnet kan kun inneholde bokstaver.";
                throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
            }
        }
        if (surName == "") {
            text = "Du m� skrive inn et navn.";
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
    }

    // Method to check if the phone number is valid
    public static void checkPhoneNumber(String number) {
        if (number.length() != 8) {
            text = "Phone number must be 8 numbers.";
            throw new IllegalArgumentException("Et telefonnummer maa vaere 8 sifre langt.");
        }
//        if (number.length() != 8) {
//            text = "Phone number is already registered";
//            throw new IllegalArgumentException("Telefon");
//        }
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (!Character.isDigit(c)) {
                text = "Phone number can only contain numbers.";
                throw new IllegalArgumentException("Could not convert from string to int.");
            }
        }
    }

    // method to check if no sport is selected
    public static void checkSport(String sport) {
        if (sport == "") {
            text = "Sport field is empty.";
            throw new IllegalArgumentException("Sport field is empty");
        }
    }

    // method to check if the max pulse is valid
    public static void checkMaxPulse(String maxPulse) {
        if (Integer.parseInt(maxPulse) < 0) {
            text = "Can't have negative max pulse.";
            throw new IllegalArgumentException("Can't have negative max pulse.");
        }
    }

    // method to check if the max pulse is valid
    public static void checkWeight(String weight) {
        if (Integer.parseInt(weight) < 0) {
            text = "Can't have negative weight.";
            throw new IllegalArgumentException("Can't have negative weight.");
        }
    }

    // returns text on the feedback field
    public String getText() {
        return text;
    }

}
