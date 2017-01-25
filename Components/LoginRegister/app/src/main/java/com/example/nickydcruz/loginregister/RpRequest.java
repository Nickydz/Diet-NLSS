package com.example.nickydcruz.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 1/25/2017.
 */

public class RpRequest extends StringRequest {
    private static final String RP_REQUEST_URL = "http://dietnlss.000webhostapp.com/BSRequest.php";
    private Map<String, String> params;

    public RpRequest(String carbs_m,String protein_m,String fat_m,String carbs_p,String protein_p,String fat_p, Response.Listener<String> listener){
        super(Method.POST, RP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("carbs_m",carbs_m);
        params.put("fat_m",fat_m);
        params.put("protein_m",protein_m);
        params.put("carbs_p",carbs_p);
        params.put("protein_p",protein_p);
        params.put("fat_p",fat_p);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
