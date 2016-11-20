package com.idroid.app.taxioncliq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.idroid.app.taxioncliq.network.SingleTon;
import com.idroid.app.taxioncliq.objects.CancelStatus;
import com.idroid.app.taxioncliq.util.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Method to place a request for getting ola product details
     */
    private void createProductRequestForSource(String aSourceLat, String aSourceLong) {
        String lUrl = Constants.OLA_BASE_URI + "/v1/products?pickup_lat=" + aSourceLat + "&pickup_lng=" + aSourceLong;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createProductRequestForSource() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createProductRequestForSource() :: onErrorResponse() ::" + error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                Log.d(TAG, "createProductRequestForSource() :: getHeaders() :: called ");
                headers.put(Constants.X_APP_TOKEN_KEY, Constants.X_APP_TOKEN_VALUE);
                return headers;
            }
        };
        SingleTon.getInstance(this).addToRequestQueue(jsObjRequest);
    }


    /**
     * Method to place a request for getting ride estimation between source and destination
     */
    private void createRideEstimateRequest(String aSourceLat, String aSourceLong, String aDestLat, String aDestLong) {
        String lUrl = Constants.OLA_BASE_URI + "/v1/products?pickup_lat=" + aSourceLat + "&pickup_lng=" + aSourceLong + "&drop_lat=" + aDestLat + "&drop_lng=" + aDestLong;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createRideEstimateRequest() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createRideEstimateRequest() :: onErrorResponse() ::" + error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                Log.d(TAG, "createRideEstimateRequest() :: getHeaders() :: called ");
                headers.put(Constants.X_APP_TOKEN_KEY, Constants.X_APP_TOKEN_VALUE);
                return headers;
            }
        };
        SingleTon.getInstance(this).addToRequestQueue(jsObjRequest);
    }


    /**
     * Method to place a booking request
     */
    private void createBookingRequest(final String aSourceLat, final String aSourceLong, final String aDestLat, final String aDestLong, final String aPickupMode, final String aCategory) {
        String lUrl = Constants.OLA_BASE_URI + "/v1/bookings/create";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createBookingRequest() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createBookingRequest() :: onErrorResponse() ::" + error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                Log.d(TAG, "createBookingRequest() :: getHeaders() :: called ");
                headers.put(Constants.X_APP_TOKEN_KEY, Constants.X_APP_TOKEN_VALUE);
                //TODO:
                headers.put(Constants.AUTHORIZATION_KEY, Constants.BEARER_STRING + "????");
                headers.put(Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE);
                return headers;
            }

            //For passing the value in the body
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> lBodyMap = new HashMap<String, String>();
                lBodyMap.put(Constants.PICK_AT_LAT_KEY, aSourceLat);
                lBodyMap.put(Constants.PICK_AT_LONG_KEY, aSourceLong);
                lBodyMap.put(Constants.DROP_AT_LAT_KEY, aDestLat);
                lBodyMap.put(Constants.DROP_AT_LONG_KEY, aDestLong);
                lBodyMap.put(Constants.PICK_UP_MODE_KEY, aPickupMode);
                lBodyMap.put(Constants.CATEGORY_KEY, aCategory);
                return lBodyMap;
            }
        };

        SingleTon.getInstance(this).addToRequestQueue(jsObjRequest);
    }


    /**
     * Method to place a request to get the tracking details for a particular booking id
     */
    private void createTrackRideRequest(String aBookingId) {
        String lUrl = Constants.OLA_BASE_URI + "v1/bookings/track_ride?booking_id=" + aBookingId;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createTrackRideRequest() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createTrackRideRequest() :: onErrorResponse() ::" + error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                Log.d(TAG, "createTrackRideRequest() :: getHeaders() :: called ");
                headers.put(Constants.X_APP_TOKEN_KEY, Constants.X_APP_TOKEN_VALUE);
                headers.put(Constants.AUTHORIZATION_KEY, Constants.BEARER_STRING + "????");
                return headers;
            }
        };

        SingleTon.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    /**
     * Method to place a booking request
     */
    private void createCancelBookingRequest(final String aBookingId, final String aReason) {
        String lUrl = Constants.OLA_BASE_URI + "v1/bookings/cancel";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson lGson = new Gson();
                        CancelStatus lStatus = lGson.fromJson(response.toString(), CancelStatus.class);
                        Log.d(TAG, "createBookingRequest() :: onResponse() ::" + lStatus);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createBookingRequest() :: onErrorResponse() ::" + error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                Log.d(TAG, "createBookingRequest() :: getHeaders() :: called ");
                headers.put(Constants.X_APP_TOKEN_KEY, Constants.X_APP_TOKEN_VALUE);
                //TODO:
                headers.put(Constants.AUTHORIZATION_KEY, Constants.BEARER_STRING + "????");
                headers.put(Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE);
                return headers;
            }

            //For passing the value in the body
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> lBodyMap = new HashMap<String, String>();
                lBodyMap.put(Constants.BOOKING_ID_KEY, aBookingId);
                lBodyMap.put(Constants.REASON_KEY, aReason);
                return lBodyMap;
            }
        };

        SingleTon.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
