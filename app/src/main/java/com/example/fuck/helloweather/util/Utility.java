package com.example.fuck.helloweather.util;

import android.text.TextUtils;

import com.example.fuck.helloweather.db.City;
import com.example.fuck.helloweather.db.County;
import com.example.fuck.helloweather.db.Province;
import com.example.fuck.helloweather.jsonbean.WeatherBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Utility {
    /**
     *
     * @param list
     * @return 返回arry
     */
    public static int[] listToArray(ArrayList<Integer>list){
        int[]array = new int[list.size()]; //创建一个和list一样长度的arry
        for (int i=0;i<list.size();i++){
            array[i]=list.get(i); //通过循环遍历将list中的数据导入arry
        }
        return array;
    }

    /**
     * 重载上面的方法
     *
     * @param list
     * @return
     */
    public static String[] listToArry(ArrayList<String>list){
        String[]arry = new String[list.size()];
        for (int i=0;i<list.size();i++){
            arry[i]=list.get(i);
        }
        return arry;
    }

    /**
     * 处理最高温度数据
     * @param highTmpList
     * @param weatherBean
     * @return
     */
    public static ArrayList<Integer> handlleHighTmpData(ArrayList<Integer> highTmpList, WeatherBean weatherBean){
        String[] str1 = weatherBean.getData().getYesterday().getHigh().split(" ");
        String[] str2 = str1[1].split("\\.");//特殊符号需要转义
        highTmpList.add(Integer.parseInt(str2[0]));
        for (int i = 1; i < 6; i++) {
            String[] str3 = weatherBean.getData().getForecast().get(i).getHigh().split(" ");
            String[] str4 = str3[1].split("\\.");
            highTmpList.add(Integer.parseInt(str4[0]));
        }
        return highTmpList;
    }

    /**
     * 处理最低温度数据
     * @param lowTmpList
     * @param weatherBean
     * @return
     */
    public static ArrayList<Integer> handleLowTmpData(ArrayList<Integer> lowTmpList,WeatherBean weatherBean){
        String[] str1 = weatherBean.getData().getYesterday().getLow().split(" ");
        String[] str2 = str1[1].split("\\.");
        lowTmpList.add(Integer.parseInt(str2[0]));
        for (int j = 1; j < 6; j++) {
            String[] str3 = weatherBean.getData().getForecast().get(j).getLow().split(" ");
            String[] str4 = str3[1].split("\\.");
            lowTmpList.add(Integer.parseInt(str4[0]));
        }
        return lowTmpList;
    }
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provincesObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provincesObject.getString("name"));
                    province.setProvinceCode(provincesObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCitys = new JSONArray(response);
                for (int i = 0;i<allCitys.length();i++){
                    JSONObject citysJSONObject = allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityName(citysJSONObject.getString("name"));
                    city.setCityCode(citysJSONObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0;i<allCounties.length();i++){
                    JSONObject countyJSONObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyJSONObject.getString("name"));
                    county.setWeatherId(countyJSONObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static void saveProvinceDataToDb(String response){
        try {
            JSONArray allProvinceObject = new JSONArray(response);
            for (int i=0;i<allProvinceObject.length();i++){
                JSONObject provinceObject =allProvinceObject.getJSONObject(i);
                Province province = new Province();
                province.setProvinceCode(provinceObject.getInt("id"));
                province.setProvinceName(provinceObject.getString("name"));
                province.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
