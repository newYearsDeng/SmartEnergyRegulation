package com.northmeter.smartenergyregulation.base;

/**
 * Created by dyd on 2018/12/13.
 */

public class API {
    //public static String URL_BASE = "http://10.168.1.200:8080/school-hydroelectricity";
    public static String URL_BASE = "https://systomcat.beidiancloud.com/school-hydroelectricity";



    /**账户管理--post  密码需先MD5加密三次，然后使用RSA加密一次*/
    public static String sysLogin = URL_BASE +"/sys/login";

    /**年度实收入总计--post*/
    public static String getAnnunCharge = URL_BASE +"/extend/leaderview/getAnnunCharge";

    /**年度实支出总计,实际使用--POST*/
    public static String getYearlyMount = URL_BASE +"/extend/leaderview/getYearlyMount";

    /** 设备汇总--POST*/
    public static String getEquipment = URL_BASE +"/extend/leaderview/getEquipment";







    /**通用懒加载树形接口--get*/
    public static String getTreeBuild = URL_BASE +"/extend/buildingandmeter/getTreeBuild";

    /**楼栋智能插座信息--post*/
    public static String getmonitor = URL_BASE +"/api/supervision/airreport/getmonitor";

    /**房间内智能插座详细信息--post*/
    public static String getallleafmonitor = URL_BASE +"/intelligentoutlet/intelligentoutlet/getallleafmonitor";

    /**非房间内智能插座统计信息--post */
    public static String getallbuildingmonitor = URL_BASE +"/intelligentoutlet/intelligentoutlet/getallbuildingmonitor";



    /**单个插座历史数据曲线--get*/
    public static String getMonitorHistory = URL_BASE +"/intelligentoutlet/intelligentoutlet/getMonitorHistory";

    /**用电用水控制-操作--post*/
    public static String controlRealSave = URL_BASE +"/comespecialtask/comespecialtask/realSave";

    /**当前告警信息--get*/
    public static String getnowwarn = URL_BASE +"/intelligentoutlet/intelligentoutlet/getnowwarn";

    /**历史告警信息--post*/
    public static String gethistorywarn = URL_BASE +"/intelligentoutlet/intelligentoutlet/gethistorywarn";


}
