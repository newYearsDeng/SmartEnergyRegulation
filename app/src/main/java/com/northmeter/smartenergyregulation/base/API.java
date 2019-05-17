package com.northmeter.smartenergyregulation.base;

/**
 * Created by dyd on 2018/12/13.
 */

public class API {
    public static String URL_BASE = "http://10.168.1.200:8080/school-hydroelectricity";


    /**账户管理--post  密码需先MD5加密三次，然后使用RSA加密一次*/
    public static String sysLogin = URL_BASE +"/sys/login";

    /**通用懒加载树形接口--get*/
    public static String getTreeBuild = URL_BASE +"/extend/buildingandmeter/getTreeBuild";

    /**楼栋智能插座信息--post*/
    public static String getmonitor = URL_BASE +"/api/supervision/airreport/getmonitor";

    /**房间内智能插座详细信息--post*/
    public static String getallleafmonitor = URL_BASE +"/intelligentoutlet/intelligentoutlet/getallleafmonitor";




}
