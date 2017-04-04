package com.example.nickydcruz.loginregister;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 4/5/2017.
 */

public class UsernameExists extends StringRequest{

    private static final String AS_REQUEST_URL = "http://dietnlss.000webhostapp.com/UserExists.php";
    private Map<String, String> params;

    public UsernameExists(String username, Response.Listener<String> listener){
        super(Request.Method.POST, AS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
