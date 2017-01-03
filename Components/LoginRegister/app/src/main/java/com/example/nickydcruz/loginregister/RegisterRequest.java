package com.example.nickydcruz.loginregister;

import android.support.v7.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky D. Cruz on 1/2/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://dietnlss.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String email, String password, String dob, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("email",email.toString());
        params.put("password",password);
        params.put("dob", dob);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
