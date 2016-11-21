package com.idroid.app.taxioncliq.uber;

import android.util.Log;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.model.Product;
import com.uber.sdk.rides.client.model.ProductsResponse;
import com.uber.sdk.rides.client.model.Ride;
import com.uber.sdk.rides.client.model.RideEstimate;
import com.uber.sdk.rides.client.model.RideRequestParameters;
import com.uber.sdk.rides.client.services.RidesService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Aron on 20-11-2016.
 */

public class UberUtil {
    private static final String TAG = UberUtil.class.getSimpleName();

    /**
     * Method to initialize the uber sdk
     */
    public static void initialize() {
        SessionConfiguration config = new SessionConfiguration.Builder().setClientId(UberConstants.CLIENT_ID).setEnvironment(SessionConfiguration.Environment.SANDBOX).build();
        UberSdk.initialize(config);
    }

    /**
     * Method to get the products available in the source location
     *
     * @param service
     * @param aLatitude
     * @param aLongitude
     * @return
     */
    public static List<Product> getProductsForSource(RidesService service, float aLatitude, float aLongitude) {

        Response<ProductsResponse> response = null;
        try {
            response = service.getProducts(aLatitude, aLongitude).execute();
        } catch (IOException e) {
            Log.e(TAG, "Exception :: getProductsForSource() :: ", e);
        }
        ProductsResponse products = response.body();

        return products.getProducts();
    }

    /**
     * Method to get the ride estimate for a product
     *
     * @param service
     * @param aProductId
     * @param aSourceLat
     * @param aSourceLong
     * @param aDestLat
     * @param aDestLong
     * @return
     */
    public static RideEstimate getRideEstimates(RidesService service, String aProductId, float aSourceLat, float aSourceLong, float aDestLat, float aDestLong) {
        RideRequestParameters rideRequestParameters = new RideRequestParameters.Builder().setPickupCoordinates(aSourceLat, aSourceLong)
                .setProductId(aProductId)
                .setDropoffCoordinates(aDestLat, aDestLong)
                .build();
        RideEstimate rideEstimate = null;
        try {
            rideEstimate = service.estimateRide(rideRequestParameters).execute().body();

        } catch (IOException e) {
            Log.e(TAG, "Exception :: getRideEstimates() :: ", e);
        }
        return rideEstimate;
    }

    /**
     * Method to request a ride
     *
     * @param service
     * @param aProductId
     * @param aFairId
     * @param aSourceLat
     * @param aSourceLong
     * @param aDestLat
     * @param aDestLong
     * @return
     */

    public static Ride requestRide(RidesService service, String aProductId, String aFairId, float aSourceLat, float aSourceLong, float aDestLat, float aDestLong) {
        RideRequestParameters rideRequestParameters = new RideRequestParameters.Builder().setPickupCoordinates(aSourceLat, aSourceLong)
                .setProductId(aProductId)
                .setFareId(aFairId)
                .setDropoffCoordinates(aDestLat, aDestLong)
                .build();
        Ride ride = null;
        try {
            ride = service.requestRide(rideRequestParameters).execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Exception :: requestRide() :: ", e);
        }
        return ride;
    }

    /**
     * Method to get the ride details for a ride
     *
     * @param service
     * @param aRideId
     * @return
     */
    public static Ride getRideDetails(RidesService service, String aRideId) {
        Ride ride = null;
        try {
            ride = service.getRideDetails(aRideId).execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Exception :: getRideDetails() :: ", e);
        }

        return ride;
    }

    /**
     * Method to cancel the ride
     *
     * @param service
     * @param aRideId
     * @return
     */
    public static boolean cancelRide(RidesService service, String aRideId) {
        Response<Void> response;
        try {
            response = service.cancelRide(aRideId).execute();
            return response.isSuccessful();
        } catch (IOException e) {
            Log.e(TAG, "Exception :: cancelRide() :: ", e);
        }

        return false;
    }

}


