package com.amirhosseinemadi.market.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    private String name;
    private String icon;


    public Category(){}


    protected Category(Parcel in) {
        name = in.readString();
        icon = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
    }
}
