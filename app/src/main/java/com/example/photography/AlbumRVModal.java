package com.example.photography;

import android.os.Parcel;
import android.os.Parcelable;

public class AlbumRVModal implements Parcelable {
    private String albumName;
    private String albumAddress;
    private String albumNumber;
    private String albumImage;
    private String albumLink;
    private String albumDescription;
    private String albumID;

    public AlbumRVModal(){

    }

    public AlbumRVModal(String albumName, String albumAddress, String albumNumber, String albumImage, String albumLink, String albumDescription, String albumID) {
        this.albumName = albumName;
        this.albumAddress = albumAddress;
        this.albumNumber = albumNumber;
        this.albumImage = albumImage;
        this.albumLink = albumLink;
        this.albumDescription = albumDescription;
        this.albumID = albumID;
    }

    protected AlbumRVModal(Parcel in) {
        albumName = in.readString();
        albumAddress = in.readString();
        albumNumber = in.readString();
        albumImage = in.readString();
        albumLink = in.readString();
        albumDescription = in.readString();
        albumID = in.readString();
    }

    public static final Creator<AlbumRVModal> CREATOR = new Creator<AlbumRVModal>() {
        @Override
        public AlbumRVModal createFromParcel(Parcel in) {
            return new AlbumRVModal(in);
        }

        @Override
        public AlbumRVModal[] newArray(int size) {
            return new AlbumRVModal[size];
        }
    };

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAddress() {
        return albumAddress;
    }

    public void setAlbumAddress(String albumAddress) {
        this.albumAddress = albumAddress;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getAlbumLink() {
        return albumLink;
    }

    public void setAlbumLink(String albumLink) {
        this.albumLink = albumLink;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(albumName);
        parcel.writeString(albumAddress);
        parcel.writeString(albumNumber);
        parcel.writeString(albumImage);
        parcel.writeString(albumLink);
        parcel.writeString(albumDescription);
        parcel.writeString(albumID);
    }
}
