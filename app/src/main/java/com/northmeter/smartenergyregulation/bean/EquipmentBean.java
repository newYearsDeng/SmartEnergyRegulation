package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;

/**
 * Created by dyd on 2019/5/25.
 * 设备汇总
 */

public class EquipmentBean extends CommonResponse{

    public Equipment equipment;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public class Equipment implements Serializable{
        public Concentrator concentrator;
        public Meter meter;

        public Concentrator getConcentrator() {
            return concentrator;
        }

        public void setConcentrator(Concentrator concentrator) {
            this.concentrator = concentrator;
        }

        public Meter getMeter() {
            return meter;
        }

        public void setMeter(Meter meter) {
            this.meter = meter;
        }
    }


    public class Concentrator implements Serializable{
        public int inuse;
        public int online;
        public int sum;

        public int getInuse() {
            return inuse;
        }

        public void setInuse(int inuse) {
            this.inuse = inuse;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }

    public class Meter implements Serializable{
        public int normal;
        public int inuse;
        public int sum;

        public int getNormal() {
            return normal;
        }

        public void setNormal(int normal) {
            this.normal = normal;
        }

        public int getInuse() {
            return inuse;
        }

        public void setInuse(int inuse) {
            this.inuse = inuse;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }





}



/**"equipment":{
     "concentrator":{// 采集设备汇总
         "inuse":"2",        // 使用中
         "online":"2",        // 在线数
         "sum":"4"            // 总数
     },
     "meter":{ // 计量设备汇总
         "normal":"1",        // 通讯正常数
         "inuse":"5",        // 使用中
         "sum":"9"            // 总数
 }
 }*/