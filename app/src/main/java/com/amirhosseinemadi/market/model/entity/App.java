package com.amirhosseinemadi.market.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class App implements Parcelable{

    private String name;
    private String detail;
    private String icon;
    private String category;
    private String size;
    private String price;
    private String slider;
    @SerializedName("package")
    private String packagee;


    public App(){}


    protected App(Parcel in) {
        name = in.readString();
        detail = in.readString();
        icon = in.readString();
        category = in.readString();
        size = in.readString();
        price = in.readString();
        slider = in.readString();
        packagee = in.readString();
    }

    public static final Creator<App> CREATOR = new Creator<App>() {
        @Override
        public App createFromParcel(Parcel in) {
            return new App(in);
        }

        @Override
        public App[] newArray(int size) {
            return new App[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSlider() {
        return slider;
    }

    public void setSlider(String slider) {
        this.slider = slider;
    }

    public void setPackagee(String packagee)
    {
        this.packagee = packagee;
    }

    public String getPackagee()
    {
        return packagee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeString(icon);
        dest.writeString(category);
        dest.writeString(size);
        dest.writeString(price);
        dest.writeString(slider);
        dest.writeString(packagee);
    }
}
