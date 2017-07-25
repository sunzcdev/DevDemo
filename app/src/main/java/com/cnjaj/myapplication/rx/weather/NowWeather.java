package com.cnjaj.myapplication.rx.weather;

import java.util.List;

public class NowWeather {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * location : {"id":"C23NB62W20TF","name":"西雅图","country":"US","timezone":"America/Los_Angeles","timezone_offset":"-07:00"}
         * now : {"text":"多云","code":"4","temperature":"14","feels_like":"14","pressure":"1018","humidity":"76","visibility":"16.09","wind_direction":"西北","wind_direction_degree":"340","wind_speed":"8.05","wind_scale":"2","clouds":"90","dew_point":"-12"}
         * last_update : 2015-09-25T22:45:00-07:00
         */

        private LocationBean location;
        private NowBean now;
        private String last_update;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public static class LocationBean {
            /**
             * id : C23NB62W20TF
             * name : 西雅图
             * country : US
             * timezone : America/Los_Angeles
             * timezone_offset : -07:00
             */

            private String id;
            private String name;
            private String country;
            private String timezone;
            private String timezone_offset;

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

    }

}
