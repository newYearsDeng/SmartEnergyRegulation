package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;

/**
 * Created by dyd on 2019/5/16.
 */

public class SelectBuildShow implements Serializable{
    private String buildingid;
    private String buildingname;
    private String parentid;
    private String address;
    private int buildinglevel;
    private String buildingtype;
    private int isleaf;

    public SelectBuildShow(String buildingid,String buildingname,String parentid, String address,
                           int buildinglevel, String buildingtype, int isleaf){
        this.buildingid = buildingid;
        this.buildingname = buildingname;
        this.parentid = parentid;
        this.address = address;
        this.buildinglevel = buildinglevel;
        this.buildingtype = buildingtype;
        this.isleaf = isleaf;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBuildinglevel() {
        return buildinglevel;
    }

    public void setBuildinglevel(int buildinglevel) {
        this.buildinglevel = buildinglevel;
    }

    public String getBuildingtype() {
        return buildingtype;
    }

    public void setBuildingtype(String buildingtype) {
        this.buildingtype = buildingtype;
    }

    public int getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(int isleaf) {
        this.isleaf = isleaf;
    }
}
