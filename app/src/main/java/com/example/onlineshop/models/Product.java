package com.example.onlineshop.models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private long id;
    private String title;
    private String description;
    private String image;
    private long price;
    private boolean enable;
    private boolean exists;
    private long categoryId;
    private long visitCount;
    private List<Long> colors;
    private List<Long> sizes;
    private List<Long> features;
    private List<Feature> featuresDataList;
    private String addDateStr;
    private List<Color> colorsList;
    private List<Size> sizesList;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public List<Long> getColors() {
        return colors;
    }

    public void setColors(List<Long> colors) {
        this.colors = colors;
    }

    public List<Long> getSizes() {
        return sizes;
    }

    public void setSizes(List<Long> sizes) {
        this.sizes = sizes;
    }

    public List<Long> getFeatures() {
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }

    public List<Feature> getFeaturesDataList() {
        return featuresDataList;
    }

    public void setFeaturesDataList(List<Feature> featuresDataList) {
        this.featuresDataList = featuresDataList;
    }

    public String getAddDateStr() {
        return addDateStr;
    }

    public void setAddDateStr(String addDateStr) {
        this.addDateStr = addDateStr;
    }

    public List<Color> getColorsList() {
        return colorsList;
    }

    public void setColorsList(List<Color> colorsList) {
        this.colorsList = colorsList;
    }

    public List<Size> getSizesList() {
        return sizesList;
    }

    public void setSizesList(List<Size> sizesList) {
        this.sizesList = sizesList;
    }
}
