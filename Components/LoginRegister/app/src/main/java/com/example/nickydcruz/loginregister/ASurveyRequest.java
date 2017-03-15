package com.example.nickydcruz.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 3/14/2017.
 */

public class ASurveyRequest extends StringRequest {
    private static final String AS_REQUEST_URL = "http://dietnlss.000webhostapp.com/ASRequest.php";
    private Map<String, String> params;

    public ASurveyRequest(String username,String height,String prommeal,String noofmeal,String prefdrink,String activitylevel, Response.Listener<String> listener){
        super(Method.POST, AS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("prommeal",prommeal);
        params.put("noofmeal",noofmeal);
        params.put("prefdrink",prefdrink);
        params.put("activitylevel",activitylevel);
        params.put("height", height);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}