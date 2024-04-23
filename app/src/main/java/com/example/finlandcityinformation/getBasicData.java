package com.example.finlandcityinformation;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Range;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.view.QWeather;
//This class is used to get basic data, including the name, ID, longitude and latitude of the city
// Classes that get the underlying data
public class getBasicData {
    public void getBasic(Context context, String inputCity,OnResultListener onResultListener) {
        QWeather.getGeoCityLookup(context, inputCity,Range.FI,1, Lang.EN, new QWeather.OnResultGeoListener() {
            public static final String TAG = "HeFeng_city";

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("Basic Data Now Error:" + new Gson());
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                if (Code.OK == geoBean.getCode()) {

                    String id = geoBean.getLocationBean().get(0).getId();
                    String name = geoBean.getLocationBean().get(0).getName();
                    String lon = geoBean.getLocationBean().get(0).getLon();
                    String lat = geoBean.getLocationBean().get(0).getLat();
                    // Store the obtained city information in the City Information class.
                    getCityInformation.getCity().setCityName(name);
                    getCityInformation.getCity().setCityID(id);
                    getCityInformation.getCity().setCityLatitude(lat);
                    getCityInformation.getCity().setCityLongitude(lon);

                    if (onResultListener != null) {
                        onResultListener.onResultBasic(geoBean);
                    }
                } else {
                    Code code = geoBean.getCode();
                    System.out.println("Failed code: " + code);
                }
            }
        });
    }
    // Define the interface for passing the fetched base data
    public interface OnResultListener {
        void onResultBasic(GeoBean geoBean);
    }
}
