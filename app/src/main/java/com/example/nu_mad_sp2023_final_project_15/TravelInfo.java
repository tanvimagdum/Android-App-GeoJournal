package com.example.nu_mad_sp2023_final_project_15;

public class TravelInfo {

    private String trPlace;
    private String trDate;
    private String trItinerary;
    private String trExpense;
    private String trCulture;
    private String trLanguage;
    private String trReflection;
    private String trTips;
    private String trImageUrl;

    public TravelInfo() {
    }

    public TravelInfo(String trPlace, String trDate, String trItinerary,
                      String trExpense, String trCulture, String trLanguage,
                      String trReflection, String trTips, String trImageUrl) {
        this.trPlace = trPlace;
        this.trDate = trDate;
        this.trItinerary = trItinerary;
        this.trExpense = trExpense;
        this.trCulture = trCulture;
        this.trLanguage = trLanguage;
        this.trReflection = trReflection;
        this.trTips = trTips;
        this.trImageUrl = trImageUrl;
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

    public String getTrImageUrl() {
        return trImageUrl;
    }

    public void setTrImageUrl(String trImageUrl) {
        this.trImageUrl = trImageUrl;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
                "trPlace='" + trPlace + '\'' +
                ", trDate='" + trDate + '\'' +
                ", trItinerary='" + trItinerary + '\'' +
                ", trExpense='" + trExpense + '\'' +
                ", trCulture='" + trCulture + '\'' +
                ", trLanguage='" + trLanguage + '\'' +
                ", trReflection='" + trReflection + '\'' +
                ", trTips='" + trTips + '\'' +
                ", trImageUrl='" + trImageUrl + '\'' +
                '}';
    }
}
