package com.kun.randomusers.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UsersListPage implements Parcelable {

    private int page;
    private String seed;
    private ArrayList<User> users;

    public UsersListPage() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    protected UsersListPage(Parcel in) {
        page = in.readInt();
        seed = in.readString();
        if (in.readByte() == 0x01) {
            users = new ArrayList<User>();
            in.readList(users, User.class.getClassLoader());
        } else {
            users = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeString(seed);
        if (users == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(users);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UsersListPage> CREATOR = new Parcelable.Creator<UsersListPage>() {
        @Override
        public UsersListPage createFromParcel(Parcel in) {
            return new UsersListPage(in);
        }

        @Override
        public UsersListPage[] newArray(int size) {
            return new UsersListPage[size];
        }
    };
}
