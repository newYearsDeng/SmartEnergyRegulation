package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/5/22.
 * 房间内智能插座信息
 */

public class AllleafmonitorBean extends CommonResponse {
    public List<MeterList> list;

    public List<MeterList> getList() {
        return list;
    }

    public void setList(List<MeterList> list) {
        this.list = list;
    }

    public class MeterList implements Serializable{
        public int meterid;//设备id
        public String metername;//设备名称
        public String comaddress;//设备编号
        public String status;//": "跳闸",//开关状态
        public int glxx;//": 1,//功率下限
        public String statement;//": "正常",//设备状态
        public double powertotal;//": 0.0000,//功率
        public double voltage;//": 226.4,//电压
        public double current;//": 0.000,//电流
        public double watts;//": 0.01,//电能
        public double temperature;//": 36.3,//环境温度
        public double frequency;//": 49.95,//频率
        public double powerfactor;//":1.000,//功率因数
        public String updatetime;//": "2019-03-13 15:29:29"//刷新时间

        public int getMeterid() {
            return meterid;
        }

        public void setMeterid(int meterid) {
            this.meterid = meterid;
        }

        public String getMetername() {
            return metername;
        }

        public void setMetername(String metername) {
            this.metername = metername;
        }

        public String getComaddress() {
            return comaddress;
        }

        public void setComaddress(String comaddress) {
            this.comaddress = comaddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getGlxx() {
            return glxx;
        }

        public void setGlxx(int glxx) {
            this.glxx = glxx;
        }

        public String getStatement() {
            return statement;
        }

        public void setStatement(String statement) {
            this.statement = statement;
        }

        public double getPowertotal() {
            return powertotal;
        }

        public void setPowertotal(double powertotal) {
            this.powertotal = powertotal;
        }

        public double getVoltage() {
            return voltage;
        }

        public void setVoltage(double voltage) {
            this.voltage = voltage;
        }

        public double getCurrent() {
            return current;
        }

        public void setCurrent(double current) {
            this.current = current;
        }

        public double getWatts() {
            return watts;
        }

        public void setWatts(double watts) {
            this.watts = watts;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getFrequency() {
            return frequency;
        }

        public void setFrequency(double frequency) {
            this.frequency = frequency;
        }

        public double getPowerfactor() {
            return powerfactor;
        }

        public void setPowerfactor(double powerfactor) {
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
 *     {
 "msg": "success",
 "code": 0,
 "list": [
 {
 "meterid": 1144,//设备id
 "metername": "智能插座",//设备名称
 "comaddress": "000000111111",//设备编号
 "status": "跳闸",//开关状态
 "glxx": 1,//功率下限
 "statement": "正常",//设备状态
 "powertotal": 0.0000,//功率
 "voltage": 226.4,//电压
 "current": 0.000,//电流
 "watts": 0.01,//电能
 "temperature": 36.3,//环境温度
 "frequency": 49.95,//频率
 "powerfactor":1.000,//功率因数
 "updatetime": "2019-03-13 15:29:29"//刷新时间
 }
 ]
 }*/