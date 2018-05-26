
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultJsonCategoryWithParentChild {

    @SerializedName("IsSuccessfull")
    @Expose
    private boolean isSuccessfull;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private List<CategoryWithParentChild> result;

    public boolean isIsSuccessfull() {
        return isSuccessfull;
    }

    public void setIsSuccessfull(boolean isSuccessfull) {
        this.isSuccessfull = isSuccessfull;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    public List<CategoryWithParentChild> getResult() {
        return result;
    }

    public void setResult(List<CategoryWithParentChild> result) {
        this.result = result;
    }
}