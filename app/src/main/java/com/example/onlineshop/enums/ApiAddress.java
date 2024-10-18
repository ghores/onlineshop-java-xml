package com.example.onlineshop.enums;

public class ApiAddress {
    public static String baseDomain = "https://javashop.holosen.net";
    public static String baseHttpDomain = "https://javashop.holosen.net";
    public static String fileUrl = baseHttpDomain + "/api/utils/upload/files/";

    public static String getFileUrl(String name){
        return fileUrl + name;
    }
}
