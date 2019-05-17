package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/16.
 */

public class SelectBuildBean extends CommonResponse implements Serializable{

    public List<TreeBuild> list;

    public List<TreeBuild> getList() {
        return list;
    }

    public void setList(List<TreeBuild> list) {
        this.list = list;
    }

    public class TreeBuild implements Serializable{
        public String buildingid;
        public String buildingname;
        public String parentid;
        public String address;
        public int buildinglevel;
        public String buildingtype;
        public String buildingarea;
        public int sumpersonal;
        public int usingpersonal;
        public String buildingpersonal;
        public String buildingtel;
        public String password;
        public int isleaf;
        public String username;
        public String customerid;
        public String buildingdesc;
        List<BuildinginfoEntityList> buildinginfoEntityList;

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

        public String getBuildingarea() {
            return buildingarea;
        }

        public void setBuildingarea(String buildingarea) {
            this.buildingarea = buildingarea;
        }

        public int getSumpersonal() {
            return sumpersonal;
        }

        public void setSumpersonal(int sumpersonal) {
            this.sumpersonal = sumpersonal;
        }

        public int getUsingpersonal() {
            return usingpersonal;
        }

        public void setUsingpersonal(int usingpersonal) {
            this.usingpersonal = usingpersonal;
        }

        public String getBuildingpersonal() {
            return buildingpersonal;
        }

        public void setBuildingpersonal(String buildingpersonal) {
            this.buildingpersonal = buildingpersonal;
        }

        public String getBuildingtel() {
            return buildingtel;
        }

        public void setBuildingtel(String buildingtel) {
            this.buildingtel = buildingtel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIsleaf() {
            return isleaf;
        }

        public void setIsleaf(int isleaf) {
            this.isleaf = isleaf;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getBuildingdesc() {
            return buildingdesc;
        }

        public void setBuildingdesc(String buildingdesc) {
            this.buildingdesc = buildingdesc;
        }

        public List<BuildinginfoEntityList> getBuildinginfoEntityList() {
            return buildinginfoEntityList;
        }

        public void setBuildinginfoEntityList(List<BuildinginfoEntityList> buildinginfoEntityList) {
            this.buildinginfoEntityList = buildinginfoEntityList;
        }
    }

    public class BuildinginfoEntityList implements Serializable{
        public String buildingid;//"001000001000001",
        public String buildingname;//有建筑预",
        public String parentid;//001000001",
        public String address;//"",
        public int buildinglevel;//5,
        public String buildingtype;//"23016",
        public float buildingarea;//0.00,
        public int sumpersonal;//0,
        public int usingpersonal;//:0,
        public String buildingpersonal;//:"",
        public String buildingtel;//:"",
        public String password;//:"",
        public int isleaf;//:0,
        public String username;//:"fxy",
        public String customerid;//:"1",
        public String buildingdesc;//:"",
        public String longitude;//:null,
        public String latitude;//:null,
        public int hasmeter;//:0,
        public int kcbid;//:0,
        public String list;//:null,
        public String paraInfoList;//:null

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

        public float getBuildingarea() {
            return buildingarea;
        }

        public void setBuildingarea(float buildingarea) {
            this.buildingarea = buildingarea;
        }

        public int getSumpersonal() {
            return sumpersonal;
        }

        public void setSumpersonal(int sumpersonal) {
            this.sumpersonal = sumpersonal;
        }

        public int getUsingpersonal() {
            return usingpersonal;
        }

        public void setUsingpersonal(int usingpersonal) {
            this.usingpersonal = usingpersonal;
        }

        public String getBuildingpersonal() {
            return buildingpersonal;
        }

        public void setBuildingpersonal(String buildingpersonal) {
            this.buildingpersonal = buildingpersonal;
        }

        public String getBuildingtel() {
            return buildingtel;
        }

        public void setBuildingtel(String buildingtel) {
            this.buildingtel = buildingtel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIsleaf() {
            return isleaf;
        }

        public void setIsleaf(int isleaf) {
            this.isleaf = isleaf;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getBuildingdesc() {
            return buildingdesc;
        }

        public void setBuildingdesc(String buildingdesc) {
            this.buildingdesc = buildingdesc;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getHasmeter() {
            return hasmeter;
        }

        public void setHasmeter(int hasmeter) {
            this.hasmeter = hasmeter;
        }

        public int getKcbid() {
            return kcbid;
        }

        public void setKcbid(int kcbid) {
            this.kcbid = kcbid;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }

        public String getParaInfoList() {
            return paraInfoList;
        }

        public void setParaInfoList(String paraInfoList) {
            this.paraInfoList = paraInfoList;
        }
    }
}

/**
 *
 *
 {

 "msg":"success",
 "code":0,
 "list":[
     {
     "buildingid":"001000001",
     "buildingname":"园区（测试）",
     "parentid":"001",
     "address":"",
     "buildinglevel":3,
     "buildingtype":"",
     "buildingarea":null,
     "sumpersonal":4,
     "usingpersonal":4,
     "buildingpersonal":"",
     "buildingtel":"|",
     "password":"",
     "isleaf":0,
     "username":"qqq",
     "customerid":"1",
     "buildingdesc":"",
     "buildinginfoEntityList":[
     {
     "buildingid":"001000001000001",
     "buildingname":"有建筑预",
     "parentid":"001000001",
     "address":"",
     "buildinglevel":5,
     "buildingtype":"23016",
     "buildingarea":0.00,
     "sumpersonal":0,
     "usingpersonal":0,
     "buildingpersonal":"",
     "buildingtel":"",
     "password":"",
     "isleaf":0,
     "username":"fxy",
     "customerid":"1",
     "buildingdesc":"",
     "longitude":null,
     "latitude":null,
     "hasmeter":0,
     "kcbid":0,
     "list":null,
     "paraInfoList":null
     },
     {
     "buildingid":"001000001000002",
     "buildingname":"有建筑非预",
     "parentid":"001000001",
     "address":"",
     "buildinglevel":5,
     "buildingtype":"23016",
     "buildingarea":0.00,
     "sumpersonal":0,
     "usingpersonal":0,
     "buildingpersonal":"",
     "buildingtel":"",
     "password":"",
     "isleaf":0,
     "username":"fxy",
     "customerid":"1",
     "buildingdesc":"",
     "longitude":null,
     "latitude":null,
     "hasmeter":0,
     "kcbid":0,
     "list":null,
     "paraInfoList":null
     }
     ],
     "meterinfoEntityList":null
     }
     ]

 }
 */