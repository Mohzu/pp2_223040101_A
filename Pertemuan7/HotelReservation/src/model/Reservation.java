package Pertemuan7.HotelReservation.src.model;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class Reservation {
    private int id;
    private String name;
    private String date;  
    private String roomType;
    private String roomPreference;
    private String facilities;
    private int guestCount;
    private int duration;
    private List<String> paymentMethods;  // Menyimpan sebagai string yang dipisahkan koma
    private String review;
    private boolean wifi;
    private boolean breakfast;

    // Getter dan Setter untuk ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter dan Setter lainnya
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPreference() {
        return roomPreference;
    }

    public void setRoomPreference(String roomPreference) {
        this.roomPreference = roomPreference;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Menggunakan String untuk menyimpan metode pembayaran yang dipisahkan koma
    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

   
}
