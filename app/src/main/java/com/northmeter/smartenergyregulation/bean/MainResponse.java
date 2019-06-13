package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;

/**
 * Created by dyd on 2019/5/24.
 */

public class MainResponse extends CommonResponse{
    public YearlyMount yearlyMount;
    public AnnunCharge annuncharge;

    public AnnunCharge getAnnuncharge() {
        return annuncharge;
    }

    public void setAnnuncharge(AnnunCharge annuncharge) {
        this.annuncharge = annuncharge;
    }

    public YearlyMount getYearlyMount() {
        return yearlyMount;
    }

    public void setYearlyMount(YearlyMount yearlyMount) {
        this.yearlyMount = yearlyMount;
    }

    /** 年度实收入总计*/
    public class AnnunCharge implements Serializable{
        /**"annuncharge":{
         "waterReturn":"1.00",// 用水退费
         "powerReturn":"97886.18",// 用电退费
         "waterCharge":"3.00",// 用水充值
         "powerCharge":"11896.84"// 用电充值
         }*/
        private double waterReturn;
        private double powerReturn;
        private double waterCharge;
        private double powerCharge;

        public double getWaterReturn() {
            return waterReturn;
        }

        public void setWaterReturn(double waterReturn) {
            this.waterReturn = waterReturn;
        }

        public double getPowerReturn() {
            return powerReturn;
        }

        public void setPowerReturn(double powerReturn) {
            this.powerReturn = powerReturn;
        }

        public double getWaterCharge() {
            return waterCharge;
        }

        public void setWaterCharge(double waterCharge) {
            this.waterCharge = waterCharge;
        }

        public double getPowerCharge() {
            return powerCharge;
        }

        public void setPowerCharge(double powerCharge) {
            this.powerCharge = powerCharge;
        }
    }


    /** 年度实支出总计*/
    public class YearlyMount implements Serializable{
        /**"yearlyMount":{
         "powerNum":"2.00",
         "waterNum":"5.00",
         "powerMount":"100.00",
         "waterMount":"50.00",
         "updateTime": "2018-07-21 14:57:09"
         }*/

        private double powerNum;
        private double waterNum;
        private double powerMount;
        private double waterMount;
        private String updateTime;

        public double getPowerNum() {
            return powerNum;
        }

        public void setPowerNum(double powerNum) {
            this.powerNum = powerNum;
        }

        public double getWaterNum() {
            return waterNum;
        }

        public void setWaterNum(double waterNum) {
            this.waterNum = waterNum;
        }

        public double getPowerMount() {
            return powerMount;
        }

        public void setPowerMount(double powerMount) {
            this.powerMount = powerMount;
        }

        public double getWaterMount() {
            return waterMount;
        }

        public void setWaterMount(double waterMount) {
            this.waterMount = waterMount;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

}
