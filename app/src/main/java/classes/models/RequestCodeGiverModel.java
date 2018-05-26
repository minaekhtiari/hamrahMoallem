
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCodeGiverModel {

    @SerializedName("MobileNumber")
    @Expose
    private long mobileNumber;

    @SerializedName("requestId")
    @Expose
    private String requestId;

    @SerializedName("pin")
    @Expose
    private long pin;


    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getPin() {
        return pin;
    }

    public void setPin(long pin) {
        this.pin = pin;
    }
}
