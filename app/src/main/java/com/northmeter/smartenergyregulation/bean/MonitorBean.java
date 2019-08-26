package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/25.
 * 楼栋智能插座信息
 */

public class MonitorBean extends CommonResponse{
    public Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public class Page implements Serializable{
        public List<BuildList> list;

        public List<BuildList> getList() {
            return list;
        }

        public void setList(List<BuildList> list) {
            this.list = list;
        }

        public class BuildList implements Serializable{
            private String bulidingid;
            private String buidingname;
            private String buildinglevel;
            private List<MonitorList> monitor;
            private List<BuildList> child;

            public String getBulidingid() {
                return bulidingid;
            }

            public void setBulidingid(String bulidingid) {
                this.bulidingid = bulidingid;
            }

            public String getBuidingname() {
                return buidingname;
            }

            public void setBuidingname(String buidingname) {
                this.buidingname = buidingname;
            }

            public String getBuildinglevel() {
                return buildinglevel;
            }

            public void setBuildinglevel(String buildinglevel) {
                this.buildinglevel = buildinglevel;
            }

            public List<MonitorList> getMonitor() {
                return monitor;
            }

            public void setMonitor(List<MonitorList> monitor) {
                this.monitor = monitor;
            }

            public List<BuildList> getChild() {
                return child;
            }

            public void setChild(List<BuildList> child) {
                this.child = child;
            }
        }

    }


    public class MonitorList implements Serializable{
        private String bulidingid;
        private int buildinglevel;
        private String comAddress;
        private String metername;
        private String meterid;

        public String getBulidingid() {
            return bulidingid;
        }

        public void setBulidingid(String bulidingid) {
            this.bulidingid = bulidingid;
        }

        public int getBuildinglevel() {
            return buildinglevel;
        }

        public void setBuildinglevel(int buildinglevel) {
            this.buildinglevel = buildinglevel;
        }

        public String getComAddress() {
            return comAddress;
        }

        public void setComAddressl(String comAddress) {
            this.comAddress = comAddress;
        }

        public String getMetername() {
            return metername;
        }

        public void setMetername(String metername) {
            this.metername = metername;
        }

        public String getMeterid() {
            return meterid;
        }

        public void setMeterid(String meterid) {
            this.meterid = meterid;
        }
    }


    public class ChildList implements Serializable{
        private String bulidingid;
        private String buidingname;
        private int buildinglevel;
        private List<MonitorList> monitor;
        private List<ChildList> child;

        public String getBulidingid() {
            return bulidingid;
        }

        public void setBulidingid(String bulidingid) {
            this.bulidingid = bulidingid;
        }

        public String getBuidingname() {
            return buidingname;
        }

        public void setBuidingname(String buidingname) {
            this.buidingname = buidingname;
        }

        public int getBuildinglevel() {
            return buildinglevel;
        }

        public void setBuildinglevel(int buildinglevel) {
            this.buildinglevel = buildinglevel;
        }

        public List<MonitorList> getMonitor() {
            return monitor;
        }

        public void setMonitor(List<MonitorList> monitor) {
            this.monitor = monitor;
        }

        public List<ChildList> getChild() {
            return child;
        }

        public void setChild(List<ChildList> child) {
            this.child = child;
        }
    }

}

/**
 * {
 "msg": "success",
 "code": 0,
 "page": {
 "list": [
 {
 "bulidingid": "001",
 "buidingname": "北电仪表",
 "buildinglevel": 1,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001000001",
 "buidingname": "园区（测试）",
 "buildinglevel": 3,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001000001000001",
 "buidingname": "有建筑预",
 "buildinglevel": 5,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001000001000001000005",
 "buidingname": "无房非预",
 "buildinglevel": 7,
 "monitor": [
 {
 "bulidingid": "001000001000001000005",
 "buildinglevel": 7,
 "comAddress": "555555555551",
 "metername": "无房非预",
 "meterid": "37"
 }
 ],
 "child": [

 ]
 }
 ]
 },
 {
 "bulidingid": "001000001000002",
 "buidingname": "有建筑非预",
 "buildinglevel": 5,
 "monitor": [
 {
 "bulidingid": "001000001000002",
 "buildinglevel": 5,
 "comAddress": "555555555552",
 "metername": "有建筑非预",
 "meterid": "38"
 }
 ],
 "child": [
 {
 "bulidingid": "001000001000002000005",
 "buidingname": "无房非预",
 "buildinglevel": 7,
 "monitor": [
 {
 "bulidingid": "001000001000002000005",
 "buildinglevel": 7,
 "comAddress": "222222222222",
 "metername": "无房非预",
 "meterid": "42"
 }
 ],
 "child": [

 ]
 }
 ]
 }
 ]
 },
 {
 "bulidingid": "001003",
 "buidingname": "邵海松测试区",
 "buildinglevel": 2,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001003000000001",
 "buidingname": "思创大厦测试",
 "buildinglevel": 5,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001003000000001000001",
 "buidingname": "4楼",
 "buildinglevel": 7,
 "monitor": [

 ],
 "child": [
 {
 "bulidingid": "001003000000001000001001",
 "buidingname": "系统部",
 "buildinglevel": 8,
 "monitor": [
 {
 "bulidingid": "001003000000001000001001",
 "buildinglevel": 8,
 "comAddress": "180713120001",
 "metername": "插座180713120001",
 "meterid": "32"
 }
 ],
 "child": [

 ]
 }
 ]
 }
 ]
 }
 ]
 }
 ]
 }
 ]
 }
 }*/
