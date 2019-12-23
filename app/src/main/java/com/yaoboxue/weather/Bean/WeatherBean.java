package com.yaoboxue.weather.Bean;

import java.util.List;

/**
 * @Author yaoboxue
 * @Date 2019.12.10  15:39
 * @Version 1.0
 */
public class WeatherBean{

    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"9","wind_direction":"西北风","wind_strength":"2级","humidity":"92%","time":"13:25"},"today":{"temperature":"7℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"西北风微风","week":"星期一","city":"长沙","date_y":"2019年12月23日","dressing_index":"冷","dressing_advice":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":[{"temperature":"7℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"西北风微风","week":"星期一","date":"20191223"},{"temperature":"7℃~11℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"西北风微风","week":"星期二","date":"20191224"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期三","date":"20191225"},{"temperature":"3℃~9℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-5级","week":"星期四","date":"20191226"},{"temperature":"3℃~13℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},"wind":"北风微风","week":"星期五","date":"20191227"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期六","date":"20191228"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期日","date":"20191229"}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * sk : {"temp":"9","wind_direction":"西北风","wind_strength":"2级","humidity":"92%","time":"13:25"}
         * today : {"temperature":"7℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"西北风微风","week":"星期一","city":"长沙","date_y":"2019年12月23日","dressing_index":"冷","dressing_advice":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
         * future : [{"temperature":"7℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"西北风微风","week":"星期一","date":"20191223"},{"temperature":"7℃~11℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"西北风微风","week":"星期二","date":"20191224"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期三","date":"20191225"},{"temperature":"3℃~9℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-5级","week":"星期四","date":"20191226"},{"temperature":"3℃~13℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},"wind":"北风微风","week":"星期五","date":"20191227"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期六","date":"20191228"},{"temperature":"7℃~11℃","weather":"小雨转中雨","weather_id":{"fa":"07","fb":"08"},"wind":"北风3-5级","week":"星期日","date":"20191229"}]
         */

        private SkBean sk;
        private TodayBean today;
        private List<FutureBean> future;

        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class SkBean {
            /**
             * temp : 9
             * wind_direction : 西北风
             * wind_strength : 2级
             * humidity : 92%
             * time : 13:25
             */

            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TodayBean {
            /**
             * temperature : 7℃~10℃
             * weather : 小雨转阴
             * weather_id : {"fa":"07","fb":"02"}
             * wind : 西北风微风
             * week : 星期一
             * city : 长沙
             * date_y : 2019年12月23日
             * dressing_index : 冷
             * dressing_advice : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
             * uv_index : 最弱 紫外线
             * comfort_index :
             * wash_index : 不宜
             * travel_index : 较不宜
             * exercise_index : 较不宜
             * drying_index :
             */

            private String temperature;
            private String weather;
            private WeatherIdBean weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBean getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBean weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate_y() {
                return date_y;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public static class WeatherIdBean {
                /**
                 * fa : 07
                 * fb : 02
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class FutureBean {
            /**
             * temperature : 7℃~10℃
             * weather : 小雨转阴
             * weather_id : {"fa":"07","fb":"02"}
             * wind : 西北风微风
             * week : 星期一
             * date : 20191223
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanX {
                /**
                 * fa : 07
                 * fb : 02
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }
    }
}