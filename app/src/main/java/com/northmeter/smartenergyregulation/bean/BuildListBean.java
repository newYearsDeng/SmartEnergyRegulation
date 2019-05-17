package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/9.
 */

public class BuildListBean extends CommonResponse implements Serializable{
    public List<BuildList> list;

    public List<BuildList> getList() {
        return list;
    }

    public void setList(List<BuildList> list) {
        this.list = list;
    }

    public class BuildList implements Serializable{
        private String buildingid;
        private String buildingname;
        private String parentid;
        private int isleaf;
        private List<BuildinginfoEntityList> buildinginfoEntityList;
        private List<meterinfoEntityList> meterinfoEntityList;

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

        public int getIsleaf() {
            return isleaf;
        }

        public void setIsleaf(int isleaf) {
            this.isleaf = isleaf;
        }

        public List<BuildinginfoEntityList> getBuildinginfoEntityList() {
            return buildinginfoEntityList;
        }

        public void setBuildinginfoEntityList(List<BuildinginfoEntityList> buildinginfoEntityList) {
            this.buildinginfoEntityList = buildinginfoEntityList;
        }

        public List<BuildListBean.meterinfoEntityList> getMeterinfoEntityList() {
            return meterinfoEntityList;
        }

        public void setMeterinfoEntityList(List<BuildListBean.meterinfoEntityList> meterinfoEntityList) {
            this.meterinfoEntityList = meterinfoEntityList;
        }
    }

    public class BuildinginfoEntityList implements Serializable{
        private String buildingid;
        private String buildingname;
        private String parentid;
        private int isleaf;

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

        public int getIsleaf() {
            return isleaf;
        }

        public void setIsleaf(int isleaf) {
            this.isleaf = isleaf;
        }
    }

    public class meterinfoEntityList implements Serializable{
        private int meterid;
        private String comaddress;
        private String buildingid;
        private String mtequipmentversionid;
        private String producedate;
        private String metername;

        public int getMeterid() {
            return meterid;
        }

        public void setMeterid(int meterid) {
            this.meterid = meterid;
        }

        public String getComaddress() {
            return comaddress;
        }

        public void setComaddress(String comaddress) {
            this.comaddress = comaddress;
        }

        public String getBuildingid() {
            return buildingid;
        }

        public void setBuildingid(String buildingid) {
            this.buildingid = buildingid;
        }

        public String getMtequipmentversionid() {
            return mtequipmentversionid;
        }

        public void setMtequipmentversionid(String mtequipmentversionid) {
            this.mtequipmentversionid = mtequipmentversionid;
        }

        public String getProducedate() {
            return producedate;
        }

        public void setProducedate(String producedate) {
            this.producedate = producedate;
        }

        public String getMetername() {
            return metername;
        }

        public void setMetername(String metername) {
            this.metername = metername;
        }
    }
}


    /**
     * {
     "msg": "success",
     "code": 0,
     "list": [{
         "buildingid": "001",
         "buildingname": "北电仪表",
         "parentid": "0",
         "address": "深南大道",
         "buildinglevel": 1,
         "buildingtype": "23001",
         "buildingarea": null,
         "sumpersonal": null,
         "usingpersonal": null,
         "buildingpersonal": null,
         "buildingtel": "",
         "password": null,
         "isleaf": 0,
         "username": "0001",
         "customerid": "1",
         "buildingdesc": null,
         "buildinginfoEntityList": [{
             "buildingid": "001000001",
             "buildingname": "园区（测试）",
             "parentid": "001",
             "address": "",
             "buildinglevel": 3,
             "buildingtype": "",
             "buildingarea": null,
             "sumpersonal": 4,
             "usingpersonal": 4,
             "buildingpersonal": "",
             "buildingtel": "|",
             "password": "",
             "isleaf": 0,//是否叶子节点 0 不是，1 是
             "username": "qqq",
             "customerid": "1",
             "buildingdesc": "",
             "longitude": null,
             "latitude": null,
             "hasmeter": 0,
             "kcbid": 0,
             "list": null,
             "paraInfoList": null
         }, {
         "buildingid": "001003",
         "buildingname": "邵海松测试区",
         "parentid": "001",
         "address": "",
         "buildinglevel": 2,
         "buildingtype": "",
         "buildingarea": null,
         "sumpersonal": 4,
         "usingpersonal": 4,
         "buildingpersonal": "|||||||||||||||||||||||",
         "buildingtel": "|||||||||||||||||||||||||",
         "password": "",
         "isleaf": 0,
         "username": "shs",
         "customerid": "1",
         "buildingdesc": "",
         "longitude": null,
         "latitude": null,
         "hasmeter": 0,
         "kcbid": 0,
         "list": null,
         "paraInfoList": null
         }],
         "meterinfoEntityList": null
         }]
     }*/


    /**
     * {
     "msg": "success",
     "code": 0,
     "list": [
     {
     "buildingid": "001000001000001000005",
     "buildingname": "无房非预",
     "parentid": "001000001000001",
     "address": "",
     "buildinglevel": 7,
     "buildingtype": "",
     "buildingarea": null,
     "sumpersonal": 4,
     "usingpersonal": 4,
     "buildingpersonal": "",
     "buildingtel": "|",
     "password": "",
     "isleaf": 1,
     "username": "fxy",
     "customerid": "1",
     "buildingdesc": "",
     "buildinginfoEntityList": null,
     "meterinfoEntityList": [
     {
     "meterid": 37,
     "comaddress": "555555555551",
     "buildingid": "001000001000001000005",
     "mtequipmentversionid": "00006",
     "producedate": "2019-04-19 17:55:44",
     "metercode": null,
     "metername": "无房非预",
     "servicestate": "14002",
     "begintousetime": null,
     "password": "00000000",
     "meterkey": null,
     "meterbaudrate": "2400bps",
     "measureseq": 4,
     "measurecode": 4,
     "ratenum": null,
     "agreement": "DL/T645-2007",
     "terminalport": "25001",
     "downloadstatus": null,
     "ratio": 1,
     "iratio": 1,
     "uratio": 1,
     "chargenumber": null,
     "warncredit1": 20.00,
     "warncredit2": 0.00,
     "maxtouzhi": 0.00,
     "maxtunji": 999999.99,
     "maxvol": 260.0,
     "qcvalue": null,
     "maxpower": null,
     "yfftimes": null,
     "meterzttimes": null,
     "yfftimesdate": null,
     "meterzttimesdate": null,
     "dxfszt": null,
     "wxfszt": null,
     "bkcs": null,
     "istwomeasure": 0,
     "looponename": "空调",
     "looptwoname": "照明插座",
     "chargenumber2": null,
     "isvirtualmeter": 0,
     "meterformula": null,
     "issetting": null,
     "createtime": "2019-04-19 17:55:57",
     "customerid": "1",
     "isimportment": 0,
     "startPower": null,
     "temperLimit": null,
     "meterNum": "",
     "pipLineID": null
     }
     ]
     }
     ]
     }*/
