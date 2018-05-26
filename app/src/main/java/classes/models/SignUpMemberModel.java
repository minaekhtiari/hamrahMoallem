
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpMemberModel {

    @SerializedName("Sex")
    @Expose
    private boolean sex;
    @SerializedName("MobileNumber")
    @Expose
    private long MobileNumber;
    @SerializedName("LevelId")
    @Expose
    private int LevelId;
    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;


    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public long getMobileNumber() {

        return MobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getLevelId() {
        return LevelId;
    }

    public void setLevelId(int levelId) {
        LevelId = levelId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
