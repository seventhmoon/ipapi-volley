package com.androidfung.geoip.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.androidfung.geoip.model.IpApiResponseModel;
import com.androidfung.volley.toolbox.GsonObjectRequest;
import com.androidfung.volley.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fung LAM on 04/12/2014.
 */
public class ApiManager {

    private static final String TAG = ApiManager.class.getSimpleName();

//    private static final String ENDPOINT = "http://ip-api.com/json";
    private static final String ENDPOINT = "https://ipapi.co/json";
    private RequestQueue mRequestQueue;

    public ApiManager(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
    }

    public void getGeoIpInfo(Response.Listener<IpApiResponseModel> listener, Response.ErrorListener errorListener){

        GsonObjectRequest gsonReq = new GsonObjectRequest(Request.Method.GET, ENDPOINT
                , IpApiResponseModel.class, null, listener, errorListener){
            public Map<String,String> getHeaders(){

                //    @Headers("User-Agent: java-ipapi-client")
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-agent", "java-ipapi-client");
                return headers;
            }
        };
        mRequestQueue.add(gsonReq);
    }


    private static String toUrlParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            try {
                sb.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(1);
    }


}
