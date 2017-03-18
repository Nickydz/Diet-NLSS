package com.example.nickydcruz.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leo on 18-03-2017.
 */

    public class Update_wtRequest extends StringRequest {
        private static final String BS_REQUEST_URL = "http://dietnlss.000webhostapp.com/UpdWtRequest.php";
        private Map<String, String> params;

        public Update_wtRequest(String username, String updatedwt, Response.Listener<String> listener){
            super(Method.POST, BS_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("username",username);
            params.put("updatedwt",updatedwt);

        }

        @Override
        public Map<String, String> getParams() {

            return params;
        }
    }

