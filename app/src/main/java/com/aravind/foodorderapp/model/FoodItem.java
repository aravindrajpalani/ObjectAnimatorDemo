package com.aravind.foodorderapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aravindraj on 6/19/2016.
 */
public class FoodItem implements Parcelable {


    protected FoodItem(Parcel in) {
        desc = in.readString();
        price = in.readString();
        oldprice = in.readString();
        photo = in.readString();
        menuid = in.readString();
        category = in.readString();
        name = in.readString();
        favoutite = in.readInt();
        count = in.readInt();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    String desc;
    String price;
    String oldprice;

    public FoodItem(String desc, String price, String oldprice, String photo, String menuid, String category, String name, int favoutite, int count) {
        this.desc = desc;
        this.price = price;
        this.oldprice = oldprice;
        this.photo = photo;
        this.menuid = menuid;
        this.category = category;
        this.name = name;
        this.favoutite = favoutite;
        this.count = count;
    }

    String photo;
    String menuid;
    String category;
    String name;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    int favoutite;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }




    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;

    public String getPrice() {
        return price;
    }




    public int getFavoutite() {
        return favoutite;
    }

    public void setFavoutite(int favoutite) {
        this.favoutite = favoutite;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc);
        dest.writeString(price);
        dest.writeString(oldprice);
        dest.writeString(photo);
        dest.writeString(menuid);
        dest.writeString(category);
        dest.writeString(name);
        dest.writeInt(favoutite);
        dest.writeInt(count);
    }
}
