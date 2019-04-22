package com.example.fuck.helloweather.jsonbean;

import java.util.List;

public class WeatherBean {
    /**
     * time : 2019-04-16 13:30:37
     * cityInfo : {"city":"天津市","cityId":"101030100","parent":"天津","updateTime":"12:32"}
     * date : 20190416
     * message : Success !
     * status : 200
     * data : {"shidu":"35%","pm25":69,"pm10":94,"quality":"良","wendu":"22","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"15","sunrise":"05:37","high":"高温 26.0℃","low":"低温 13.0℃","sunset":"18:47","aqi":80,"ymd":"2019-04-15","week":"星期一","fx":"西南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"16","sunrise":"05:36","high":"高温 24.0℃","low":"低温 15.0℃","sunset":"18:48","aqi":87,"ymd":"2019-04-16","week":"星期二","fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"17","sunrise":"05:35","high":"高温 30.0℃","low":"低温 17.0℃","sunset":"18:49","aqi":102,"ymd":"2019-04-17","week":"星期三","fx":"西南风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"18","sunrise":"05:33","high":"高温 23.0℃","low":"低温 11.0℃","sunset":"18:50","aqi":61,"ymd":"2019-04-18","week":"星期四","fx":"东南风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"19","sunrise":"05:32","high":"高温 16.0℃","low":"低温 9.0℃","sunset":"18:51","aqi":50,"ymd":"2019-04-19","week":"星期五","fx":"东南风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"20","sunrise":"05:30","high":"高温 23.0℃","low":"低温 15.0℃","sunset":"18:52","aqi":77,"ymd":"2019-04-20","week":"星期六","fx":"南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"21","sunrise":"05:29","high":"高温 22.0℃","low":"低温 13.0℃","sunset":"18:53","aqi":74,"ymd":"2019-04-21","week":"星期日","fx":"东风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"22","sunrise":"05:27","high":"高温 22.0℃","low":"低温 15.0℃","sunset":"18:54","ymd":"2019-04-22","week":"星期一","fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"23","sunrise":"05:26","high":"高温 28.0℃","low":"低温 16.0℃","sunset":"18:55","ymd":"2019-04-23","week":"星期二","fx":"西南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"24","sunrise":"05:25","high":"高温 21.0℃","low":"低温 15.0℃","sunset":"18:56","ymd":"2019-04-24","week":"星期三","fx":"东风","fl":"4-5级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"25","sunrise":"05:23","high":"高温 20.0℃","low":"低温 12.0℃","sunset":"18:57","ymd":"2019-04-25","week":"星期四","fx":"东风","fl":"3-4级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"26","sunrise":"05:22","high":"高温 21.0℃","low":"低温 12.0℃","sunset":"18:58","ymd":"2019-04-26","week":"星期五","fx":"东北风","fl":"4-5级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"27","sunrise":"05:21","high":"高温 25.0℃","low":"低温 14.0℃","sunset":"18:59","ymd":"2019-04-27","week":"星期六","fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28","sunrise":"05:19","high":"高温 22.0℃","low":"低温 12.0℃","sunset":"19:00","ymd":"2019-04-28","week":"星期日","fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29","sunrise":"05:18","high":"高温 21.0℃","low":"低温 12.0℃","sunset":"19:01","ymd":"2019-04-29","week":"星期一","fx":"西南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"30","sunrise":"05:17","high":"高温 22.0℃","low":"低温 12.0℃","sunset":"19:02","ymd":"2019-04-30","week":"星期二","fx":"北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}]}
     */

