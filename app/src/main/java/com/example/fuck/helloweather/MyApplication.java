package com.example.fuck.helloweather;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.itheima.retrofitutils.ItheimaHttp;

import org.litepal.LitePal;


public class MyApplication extends Application {
    public void onCreate(){
        super.onCreate();
        ItheimaHttp.init(this, "http://t.weather.sojson.com");
        LitePal.initialize(this);
        AVOSCloud.initialize(this,"aPLGUI3S75WtiYbDNN8PG4s7-gzGzoHsz","geRMuctu2BdaTiQq1ea4TKSF");
    }
}
