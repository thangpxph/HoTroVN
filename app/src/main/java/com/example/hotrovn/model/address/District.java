package com.example.hotrovn.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class District {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("village")
    @Expose
    private List<Village> village = null;

    public District(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Village> getVillage() {
        return village;
    }

    public void setVillage(List<Village> village) {
        this.village = village;
    }

}
