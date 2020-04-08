package com.birdwang.maps.map.model;

import android.text.TextUtils;

import java.util.List;

public class GeoCode {
    /**
     * plus_code : {"compound_code":"73V6+2G 中国浙江省杭州市西湖区","global_code":"8Q2273V6+2G"}
     * results : [{"address_components":[{"long_name":"双龙街","short_name":"双龙街","types":["route"]},{"long_name":"西湖区","short_name":"西湖区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"杭州市","short_name":"杭州市","types":["locality","political"]},{"long_name":"浙江省","short_name":"浙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国浙江省杭州市西湖区双龙街","geometry":{"bounds":{"northeast":{"lat":30.2931001,"lng":120.062739},"southwest":{"lat":30.2928115,"lng":120.060838}},"location":{"lat":30.2929558,"lng":120.0617885},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":30.2943047802915,"lng":120.0631374802915},"southwest":{"lat":30.2916068197085,"lng":120.0604395197085}}},"place_id":"ChIJIbuUl4BkSzQRbujsuBDOwS0","types":["route"]},{"address_components":[{"long_name":"西湖区","short_name":"西湖区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"杭州市","short_name":"杭州市","types":["locality","political"]},{"long_name":"浙江省","short_name":"浙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国浙江省杭州市西湖区","geometry":{"bounds":{"northeast":{"lat":30.3554446,"lng":120.1830059},"southwest":{"lat":30.0781655,"lng":119.9963384}},"location":{"lat":30.259312,"lng":120.130211},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":30.3554446,"lng":120.1830059},"southwest":{"lat":30.0781655,"lng":119.9963384}}},"place_id":"ChIJNbvq9xtkSzQRL26HFDUVqm4","types":["political","sublocality","sublocality_level_1"]},{"address_components":[{"long_name":"杭州市","short_name":"杭州市","types":["locality","political"]},{"long_name":"浙江省","short_name":"浙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国浙江省杭州市","geometry":{"bounds":{"northeast":{"lat":30.5665162,"lng":120.7219451},"southwest":{"lat":29.18875689999999,"lng":118.3449333}},"location":{"lat":30.274084,"lng":120.15507},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":30.5665162,"lng":120.7219451},"southwest":{"lat":29.18875689999999,"lng":118.3449333}}},"place_id":"ChIJmaqaQym2SzQROuhNgoPRv6c","types":["locality","political"]},{"address_components":[{"long_name":"浙江省","short_name":"浙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国浙江省","geometry":{"bounds":{"northeast":{"lat":31.1787819,"lng":122.9495085},"southwest":{"lat":27.0413557,"lng":118.0282788}},"location":{"lat":29.1416432,"lng":119.7889248},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":31.1787819,"lng":122.9495085},"southwest":{"lat":27.0413557,"lng":118.0282788}}},"place_id":"ChIJv8wKpTQSSTQRt3wYNsVx74E","types":["administrative_area_level_1","political"]},{"address_components":[{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国","geometry":{"bounds":{"northeast":{"lat":53.5609739,"lng":134.7754563},"southwest":{"lat":17.9996,"lng":73.4994136}},"location":{"lat":35.86166,"lng":104.195397},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":53.5609739,"lng":134.7754563},"southwest":{"lat":17.9996,"lng":73.4994136}}},"place_id":"ChIJwULG5WSOUDERbzafNHyqHZU","types":["country","political"]}]
     * status : OK
     */
    private String city;


    private PlusCodeBean plus_code;
    private String status;
    private List<ResultsBean> results;

    public PlusCodeBean getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCodeBean plus_code) {
        this.plus_code = plus_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class PlusCodeBean {
        /**
         * compound_code : 73V6+2G 中国浙江省杭州市西湖区
         * global_code : 8Q2273V6+2G
         */

        private String compound_code;
        private String global_code;

        public String getCompound_code() {
            return compound_code;
        }

        public void setCompound_code(String compound_code) {
            this.compound_code = compound_code;
        }

        public String getGlobal_code() {
            return global_code;
        }

        public void setGlobal_code(String global_code) {
            this.global_code = global_code;
        }
    }

    public static class ResultsBean {
        /**
         * address_components : [{"long_name":"双龙街","short_name":"双龙街","types":["route"]},{"long_name":"西湖区","short_name":"西湖区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"杭州市","short_name":"杭州市","types":["locality","political"]},{"long_name":"浙江省","short_name":"浙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}]
         * formatted_address : 中国浙江省杭州市西湖区双龙街
         * geometry : {"bounds":{"northeast":{"lat":30.2931001,"lng":120.062739},"southwest":{"lat":30.2928115,"lng":120.060838}},"location":{"lat":30.2929558,"lng":120.0617885},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":30.2943047802915,"lng":120.0631374802915},"southwest":{"lat":30.2916068197085,"lng":120.0604395197085}}}
         * place_id : ChIJIbuUl4BkSzQRbujsuBDOwS0
         * types : ["route"]
         */

        private String formatted_address;
        private GeometryBean geometry;
        private String place_id;
        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * bounds : {"northeast":{"lat":30.2931001,"lng":120.062739},"southwest":{"lat":30.2928115,"lng":120.060838}}
             * location : {"lat":30.2929558,"lng":120.0617885}
             * location_type : GEOMETRIC_CENTER
             * viewport : {"northeast":{"lat":30.2943047802915,"lng":120.0631374802915},"southwest":{"lat":30.2916068197085,"lng":120.0604395197085}}
             */

            private BoundsBean bounds;
            private LocationBean location;
            private String location_type;
            private ViewportBean viewport;

            public BoundsBean getBounds() {
                return bounds;
            }

            public void setBounds(BoundsBean bounds) {
                this.bounds = bounds;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class BoundsBean {
                /**
                 * northeast : {"lat":30.2931001,"lng":120.062739}
                 * southwest : {"lat":30.2928115,"lng":120.060838}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 30.2931001
                     * lng : 120.062739
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 30.2928115
                     * lng : 120.060838
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                /**
                 * lat : 30.2929558
                 * lng : 120.0617885
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":30.2943047802915,"lng":120.0631374802915}
                 * southwest : {"lat":30.2916068197085,"lng":120.0604395197085}
                 */

                private NortheastBeanX northeast;
                private SouthwestBeanX southwest;

                public NortheastBeanX getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBeanX northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBeanX getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBeanX southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBeanX {
                    /**
                     * lat : 30.2943047802915
                     * lng : 120.0631374802915
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBeanX {
                    /**
                     * lat : 30.2916068197085
                     * lng : 120.0604395197085
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : 双龙街
             * short_name : 双龙街
             * types : ["route"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }

    public String getCity(){
        if (!TextUtils.isEmpty(city)){
            return city;
        }
        List<ResultsBean> resultsBeanList = getResults();
        if (resultsBeanList != null){
            for (GeoCode.ResultsBean resultsBean: resultsBeanList){
                List<ResultsBean.AddressComponentsBean> addressList = resultsBean.getAddress_components();
                if (addressList != null){
                    for (GeoCode.ResultsBean.AddressComponentsBean address: addressList){
                        List<String> types = address.getTypes();
                        if (types != null){
                            for (String type: types){
                                if (TextUtils.equals(type, "locality")){
                                    city = address.getShort_name();
                                    return city;
                                }
                            }
                        }
                    }
                }
            }
        }
        return city;
    }
}
