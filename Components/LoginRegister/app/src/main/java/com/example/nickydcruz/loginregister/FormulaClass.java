package com.example.nickydcruz.loginregister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nicky D. Cruz on 1/12/2017.
 */

public class FormulaClass {
    public int calculateAge(String birthday){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        try {
            Date d =sdf.parse(birthday);
            dob.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }

    public float bmi(float h,float w){
        float bmi = w/(h*h);
        return bmi;
    }

    public double bmr(String gender,int age,float w,float h){
        double bmr;
        h= h*100;
        if(gender.equals("M")){
            bmr = (10*w)+(6.25 * h)-(5*age)+ 5;
        }
        else
            bmr = (10*w)+(6.25 * h)-(5*age)- 161;

        return bmr;
    }
}
