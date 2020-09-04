package com.example.shater.models;

public class reciverFromProvider {

    String id_provider ;
    String name_provider ;
    String email_provider;
    String phone_number;
    String category ;
    String description;
    Double lat_provider;
    Double lng_provider;
    String  urlImage ;
    float munStartRating ;
    float price ;

    public reciverFromProvider(String id_provider, String name_provider, String email_provider, String phone_number, String category, String description, Double lat_provider, Double lng_provider, String urlImage, float munStartRating, float price) {
        this.id_provider = id_provider;
        this.name_provider = name_provider;
        this.email_provider = email_provider;
        this.phone_number = phone_number;
        this.category = category;
        this.description = description;
        this.lat_provider = lat_provider;
        this.lng_provider = lng_provider;
        this.urlImage = urlImage;
        this.munStartRating = munStartRating;
        this.price = price;
    }

    public String getId_provider() {
        return id_provider;
    }

    public void setId_provider(String id_provider) {
        this.id_provider = id_provider;
    }

    public String getName_provider() {
        return name_provider;
    }

    public void setName_provider(String name_provider) {
        this.name_provider = name_provider;
    }

    public String getEmail_provider() {
        return email_provider;
    }

    public void setEmail_provider(String email_provider) {
        this.email_provider = email_provider;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat_provider() {
        return lat_provider;
    }

    public void setLat_provider(Double lat_provider) {
        this.lat_provider = lat_provider;
    }

    public Double getLng_provider() {
        return lng_provider;
    }

    public void setLng_provider(Double lng_provider) {
        this.lng_provider = lng_provider;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public float getMunStartRating() {
        return munStartRating;
    }

    public void setMunStartRating(float munStartRating) {
        this.munStartRating = munStartRating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
