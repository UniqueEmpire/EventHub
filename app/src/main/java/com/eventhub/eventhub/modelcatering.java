package com.eventhub.eventhub;

import android.os.Parcel;
import android.os.Parcelable;

public class modelcatering implements Parcelable {
    private String comname;
    private String manname;
    private String manphnnum;
    private String offlandnum;
    private String offadd;
    private String catertype;
    private String deltype;
    private String opentime;
    private String clstime;
    private String des;
    private String opendays;
    private String location;
    private String caterID;
    private String logourl;

    public modelcatering() { }

    public modelcatering(String comname, String manname, String manphnnum, String offlandnum, String offadd, String catertype, String deltype, String opentime, String clstime, String des, String opendays, String location,String caterID,String logourl) {
        this.comname = comname;
        this.manname = manname;
        this.manphnnum = manphnnum;
        this.offlandnum = offlandnum;
        this.offadd = offadd;
        this.catertype = catertype;
        this.deltype = deltype;
        this.opentime = opentime;
        this.clstime = clstime;
        this.des = des;
        this.opendays = opendays;
        this.location = location;
        this.caterID=caterID;
        this.logourl=logourl;
    }
    protected modelcatering(Parcel in) {
        comname = in.readString();
        manname = in.readString();
        manphnnum = in.readString();
        offlandnum = in.readString();
        offadd = in.readString();
        catertype = in.readString();
        deltype = in.readString();
        opentime = in.readString();
        clstime = in.readString();
        des = in.readString();
        opendays = in.readString();
        location = in.readString();
        caterID = in.readString();
        logourl = in.readString();
    }

    public static final Creator<modelcatering> CREATOR = new Creator<modelcatering>() {
        @Override
        public modelcatering createFromParcel(Parcel in) {
            return new modelcatering(in);
        }

        @Override
        public modelcatering[] newArray(int size) {
            return new modelcatering[size];
        }
    };

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getCaterID() {
        return caterID;
    }

    public void setCaterID(String caterID) {
        this.caterID = caterID;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

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

    public String getCatertype() {
        return catertype;
    }

    public void setCatertype(String catertype) {
        this.catertype = catertype;
    }

    public String getDeltype() {
        return deltype;
    }

    public void setDeltype(String deltype) {
        this.deltype = deltype;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getOpendays() {
        return opendays;
    }

    public void setOpendays(String opendays) {
        this.opendays = opendays;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comname);
        dest.writeString(manname);
        dest.writeString(manphnnum);
        dest.writeString(offlandnum);
        dest.writeString(offadd);
        dest.writeString(catertype);
        dest.writeString(deltype);
        dest.writeString(opentime);
        dest.writeString(clstime);
        dest.writeString(des);
        dest.writeString(opendays);
        dest.writeString(location);
        dest.writeString(caterID);
        dest.writeString(logourl);
    }
}
