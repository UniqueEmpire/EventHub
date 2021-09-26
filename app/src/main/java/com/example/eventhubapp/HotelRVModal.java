package com.example.eventhubapp;

import android.os.Parcel;
import android.os.Parcelable;

public class HotelRVModal implements Parcelable {
    private String hotelName;
    private String hotelAddress;
    private String hotelNumber;
    private String hotelEmail;
    private String hotelImage;
    private String hotelPrice;
    private String hotelDescription;
    private String hotelID;

    public HotelRVModal(){

    }

    public HotelRVModal(String hotelName, String hotelAddress, String hotelNumber,String hotelEmail, String hotelImage, String hotelPrice, String hotelDescription, String hotelID) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelNumber = hotelNumber;
        this.hotelEmail = hotelEmail;
        this.hotelImage = hotelImage;
        this.hotelPrice = hotelPrice;
        this.hotelDescription = hotelDescription;
        this.hotelID = hotelID;
    }

    protected HotelRVModal(Parcel in) {
        hotelName = in.readString();
        hotelAddress = in.readString();
        hotelNumber = in.readString();
        hotelEmail = in.readString();
        hotelImage = in.readString();
        hotelPrice = in.readString();
        hotelDescription = in.readString();
        hotelID = in.readString();
    }

    public static final Creator<HotelRVModal> CREATOR = new Creator<HotelRVModal>() {
        @Override
        public HotelRVModal createFromParcel(Parcel in) {
            return new HotelRVModal(in);
        }

        @Override
        public HotelRVModal[] newArray(int size) {
            return new HotelRVModal[size];
        }
    };

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelNumber() {
        return hotelNumber;
    }

    public void setHotelNumber(String hotelNumber) {
        this.hotelNumber = hotelNumber;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hotelName);
        parcel.writeString(hotelAddress);
        parcel.writeString(hotelNumber);
        parcel.writeString(hotelEmail);
        parcel.writeString(hotelImage);
        parcel.writeString(hotelPrice);
        parcel.writeString(hotelDescription);
        parcel.writeString(hotelID);
    }
}
