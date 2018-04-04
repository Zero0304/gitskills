package com.datasource.pojo;

/**
 * Created by ranai on 2018/4/2 0002.
 */
public class City {

    private Integer id;
    private String city_name;
    private String province_id;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", city_name='" + city_name + '\'' +
                ", province_id='" + province_id + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
}
