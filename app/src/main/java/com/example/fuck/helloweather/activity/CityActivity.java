package com.example.fuck.helloweather.activity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuck.helloweather.R;
import com.example.fuck.helloweather.db.City;
import com.example.fuck.helloweather.db.County;
import com.example.fuck.helloweather.db.Province;
import com.example.fuck.helloweather.util.HttpUtil;
import com.example.fuck.helloweather.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityActivity extends AppCompatActivity {
    private List<String> provinceList = new ArrayList<>();
    private List<String> provinceIdList = new ArrayList<>();
    private List<String>cityList = new ArrayList<>();
    private List<String>cityIdList = new ArrayList<>();
    private List<String>countyList = new ArrayList<>();
    private List<String>weatherIdList = new ArrayList<>();
    private List<Province>provinces = new ArrayList<>();
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countyAdapter;
    private ListView             listView;
    private Button          backButton;
    private TextView             title;
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final  int LEVEL_COUNTY = 2;
    /**
     * 当前选中的级别
     */
    private int            currentLevel;
    @Override
    protected void onCreate(Bundle saveInstanceSate){
        super.onCreate(saveInstanceSate);
        setContentView(R.layout.activity_city);
        initView();
        handleProvinceResponse();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel==LEVEL_CITY){
                    handleProvinceResponse();
                }else if (currentLevel==LEVEL_COUNTY){

                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel==LEVEL_PROVINCE){
                    handleCityResponse(provinceIdList.get(i));
                }else if (currentLevel==LEVEL_CITY){
                    handleCountyResponse(provinceIdList.get(i),cityIdList.get(i));
                }else if (currentLevel==LEVEL_COUNTY){
                  //Toast.makeText(CityActivity.this,weatherIdList.get(i),Toast.LENGTH_SHORT).show();
                        String[]arry=weatherIdList.get(i).split("N");
                        Intent intent = new Intent(CityActivity.this,WeatherActivity.class);
                        intent.putExtra("weather_id",arry[1]);
                        intent.putExtra("isForHttp",true);
                        startActivity(intent);
                        finish();


                }
            }
        });
    }
    private void initView(){
        listView = findViewById(R.id.list_view);
        backButton = findViewById(R.id.back_button);
        title = findViewById(R.id.title);
    }

    /**
     * 发送网络请求
     */
    private void handleProvinceResponse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendOkHttpRequest("http://guolin.tech/api/china", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONArray allProvinces = new JSONArray(response.body().string());
                            for (int i=0;i<allProvinces.length();i++){
                                JSONObject provincesObject = allProvinces.getJSONObject(i);
                                provinceList.add(provincesObject.getString("name"));
                                provinceIdList.add(provincesObject.getString("id"));
                                /*Province province = new Province();
                                province.setProvinceName(provincesObject.getString("name"));
                                province.setProvinceCode(provincesObject.getInt("id"));
                                province.save();*/
                            }

                            showProvinceList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
    private void handleCityResponse(final String provinceId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendOkHttpRequest("http://guolin.tech/api/china/" + provinceId, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONArray allCity = new JSONArray(response.body().string());
                            for (int i=0;i<allCity.length();i++){
                                JSONObject cityObject = allCity.getJSONObject(i);
                                cityList.add(cityObject.getString("name"));
                                cityIdList.add(cityObject.getString("id"));
                                /*City city = new City();
                                city.setCityName(cityObject.getString("name"));
                                city.setCityCode(cityObject.getInt("id"));
                                city.setProvinceId(Integer.parseInt(provinceId));
                                city.save();*/
                            }
                            showCityList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
    private void handleCountyResponse(final String provinceId, final String cityId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendOkHttpRequest("http://guolin.tech/api/china/" + provinceId + "/" + cityId, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONArray allCounty = new JSONArray(response.body().string());
                            for (int i=0;i<allCounty.length();i++){
                                JSONObject countyObject = allCounty.getJSONObject(i);
                                countyList.add(countyObject.getString("name"));
                                weatherIdList.add(countyObject.getString("weather_id"));
                               /* County county = new County();
                                county.setCountyName(countyObject.getString("name"));
                                county.setWeatherId(countyObject.getString("weather_id"));
                                county.setCityId(Integer.parseInt(cityId));
                                county.save();*/
                            }
                                showCountyList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
    private void showProvinceList(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                provinceAdapter = new ArrayAdapter<String>(CityActivity.this,android.R.layout.simple_list_item_1,provinceList);
                provinceAdapter.notifyDataSetChanged();
                listView.setSelection(0);
                listView.setAdapter(provinceAdapter);
                currentLevel=LEVEL_PROVINCE;
                backButton.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void showCityList(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityAdapter = new ArrayAdapter<String>(CityActivity.this,android.R.layout.simple_list_item_1,cityList);
                cityAdapter.notifyDataSetChanged();
                listView.setSelection(0);
                listView.setAdapter(cityAdapter);
                currentLevel=LEVEL_CITY;
                backButton.setVisibility(View.VISIBLE);
            }
        });
    }
    private void showCountyList(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                countyAdapter = new ArrayAdapter<String>(CityActivity.this,android.R.layout.simple_list_item_1,countyList);
                countyAdapter.notifyDataSetChanged();
                listView.setSelection(0);
                listView.setAdapter(countyAdapter);
                currentLevel=LEVEL_COUNTY;
            }
        });
    }
    private void showProvinceListForDb(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                provinces = LitePal.findAll(Province.class);
                if (provinces.size()>0){
                    provinceList.clear();
                    for (Province province:provinces){
                        provinceList.add(province.getProvinceName());

                    }
                    provinceAdapter.notifyDataSetChanged();


                }
            }
        });
    }
}
