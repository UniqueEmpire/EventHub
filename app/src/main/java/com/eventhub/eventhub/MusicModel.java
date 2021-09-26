package com.eventhub.eventhub;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable {

    private String manname;
    private String manphnnum;
    private String offlandnum;
    private String offadd;
    private String emailadd;
    private String comname;
    private String opentime;
    private String clstime;
    private String location;
    private String des;

    public MusicModel() { }

    public MusicModel(String manname, String manphnnum, String offlandnum, String offadd, String emailadd, String comname, String opentime, String clstime, String location, String des) {
        this.manname = manname;
        this.manphnnum = manphnnum;
        this.offlandnum = offlandnum;
        this.offadd = offadd;
        this.emailadd = emailadd;
        this.comname = comname;
        this.opentime = opentime;
        this.clstime = clstime;
        this.location = location;
        this.des = des;
    }

    protected MusicModel(Parcel in) {
        manname = in.readString();
        manphnnum = in.readString();
        offlandnum = in.readString();
        offadd = in.readString();
        emailadd = in.readString();
        comname = in.readString();
        opentime = in.readString();
        clstime = in.readString();
        location = in.readString();
        des = in.readString();
    }

    public static final Creator<MusicModel> CREATOR = new Creator<MusicModel>() {
        @Override
        public MusicModel createFromParcel(Parcel in) {
            return new MusicModel(in);
        }

        @Override
        public MusicModel[] newArray(int size) {
            return new MusicModel[size];
        }
    };

    public String getManname() {
        return manname;
    }

    public void setManname(String manname) {
        this.manname = manname;
    }

    public String getManphnnum() {
        return manphnnum;
    }

    public void setManphnnum(String manphnnum) {
        this.manphnnum = manphnnum;
    }

    public String getOfflandnum() {
        return offlandnum;
    }

    public void setOfflandnum(String offlandnum) {
        this.offlandnum = offlandnum;
    }

    public String getOffadd() {
        return offadd;
    }

    public void setOffadd(String offadd) {
        this.offadd = offadd;
    }

    public String getEmailadd() {
        return emailadd;
    }

    public void setEmailadd(String emailadd) {
        this.emailadd = emailadd;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClstime() {
        return clstime;
    }

    public void setClstime(String clstime) {
        this.clstime = clstime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(manname);
        dest.writeString(manphnnum);
        dest.writeString(offlandnum);
        dest.writeString(offadd);
        dest.writeString(emailadd);
        dest.writeString(comname);
        dest.writeString(opentime);
        dest.writeString(clstime);
        dest.writeString(location);
        dest.writeString(des);
    }
}
