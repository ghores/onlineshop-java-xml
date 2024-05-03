package com.example.onlineshop.enums;

public class ApiAddress {
    public static String baseDomain = "http://onlineshop.holosen.net:8081";
    public static String baseHttpDomain = "http://onlineshop.holosen.net:8081";
    public static String fileUrl = baseHttpDomain + "/api/utils/upload/files/";

    public static String getFileUrl(String name){
        return fileUrl + name;
    }
}
