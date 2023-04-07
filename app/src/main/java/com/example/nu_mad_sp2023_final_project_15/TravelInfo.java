package com.example.nu_mad_sp2023_final_project_15;

import com.google.type.LatLng;

import java.util.List;

public class TravelInfo {

    private LatLng trLocation;
    private String trPlace;
    private String trDate;
    private String trItinerary;
    private String trExpense;
    private String trCulture;
    private String trLanguage;
    private String trReflection;
    private String trTips;
    private List<String> trImageUrl;
    private String userId;

    public TravelInfo() {
    }

    public TravelInfo(LatLng trLocation, String trPlace, String trDate, String trItinerary,
                      String trExpense, String trCulture, String trLanguage,
                      String trReflection, String trTips, List<String> trImageUrl, String userId) {
        this.
        this.trPlace = trPlace;
        this.trDate = trDate;
        this.trItinerary = trItinerary;
        this.trExpense = trExpense;
        this.trCulture = trCulture;
        this.trLanguage = trLanguage;
        this.trReflection = trReflection;
        this.trTips = trTips;
        this.trImageUrl = trImageUrl;
        this.userId = userId;
    }

    public String getTrPlace() {
        return trPlace;
    }

    public void setTrPlace(String trPlace) {
        this.trPlace = trPlace;
    }

    public String getTrDate() {
        return trDate;
    }

    public void setTrDate(String trDate) {
        this.trDate = trDate;
    }

    public String getTrItinerary() {
        return trItinerary;
    }

    public void setTrItinerary(String trItinerary) {
        this.trItinerary = trItinerary;
    }

    public String getTrExpense() {
        return trExpense;
    }

    public void setTrExpense(String trExpense) {
        this.trExpense = trExpense;
    }

    public String getTrCulture() {
        return trCulture;
    }

    public void setTrCulture(String trCulture) {
        this.trCulture = trCulture;
    }

    public String getTrLanguage() {
        return trLanguage;
    }

    public void setTrLanguage(String trLanguage) {
        this.trLanguage = trLanguage;
    }

    public String getTrReflection() {
        return trReflection;
    }

    public void setTrReflection(String trReflection) {
        this.trReflection = trReflection;
    }

    public String getTrTips() {
        return trTips;
    }

    public void setTrTips(String trTips) {
        this.trTips = trTips;
    }

    public List<String> getTrImageUrl() {
        return trImageUrl;
    }

    public void setTrImageUrl(List<String> trImageUrl) {
        this.trImageUrl = trImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
