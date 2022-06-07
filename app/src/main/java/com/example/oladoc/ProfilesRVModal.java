package com.example.oladoc;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfilesRVModal  implements Parcelable {
    // creating variables for our different fields.
    private String profileName;
    private String age;
    private String num;
    private String profileId;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        age = age;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        num = num;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    // creating an empty constructor.
    public ProfilesRVModal() {

    }

    protected ProfilesRVModal(Parcel in) {
        profileName = in.readString();
        profileId = in.readString();
        age = in.readString();
        num = in.readString();

    }

    public static final Creator<ProfilesRVModal> CREATOR = new Creator<ProfilesRVModal>() {
        @Override
        public ProfilesRVModal createFromParcel(Parcel in) {
            return new ProfilesRVModal(in);
        }

        @Override
        public ProfilesRVModal[] newArray(int size) {
            return new ProfilesRVModal[size];
        }
    };



    public ProfilesRVModal(String profileId, String profileName, String age, String num) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.age = age;
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profileName);
        dest.writeString(profileId);
        dest.writeString(age);
        dest.writeString(num);
    }
}


