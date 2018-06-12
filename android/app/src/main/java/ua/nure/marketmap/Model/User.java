package ua.nure.marketmap.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class User implements Parcelable {
    public static User GUEST = new User(-1, "Guest");
    private int mId;
    private String mEmail;


    public User(int id, String email) {
        mId = id;
        mEmail = email;
    }
    public User(Parcel in) {
        mId = in.readInt();
        mEmail = in.readString();
    }

    public int getId() {
        return mId;
    }
    public void setId(int mId) {
        this.mId = mId;
    }
    public String getEmail() {
        return mEmail;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mEmail);
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mId == user.mId &&
                Objects.equals(mEmail, user.mEmail);
    }
    @Override
    public int hashCode() {

        return Objects.hash(mId, mEmail);
    }
}
