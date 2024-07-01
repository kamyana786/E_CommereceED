package com.awais.e_commereceed.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    private String addressTitle;
    private String fullName;
    private String street;
    private String phone;
    private String city;
    private String state;

    public Address() {
        this("", "", "", "", "", "");
    }

    public Address(String addressTitle, String fullName, String street, String phone, String city, String state) {
        this.addressTitle = addressTitle;
        this.fullName = fullName;
        this.street = street;
        this.phone = phone;
        this.city = city;
        this.state = state;
    }

    protected Address(Parcel in) {
        addressTitle = in.readString();
        fullName = in.readString();
        street = in.readString();
        phone = in.readString();
        city = in.readString();
        state = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressTitle);
        dest.writeString(fullName);
        dest.writeString(street);
        dest.writeString(phone);
        dest.writeString(city);
        dest.writeString(state);
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStreet() {
        return street;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