    private String time;
    private CityInfoBean cityInfo;
    private String       date;
    private String       message;
    private int          status;
    private DataBean     data;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfoBean getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoBean cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class CityInfoBean {
        /**
         * city : 天津市
         * cityId : 101030100
         * parent : 天津
         * updateTime : 12:32
         */

        private String city;
        private String cityId;
        private String parent;
        private String updateTime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class DataBean {
        /**
         * shidu : 35%
         * pm25 : 69.0
         * pm10 : 94.0
         * quality : 良
         * wendu : 22
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"15","sunrise":"05:37","high":"高温 26.0℃","low":"低温 13.0℃","sunset":"18:47","aqi":80,"ymd":"2019-04-15","week":"星期一","fx":"西南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}
         * forecast : [{"date":"16","sunrise":"05:36","high":"高温 24.0℃","low":"低温 15.0℃","sunset":"18:48","aqi":87,"ymd":"2019-04-16","week":"星期二","fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"17","sunrise":"05:35","high":"高温 30.0℃","low":"低温 17.0℃","sunset":"18:49","aqi":102,"ymd":"2019-04-17","week":"星期三","fx":"西南风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"18","sunrise":"05:33","high":"高温 23.0℃","low":"低温 11.0℃","sunset":"18:50","aqi":61,"ymd":"2019-04-18","week":"星期四","fx":"东南风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"19","sunrise":"05:32","high":"高温 16.0℃","low":"低温 9.0℃","sunset":"18:51","aqi":50,"ymd":"2019-04-19","week":"星期五","fx":"东南风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"20","sunrise":"05:30","high":"高温 23.0℃","low":"低温 15.0℃","sunset":"18:52","aqi":77,"ymd":"2019-04-20","week":"星期六","fx":"南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"21","sunrise":"05:29","high":"高温 22.0℃","low":"低温 13.0℃","sunset":"18:53","aqi":74,"ymd":"2019-04-21","week":"星期日","fx":"东风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"22","sunrise":"05:27","high":"高温 22.0℃","low":"低温 15.0℃","sunset":"18:54","ymd":"2019-04-22","week":"星期一","fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"23","sunrise":"05:26","high":"高温 28.0℃","low":"低温 16.0℃","sunset":"18:55","ymd":"2019-04-23","week":"星期二","fx":"西南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"24","sunrise":"05:25","high":"高温 21.0℃","low":"低温 15.0℃","sunset":"18:56","ymd":"2019-04-24","week":"星期三","fx":"东风","fl":"4-5级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"25","sunrise":"05:23","high":"高温 20.0℃","low":"低温 12.0℃","sunset":"18:57","ymd":"2019-04-25","week":"星期四","fx":"东风","fl":"3-4级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"26","sunrise":"05:22","high":"高温 21.0℃","low":"低温 12.0℃","sunset":"18:58","ymd":"2019-04-26","week":"星期五","fx":"东北风","fl":"4-5级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"27","sunrise":"05:21","high":"高温 25.0℃","low":"低温 14.0℃","sunset":"18:59","ymd":"2019-04-27","week":"星期六","fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28","sunrise":"05:19","high":"高温 22.0℃","low":"低温 12.0℃","sunset":"19:00","ymd":"2019-04-28","week":"星期日","fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29","sunrise":"05:18","high":"高温 21.0℃","low":"低温 12.0℃","sunset":"19:01","ymd":"2019-04-29","week":"星期一","fx":"西南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"30","sunrise":"05:17","high":"高温 22.0℃","low":"低温 12.0℃","sunset":"19:02","ymd":"2019-04-30","week":"星期二","fx":"北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}]
         */

        private String shidu;
        private double             pm25;
        private double             pm10;
        private String             quality;
        private String             wendu;
        private String             ganmao;
        private YesterdayBean      yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 15
             * sunrise : 05:37
             * high : 高温 26.0℃
             * low : 低温 13.0℃
             * sunset : 18:47
             * aqi : 80.0
             * ymd : 2019-04-15
             * week : 星期一
             * fx : 西南风
             * fl : 3-4级
             * type : 晴
             * notice : 愿你拥有比阳光明媚的心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private float aqi;
            private String ymd;
            private String week;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public float getAqi() {
                return aqi;
            }

            public void setAqi(float aqi) {
                this.aqi = aqi;
            }

            public String getYmd() {
                return ymd;
            }

            public void setYmd(String ymd) {
                this.ymd = ymd;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 16
             * sunrise : 05:36
             * high : 高温 24.0℃
             * low : 低温 15.0℃
             * sunset : 18:48
             * aqi : 87.0
             * ymd : 2019-04-16
             * week : 星期二
             * fx : 东南风
             * fl : 3-4级
             * type : 多云
             * notice : 阴晴之间，谨防紫外线侵扰
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private float aqi;
            private String ymd;
            private String week;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public float getAqi() {
                return aqi;
            }

            public void setAqi(float aqi) {
                this.aqi = aqi;
            }

            public String getYmd() {
                return ymd;
            }

            public void setYmd(String ymd) {
                this.ymd = ymd;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
