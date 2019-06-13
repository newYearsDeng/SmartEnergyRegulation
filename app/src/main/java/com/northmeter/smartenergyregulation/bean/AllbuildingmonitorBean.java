package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/29.
 * 非房间内智能插座统计信息
 */

public class AllbuildingmonitorBean extends CommonResponse{

    public AllleafmonitorList list;

    public AllleafmonitorList getList() {
        return list;
    }

    public void setList(AllleafmonitorList list) {
        this.list = list;
    }

    public class AllleafmonitorList implements Serializable{
        private String buildingid;//":"001002000002",
        private String buildigname;//": "耶鲁大学",//建筑名称
        private int buildinglevel;//":5,
        private int accounttotal;//": 2,//设备总数
        private int normalnum;//": 1,//正常数
        private int abnormalnum;//": 1,//异常数
        private List<Entities> outletStatisticsEntities;

        public String getBuildingid() {
            return buildingid;
        }

        public void setBuildingid(String buildingid) {
            this.buildingid = buildingid;
        }

        public String getBuildigname() {
            return buildigname;
        }

        public void setBuildigname(String buildigname) {
            this.buildigname = buildigname;
        }

        public int getBuildinglevel() {
            return buildinglevel;
        }

        public void setBuildinglevel(int buildinglevel) {
            this.buildinglevel = buildinglevel;
        }

        public int getAccounttotal() {
            return accounttotal;
        }

        public void setAccounttotal(int accounttotal) {
            this.accounttotal = accounttotal;
        }

        public int getNormalnum() {
            return normalnum;
        }

        public void setNormalnum(int normalnum) {
            this.normalnum = normalnum;
        }

        public int getAbnormalnum() {
            return abnormalnum;
        }

        public void setAbnormalnum(int abnormalnum) {
            this.abnormalnum = abnormalnum;
        }

        public List<Entities> getOutletStatisticsEntities() {
            return outletStatisticsEntities;
        }

        public void setOutletStatisticsEntities(List<Entities> outletStatisticsEntities) {
            this.outletStatisticsEntities = outletStatisticsEntities;
        }
    }

    public class Entities implements Serializable{
        private String buildingid;//":"001002000002",
        private String buildigname;//": "耶鲁大学",//建筑名称
        private int buildinglevel;//":5,
        private int accounttotal;//": 2,//设备总数
        private int normalnum;//": 1,//正常数
        private int abnormalnum;//": 1,//异常数
        private List<Entities> outletStatisticsEntities;

        public String getBuildingid() {
            return buildingid;
        }

        public void setBuildingid(String buildingid) {
            this.buildingid = buildingid;
        }

        public String getBuildigname() {
            return buildigname;
        }

        public void setBuildigname(String buildigname) {
            this.buildigname = buildigname;
        }

        public int getBuildinglevel() {
            return buildinglevel;
        }

        public void setBuildinglevel(int buildinglevel) {
            this.buildinglevel = buildinglevel;
        }

        public int getAccounttotal() {
            return accounttotal;
        }

        public void setAccounttotal(int accounttotal) {
            this.accounttotal = accounttotal;
        }

        public int getNormalnum() {
            return normalnum;
        }

        public void setNormalnum(int normalnum) {
            this.normalnum = normalnum;
        }

        public int getAbnormalnum() {
            return abnormalnum;
        }

        public void setAbnormalnum(int abnormalnum) {
            this.abnormalnum = abnormalnum;
        }

        public List<Entities> getOutletStatisticsEntities() {
            return outletStatisticsEntities;
        }

        public void setOutletStatisticsEntities(List<Entities> outletStatisticsEntities) {
            this.outletStatisticsEntities = outletStatisticsEntities;
        }
    }
}

/**
 *     {
 "msg": "success",
 "code": 0,
 "list": {
 "buildingid":"001002000002",
 "buildigname": "耶鲁大学",//建筑名称
 "buildinglevel":5,
 "accounttotal": 2,//设备总数
 "normalnum": 1,//正常数
 "abnormalnum": 1,//异常数
 "outletStatisticsEntities": [//子一级建筑列表
 {
 "buildingid":"001002000002",
 "buildigname": "北校区",
 "buildinglevel":6,
 "accounttotal": 2,
 "normalnum": 1,
 "abnormalnum": 1,
 "outletStatisticsEntities": null
 }
 ]
 }
 }*/