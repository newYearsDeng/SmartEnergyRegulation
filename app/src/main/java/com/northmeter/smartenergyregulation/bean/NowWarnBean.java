package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/6/5.
 * 当前告警信息
 */

public class NowWarnBean extends CommonResponse {

    private List<WarnBean> list;

    public List<WarnBean> getList() {
        return list;
    }

    public void setList(List<WarnBean> list) {
        this.list = list;
    }

    public class WarnBean implements Serializable{
        private String metername;// "智能插座",//路线名称
        private String eventname;// "温度超限",//告警类型
        private String eventdata;// 超限时刻温度:35.0℃,上报时刻功率:0.0000kW,上报时刻温度:35.0℃",//告警内容
        private String eventtime;// "2019-03-18 14:10:10",//告警时间
        private int isread;// 0

        public String getMetername() {
            return metername;
        }

        public void setMetername(String metername) {
            this.metername = metername;
        }

        public String getEventname() {
            return eventname;
        }

        public void setEventname(String eventname) {
            this.eventname = eventname;
        }

        public String getEventdata() {
            return eventdata;
        }

        public void setEventdata(String eventdata) {
            this.eventdata = eventdata;
        }

        public String getEventtime() {
            return eventtime;
        }

        public void setEventtime(String eventtime) {
            this.eventtime = eventtime;
        }

        public int getIsread() {
            return isread;
        }

        public void setIsread(int isread) {
            this.isread = isread;
        }
    }

}
/**
 "metername": "智能插座",//路线名称
 "eventname": "温度超限",//告警类型
 "eventdata": "超限时刻温度:35.0℃,上报时刻功率:0.0000kW,上报时刻温度:35.0℃",//告警内容
 "eventtime": "2019-03-18 14:10:10",//告警时间
 "isread": 0
 * */
