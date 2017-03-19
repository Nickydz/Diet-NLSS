package com.example.nickydcruz.loginregister;

import java.math.RoundingMode;
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

    public float idealwtone(float hieght, float wieght){
        float w1 = 18.5f * (hieght*hieght);
        return w1;
    }

    public float idealwttwo(float hieght, float wieght){
        float w2 = 24.9f * (hieght*hieght);
        return w2;
    }

    public String category(float bmi){
        String category;
        if(bmi >0 && bmi<16){
            category = "You are Very Severely Underweight";
        }
        else if (bmi >= 16 && bmi < 17){
            category = "You are Severely Underweight";
        }
        else if (bmi >= 16 && bmi < 17){
            category = "You are Underweight";
        }
        else if (bmi >= 17 && bmi < 18.5){
            category = "You are Mildly Underweight";
        }
        else if (bmi >= 18.5 && bmi < 25){
            category = "Awesome!! You have a Healthy weight";
        }
        else if (bmi >= 25 && bmi < 30){
            category = "You are Overweight";
        }
        else if (bmi >= 30 && bmi < 35){
            category = "You are Moderately obese";
        }
        else if (bmi >= 35 && bmi < 40){
            category = "You are Severely obese";
        }
        else if (bmi >= 40){
            category = "You are Very Severely obese";
        }
        else{
            category = "You are a very unique specimen in human genealogy";
        }

        return category;
    }

    public int bmr(String gender, int age, float wieght, float hieght) {
        Double bmr1;
        if (gender.equals("M")){
            bmr1 = (10*wieght)+(6.25*hieght)+(5*age)+5;
        }
        else {
            bmr1 =(10*wieght)+(6.25*hieght)+(5*age)-161;
        }
        int bmr = bmr1.intValue();
        return bmr;
    }

    public int[] bmrcal(double bmr){
        int a[]= new int[3];
        a[0] = (int) ((bmr *0.5)/4);
        a[1] = (int) ((bmr *0.3)/4);
        a[2] = (int)  ((bmr *0.2)/9);
        return a;
    }
}
