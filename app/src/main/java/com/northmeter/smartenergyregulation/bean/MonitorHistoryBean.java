package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/23.
 * 设备历史曲线
 */

public class MonitorHistoryBean extends CommonResponse{

    public List<HistoryList> list;

    public List<HistoryList> getList() {
        return list;
    }

    public void setList(List<HistoryList> list) {
        this.list = list;
    }

    public class HistoryList implements Serializable{
        public int meterid;//设备id
        public float powertotal;//功率
        public float voltage;//电压
        public float current;//电流
        public float watts;//电能
        public float temperature;//环境温度
        public float frequency;//频率
        public float powerfactor;//功率因数
        public String updatetime;//刷新时间

        public int getMeterid() {
            return meterid;
        }

        public void setMeterid(int meterid) {
            this.meterid = meterid;
        }

        public float getPowertotal() {
            return powertotal;
        }

        public void setPowertotal(float powertotal) {
            this.powertotal = powertotal;
        }

        public float getVoltage() {
            return voltage;
        }

        public void setVoltage(float voltage) {
            this.voltage = voltage;
        }

        public float getCurrent() {
            return current;
        }

        public void setCurrent(float current) {
            this.current = current;
        }

        public float getWatts() {
            return watts;
        }

        public void setWatts(float watts) {
            this.watts = watts;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public float getFrequency() {
            return frequency;
        }

        public void setFrequency(float frequency) {
            this.frequency = frequency;
        }

        public float getPowerfactor() {
            return powerfactor;
        }

        public void setPowerfactor(float powerfactor) {
            this.powerfactor = powerfactor;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }


}

/**
 *
 *      {
 "msg": "success",
 "code": 0,
 "list": [
 {
 "meterid": 1144,//设备id
 "powertotal": 0.0000,//功率
 "voltage": 226.4,//电压
 "current": 0.000,//电流
 "watts": 0.01,//电能
 "temperature": 36.3,//环境温度
 "frequency": 49.95,//频率
 "powerfactor":1.000,//功率因数
 "updatetime": "2019-03-13 15:29:29",//刷新时间

                        }
                        ]
                        }*/