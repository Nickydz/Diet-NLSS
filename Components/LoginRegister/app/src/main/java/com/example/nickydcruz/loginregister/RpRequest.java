package com.example.nickydcruz.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 1/25/2017.
 */

public class RpRequest extends StringRequest {
    private static final String RP_REQUEST_URL = "http://dietnlss.000webhostapp.com/Food1.php";
    private Map<String, String> params;

    public RpRequest(String carbs_m,String protein_m,String fat_m,String carbs_s,String protein_s,String fat_s, Response.Listener<String> listener){
        super(Method.POST, RP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("carbs_m",carbs_m);
        params.put("fat_m",fat_m);
        params.put("protein_m",protein_m);
        params.put("carbs_s",carbs_s);
        params.put("protein_s",protein_s);
        params.put("fat_s",fat_s);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
