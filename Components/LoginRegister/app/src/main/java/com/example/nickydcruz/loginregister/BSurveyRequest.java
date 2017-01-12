package com.example.nickydcruz.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 1/12/2017.
 */

public class BSurveyRequest extends StringRequest {
    private static final String SURVEY_REQUEST_URL = "http://dietnlss.000webhostapp.com/BSRequest.php";
    private Map<String, String> params;

    public BSurveyRequest(String username,String dob,String gender,String height,String weight,String wristCir,Response.Listener<String> listener){
        super(Method.POST, SURVEY_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("dob",dob);
        params.put("gender",gender);
        params.put("height",height);
        params.put("weight",weight);
        params.put("wristCir",wristCir);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
