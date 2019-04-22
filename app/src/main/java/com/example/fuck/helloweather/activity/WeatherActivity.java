package com.example.fuck.helloweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.fuck.helloweather.db.WeatherId;
import com.example.fuck.helloweather.jsonbean.WeatherBean;
import com.example.fuck.helloweather.jsonbean.WeatherIdBean;
import com.example.fuck.helloweather.util.Utility;
import com.example.fuck.helloweather.view.CircleBarView;
import com.example.fuck.helloweather.R;
import com.example.fuck.helloweather.view.WeatherChartView;
import com.example.fuck.helloweather.util.LinearGradientUtil;
import com.google.gson.Gson;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
public class WeatherActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private LinearLayout forecastLayout;
    private TextView     toolbarTitle;
    private TextView     toolbarUpdate;
    private TextView     nowTmp;
    private TextView     nowWeather;
    private TextView     nowFengXiang;
    private TextView     nowWindLevel;
    private TextView     nowShiDu;
    private TextView     nowNotice;
    private TextView     nowAQI;
    private TextView     nowPM25;
    private TextView     nowPM10;
    private ArrayList<Integer> highTmpList = new ArrayList<>();
    private ArrayList<Integer> lowTmpList  = new ArrayList<>();
    private String             weatherId ;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //让状态栏变透明
        if (Build.VERSION.SDK_INT >= 21) {//将状态栏设置成透明色（本功能只有在版本号大于或等于21，也就是Android5.0及以上系统才会执行此代码	）
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bar_color));
        }
        setContentView(R.layout.activity_weather);
        initView();
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        weatherId = intent.getStringExtra("weather_id");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null && !intent.getBooleanExtra("isForHttp", false)) { //判断缓存
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(weatherString, WeatherBean.class);
            showWeatherData(weatherBean);
        } else {
            queryWeather(weatherId);
        }


    }
    /**
     * 初始化控件
     */
    private void initView() {
        toolbar = findViewById(R.id.toolbar);

        forecastLayout = findViewById(R.id.forecase_layout);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarUpdate = findViewById(R.id.toolbar_update);
        nowTmp = findViewById(R.id.now_tmp);
        nowWeather = findViewById(R.id.now_weather);
        nowFengXiang = findViewById(R.id.now_fengxiang);
        nowWindLevel = findViewById(R.id.now_wendlevel);
        nowShiDu = findViewById(R.id.now_shidu);
        nowNotice = findViewById(R.id.now_notice);
        nowAQI = findViewById(R.id.now_aqi);
        nowPM25 = findViewById(R.id.air_qualit_pm25);
        nowPM10 = findViewById(R.id.air_qualit_pm10);
    }

    /**
     * 初始化未来4天模块的布局
     * @param weatherBean
     */
    private void initForecast(WeatherBean weatherBean) {
        forecastLayout.removeAllViews();

        for (int i = 0; i < 6; i++) {
            View view = LayoutInflater.from(WeatherActivity.this).inflate(R.layout.item_forecast, forecastLayout, false);
            TextView dateText = view.findViewById(R.id.forecase_date);
            TextView weatherText = view.findViewById(R.id.forecase_weather);
            TextView shiduText = view.findViewById(R.id.forecase_shidu);
            TextView windSpeedText = view.findViewById(R.id.forecase_wind_speed);
            ImageView weatherIcon = view.findViewById(R.id.forecase_weather_icon);
            if (i == 0) {
                dateText.setText("昨天");
                weatherText.setText(weatherBean.getData().getYesterday().getType());
                shiduText.setText("80%");
                windSpeedText.setText(weatherBean.getData().getYesterday().getFl());
                if (weatherBean.getData().getYesterday().getType().equals("阵雪")) {
                    weatherIcon.setImageResource(R.drawable.ic_xue);
                } else if (weatherBean.getData().getYesterday().getType().equals("晴")) {
                    weatherIcon.setImageResource(R.drawable.ic_qing);
                } else if (weatherBean.getData().getYesterday().getType().equals("小雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getYesterday().getType().equals("中雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getYesterday().getType().equals("大雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getYesterday().getType().equals("阴")) {
                    weatherIcon.setImageResource(R.drawable.ic_yin);
                } else if (weatherBean.getData().getYesterday().getType().equals("多云")) {
                    weatherIcon.setImageResource(R.drawable.ic_duoyun);
                } else if (weatherBean.getData().getYesterday().getType().equals("雨夹雪")) {
                    weatherIcon.setImageResource(R.drawable.ic_yujiaxue);
                }
            } else {

                dateText.setText(weatherBean.getData().getForecast().get(i).getWeek());
                weatherText.setText(weatherBean.getData().getForecast().get(i).getType());
                shiduText.setText("80%");
                windSpeedText.setText(weatherBean.getData().getForecast().get(i).getFl());
                if (weatherBean.getData().getForecast().get(i).getType().equals("阵雪")) {
                    weatherIcon.setImageResource(R.drawable.ic_xue);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("晴")) {
                    weatherIcon.setImageResource(R.drawable.ic_qing);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("小雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("中雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("大雨")) {
                    weatherIcon.setImageResource(R.drawable.ic_xiaoyu_yu);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("阴")) {
                    weatherIcon.setImageResource(R.drawable.ic_yin);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("多云")) {
                    weatherIcon.setImageResource(R.drawable.ic_duoyun);
                } else if (weatherBean.getData().getForecast().get(i).getType().equals("雨夹雪")) {
                    weatherIcon.setImageResource(R.drawable.ic_yujiaxue);
                }

            }

            forecastLayout.addView(view);
        }
    }

    /**
     * 初始化温度折线图并加载温度数据
     * @param weatherBean
     */
    private void initWeatherChartView(WeatherBean weatherBean) {
        WeatherChartView mCharView = findViewById(R.id.line_char);
        // set day
        mCharView.setTempDay(Utility.listToArray(
                Utility.handlleHighTmpData(highTmpList, weatherBean)
                )
        );
        // set night
        mCharView.setTempNight(Utility.listToArray(
                Utility.handleLowTmpData(lowTmpList,weatherBean)
                )
        );
        mCharView.invalidate();
    }
    /**
     * 发起网络请求并解析Json数据
     * 将解析的数据返回到WeatherBean
     */
    private void queryWeather(String weatherId) {
        Request request = ItheimaHttp.newGetRequest("/api/weather/city/"+weatherId);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(response,WeatherBean.class);
                if (weatherBean!=null &&"200".equals(String.valueOf(weatherBean.getStatus()))){
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();//将数据缓存到SP中
                    editor.putString("weather",response);
                    editor.apply();
                    showWeatherData(weatherBean);
                }else {
                    Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {

            }
        });
    }

    /**
     * 给UI布局的控件加载数据
     * @param weatherBean
     */
    private void showWeatherData(WeatherBean weatherBean) {
        toolbarTitle.setText(weatherBean.getCityInfo().getCity());
        toolbarUpdate.setText("更新于" + weatherBean.getCityInfo().getUpdateTime());
        nowTmp.setText(weatherBean.getData().getWendu());
        nowWeather.setText(weatherBean.getData().getForecast().get(0).getType());
        nowFengXiang.setText(weatherBean.getData().getForecast().get(0).getFx());
        nowWindLevel.setText(weatherBean.getData().getForecast().get(0).getFl());
        nowShiDu.setText(weatherBean.getData().getShidu());
        nowNotice.setText(weatherBean.getData().getForecast().get(0).getNotice());
        nowAQI.setText(String.valueOf(weatherBean.getData().getForecast().get(0).getAqi()));
        nowPM25.setText(String.valueOf(weatherBean.getData().getPm25()));
        nowPM10.setText(String.valueOf(weatherBean.getData().getPm10()));
        initCircleBarView(weatherBean.getData().getForecast().get(0).getAqi());
        initForecast(weatherBean);
        initWeatherChartView(weatherBean);
    }

    /**
     * 初始化AQI圆弧进度条
     * @param aqiNum
     *
     */
    private void initCircleBarView(float aqiNum) {
        CircleBarView circleBarView = findViewById(R.id.circle_view);
        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * updateNum / maxNum * 100) + "%";
                return s;
            }
            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {
                LinearGradientUtil linearGradientUtil = new LinearGradientUtil(getResources().getColor(R.color.yellow), getResources().getColor(R.color.line_green));
                paint.setColor(linearGradientUtil.getColor(interpolatedTime));
            }
        });
        float i = (1 - (aqiNum / 800)) * 100; //根据空气质量0-800范围计算百分比 0为100%
        circleBarView.setProgressNum(i, 5000);
    }
    private void queryCityCode(){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //引入options菜单
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //给options菜单设置点击事情
        int id = item.getItemId();
        if (id == R.id.menu_setting) {
            Intent intent = new Intent(WeatherActivity.this,LocationActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_city){
            Intent intent = new Intent(WeatherActivity.this,CityActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
