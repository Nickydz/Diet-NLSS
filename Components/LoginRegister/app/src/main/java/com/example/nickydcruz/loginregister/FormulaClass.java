package com.example.nickydcruz.loginregister;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nicky D. Cruz on 1/15/2017.
 */
public class FormulaClass {

    public int calculateAge(String dob) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar dob1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        try {
            Date date = format.parse(dob);
            dob1.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int age = today.get(Calendar.YEAR) - dob1.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob1.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }


    public float bmi(float hieght, float wieght) {
        float bmi = wieght/(hieght*hieght);
        return bmi;
    }

    public double bmr(String gender, int age, float wieght, float hieght) {
        double bmr;
        if (gender.equals("M")){
            bmr =(10*wieght)+(6.25*hieght)+(5*age)+5;
        }
        else {
            bmr =(10*wieght)+(6.25*hieght)+(5*age)-161;
        }
        return bmr;
    }
}
