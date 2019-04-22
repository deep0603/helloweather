package com.example.fuck.helloweather.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.fuck.helloweather.R;
import com.example.fuck.helloweather.db.WeatherId;
import com.example.fuck.helloweather.jsonbean.WeatherIdBean;
import com.google.gson.Gson;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    public  LocationClient     mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private TextView textView;
    private List<WeatherId> weatherIdList = new ArrayList<>();
    private String locationAdd ;
    private String weatherId;
    private Button button;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location);
        textView = findViewById(R.id.text_view);
        button =findViewById(R.id.button);
        /*
        申请危险权限，首先创建一个空的List集合，然后依次判断这个几个权限有没有被授权，如果没有被授权就添加到List集合中，最后将List转换成数组，再调用ActivityCompat.
        requesstPermissions()方法一次申请。
         */
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationActivity.this,permissions,1);
        }else {
            requestLocation();
        }
        //queryCityCode();
    }
    private void requestLocation(){
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        mLocationClient.setLocOption(option);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setIgnoreKillProcess(true);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            StringBuilder stringBuilder = new StringBuilder();
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();//获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();//获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            stringBuilder.append("纬度：");
            stringBuilder.append(location.getLatitude());
            stringBuilder.append("\n");

            stringBuilder.append("纬度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("\n");

            stringBuilder.append("定位精度：");
            stringBuilder.append(location.getRadius());
            stringBuilder.append("\n");

            stringBuilder.append("国家：");
            stringBuilder.append(location.getCountry());
            stringBuilder.append("\n");

            stringBuilder.append("省份：");
            stringBuilder.append(location.getProvince());
            stringBuilder.append("\n");

            stringBuilder.append("区县：");
            stringBuilder.append(location.getDistrict());
            stringBuilder.append("\n");

            stringBuilder.append("街道:");
            stringBuilder.append(location.getStreet());
            stringBuilder.append("\n");

            stringBuilder.append("详细地址：");
            stringBuilder.append(location.getAddrStr());
            stringBuilder.append("\n");

            stringBuilder.append("定位方式：");
            if (location.getLocType() == BDLocation.TypeGpsLocation){
                stringBuilder.append("GPS").append("\n");
            }else if (location.getLocType() ==BDLocation.TypeNetWorkLocation){
                stringBuilder.append("网络").append("\n");
            }

                String a = "..";
                String s=location.getCity()+a;
                String[]arry=s.split("市");
                locationAdd=arry[0];

            textView.setText(stringBuilder);
            Log.e("LocationTag", String.valueOf(latitude));
            Log.e("LocationTag", String.valueOf(longitude));
            Log.e("LocationTag", String.valueOf(radius));
            Log.e("LocationTag", String.valueOf(coorType));
            Log.e("LocationTag", String.valueOf(errorCode));
            Log.e("LocationTag", String.valueOf(addr));
            Log.e("LocationTag", String.valueOf(country));
            Log.e("LocationTag", String.valueOf(province));
            Log.e("LocationTag", String.valueOf(city));
            Log.e("LocationTag", String.valueOf(district));
            Log.e("LocationTag", String.valueOf(street));
            if (location.getProvince()==null){
                Toast.makeText(LocationActivity.this,"请打开定位服务",Toast.LENGTH_SHORT).show();
                button.setVisibility(View.INVISIBLE);
            }else {
                button.setVisibility(View.VISIBLE);
            }

        }
    }
    private void queryCityCode() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    weatherIdList = LitePal.where("name =?", locationAdd).find(WeatherId.class);
                    weatherId = weatherIdList.get(0).getCityCode();
                    Log.e("tag",weatherId);
                }
            }).start();

    }
    private void saveWeatherIdToDb(){
        AVQuery<AVObject> avQuery =new AVQuery<>("HelloWeather");
        avQuery.getInBackground("5cb96e70a3180b78329d3a10" ,new GetCallback<AVObject>() {
                    @Override
                    public void done(AVObject object, AVException e) {
                        final String weatherIdResponse = object.getString("Json");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                WeatherIdBean weatherIdBean = gson.fromJson(weatherIdResponse,WeatherIdBean.class);
                                for (WeatherIdBean.DataBean dataBean : weatherIdBean.getData()){
                                    WeatherId weatherId = new WeatherId();
                                    weatherId.setCityCode(dataBean.getCity_code());
                                    weatherId.setName(dataBean.getCity_name());
                                    weatherId.save();
                                }

                            }
                        }).start();
                    }
                }
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int [] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length> 0){
                    for(int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

}
