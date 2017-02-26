//package com.example.nlss.custom_diet;
//
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Leo on 26-02-2017.
// */
//
//public class CustomRequest extends StringRequest {
//    private static final String Custom_REQUEST_URL = "http://dietnlss.000webhostapp.com/Custom.php";
//    private Map<String, String> params;
//
//    public CustomRequest(String username,Response.Listener<String> listener) {
//        super(Method.POST, Custom_REQUEST_URL, listener,null);
//        params = new HashMap<>();
//        params.put("username",username);
//    }
//
//    @Override
//    public Map<String, String> getParams() {
//
//        return params;
//    }
//}