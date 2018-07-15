
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultJsonPurchase {

    @SerializedName("IsSuccessful")
    @Expose
    private boolean isSuccessfull;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("Result")
    @Expose
    private ResultPurchase resultPurchase;

    public boolean isIsSuccessfull() {
        return isSuccessfull;
    }

    public void setIsSuccessfull(boolean isSuccessfull) {
        this.isSuccessfull = isSuccessfull;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ResultPurchase getResultPurchase() {
        return resultPurchase;
    }

    public void setResultPurchase(ResultPurchase resultPurchase) {
        this.resultPurchase = resultPurchase;
    }

}
