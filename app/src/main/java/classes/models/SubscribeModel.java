
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribeModel {


    @SerializedName("MobileNumber")
    @Expose
    private String MobileNumber;
    @SerializedName("Channel")
    @Expose
    private String Channel;

    public SubscribeModel() {
        Channel = "App";
    }

    public String getMobileNumber() {

        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }
}
