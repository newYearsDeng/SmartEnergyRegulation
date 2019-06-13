package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyd on 2019/6/5.
 * 历史告警信息
 */

public class HistoryWarnBean extends CommonResponse {

    private WarnPage page;

    public WarnPage getPage() {
        return page;
    }

    public void setPage(WarnPage page) {
        this.page = page;
    }

    public class WarnPage implements Serializable {
        private int totalCount;// 12,
        private int pageSize;//10,
        private int totalPage;// 2,
        private int currPage;// 1,
        private List<NowWarnBean.WarnBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<NowWarnBean.WarnBean> getList() {
            return list;
        }

        public void setList(List<NowWarnBean.WarnBean> list) {
            this.list = list;
        }
    }


}
/**
 "page": {
 "totalCount": 12,
 "pageSize": 10,
 "totalPage": 2,
 "currPage": 1,
 "list": [
 {
 "metername": "智能插座",//线路名称
 "eventname": "温度超限",//告警类型
 "eventdata": "超限时刻温度:35.0℃,上报时刻功率:0.0000kW,上报时刻温度:35.0℃",//告警内容
 "eventtime": "2019-03-18 14:10:10",//告警时间
 "isread": 0//是否查阅
 }
 ]
 }
 * */
