package com.cnjaj.myapplication.rx.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Weather {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "results=" + results +
                '}';
    }

    public static class ResultsBean {
        /**
         * location : {"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
         * daily : [{"date":"2016-11-10","text_day":"霾","code_day":"31","text_night":"多云","code_night":"4","high":"9","low":"1","precip":"","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"10","wind_scale":"2"},{"date":"2016-11-11","text_day":"多云","code_day":"4","text_night":"晴","code_night":"0","high":"11","low":"1","precip":"","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"10","wind_scale":"2"},{"date":"2016-11-12","text_day":"晴","code_day":"0","text_night":"霾","code_night":"31","high":"12","low":"2","precip":"","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"10","wind_scale":"2"}]
         * last_update : 2016-11-10T08:00:00+08:00
         */

        private LocationBean location;
        private String last_update;
        private List<DailyBean> daily;
        private List<HourlyBean> hourly;

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class LocationBean {
            /**
             * id : WX4FBXXFKE4F
             * name : 北京
             * country : CN
             * path : 北京,北京,中国
             * timezone : Asia/Shanghai
             * timezone_offset : +08:00
             */

            private String id;
            private String name;
            private String country;
            private String path;
            private String timezone;
            private String timezone_offset;

            @Override
            public String toString() {
                return "LocationBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", country='" + country + '\'' +
                        ", path='" + path + '\'' +
                        ", timezone='" + timezone + '\'' +
                        ", timezone_offset='" + timezone_offset + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class HourlyBean {

            /**
             * time : 2016-11-10T11:00:00+08:00
             * text : 晴
             * code : 0
             * temperature : 13
             * humidity : 50
             * wind_direction : 无持续风向
             * wind_speed : 14.4
             */

            private String time;
            private String text;
            private String code;
            private String temperature;
            private String humidity;
            private String wind_direction;
            private String wind_speed;

            @Override
            public String toString() {
                return "今天" + time + "的天气:\t" + text +
                        "温度：\t" + temperature + "度\t" +
                        "湿度: \t" + humidity + '\n' +
                        "风向: \t" + wind_direction + '\t' +
                        "风速" + wind_speed;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }
        }

        public static class DailyBean {
            /**
             * date : 2016-11-10
             * text_day : 霾
             * code_day : 31
             * text_night : 多云
             * code_night : 4
             * high : 9
             * low : 1
             * precip :
             * wind_direction : 无持续风向
             * wind_direction_degree :
             * wind_speed : 10
             * wind_scale : 2
             */

            private String date;
            private String text_day;
            private String code_day;
            private String text_night;
            private String code_night;
            private String high;
            private String low;
            private String precip;
            private String wind_direction;
            private String wind_direction_degree;
            private String wind_speed;
            private String wind_scale;

            @Override
            public String toString() {
                return date + "\t天气情况如下:\n" +
                        "白天：\t" + text_day + "\t" +
                        "夜晚:\t" + text_night + '\n' +
                        "最高气温:\t" + high + "度\t" +
                        "最低气温:" + low + "度\n" +
                        "有" + wind_scale + "级风";
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getText_day() {
                return text_day;
            }

            public void setText_day(String text_day) {
                this.text_day = text_day;
            }

            public String getCode_day() {
                return code_day;
            }

            public void setCode_day(String code_day) {
                this.code_day = code_day;
            }

            public String getText_night() {
                return text_night;
            }

            public void setText_night(String text_night) {
                this.text_night = text_night;
            }

            public String getCode_night() {
                return code_night;
            }

            public void setCode_night(String code_night) {
                this.code_night = code_night;
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

            public String getPrecip() {
                return precip;
            }

            public void setPrecip(String precip) {
                this.precip = precip;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_direction_degree() {
                return wind_direction_degree;
            }

            public void setWind_direction_degree(String wind_direction_degree) {
                this.wind_direction_degree = wind_direction_degree;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }
        }
    }
}
