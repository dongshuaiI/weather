package com.example.chang.myweather.util;

import android.text.TextUtils;

import com.example.chang.myweather.db.City;
import com.example.chang.myweather.db.County;
import com.example.chang.myweather.db.Province;
import com.example.chang.myweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static boolean handleProvinceResponse(String response){

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces=new JSONArray(response);


                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provincesJSONObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provincesJSONObject.getString("name"));
                    province.setProvinceCode(provincesJSONObject.getInt("id"));
                    province.setId(provincesJSONObject.getInt("id"));
                    province.save();
                }
                return true;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject cityObject = jsonArray.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean handleCountyResponse(String response,int cityId){

        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject cityObject = jsonArray.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(cityObject.getString("name"));
                    county.setWeatherId(cityObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    public static Weather handleWeatherResponse(String response){

        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}
