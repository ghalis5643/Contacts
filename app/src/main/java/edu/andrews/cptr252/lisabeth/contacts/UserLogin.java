package edu.andrews.cptr252.lisabeth.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserLogin implements Parcelable {
    private String userId = "";

    private String username = "";
    private String password = "";

    private  Long id = -1L;


    private UserLogin(Parcel source){
        String[] data = new String[5];
        source.readStringArray(data);
        setName(data[0]);
        setPhone(data[1]);
        setAddress(data[2]);
        setPhoto(data[3]);
        setId(Long.parseLong(data[4]));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                getName(),getPhone(),getAddress(),getPhoto(), String.valueOf(getId())
        });
    }

    public static final Creator<UserLogin> CREATOR = new Creator<UserLogin>(){

        @Override
        public UserLogin createFromParcel(Parcel source){
            return new UserLogin(source);
        }
        @Override
        public UserLogin[] newArray(int size){
            return new UserLogin[0];
        }
    };
}
