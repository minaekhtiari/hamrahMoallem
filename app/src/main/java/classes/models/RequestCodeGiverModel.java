
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCodeGiverModel {

//    @SerializedName("MobileNumber")
//    @Expose
//    private long mobileNumber;

    @SerializedName("TransactionId")
    @Expose
    private String transactionId;

    @SerializedName("pin")
    @Expose
    private long pin;


//    public long getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(long mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getPin() {
        return pin;
    }

    public void setPin(long pin) {
        this.pin = pin;
    }
}
