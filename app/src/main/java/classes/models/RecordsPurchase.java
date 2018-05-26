
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordsPurchase {

    @SerializedName("PurchaseId")
    @Expose
    private int purchaseId;
    @SerializedName("ContentSubject")
    @Expose
    private String contentSubject;
    @SerializedName("ContentId")
    @Expose
    private int contentId;
    @SerializedName("Price")
    @Expose
    private int price;
    @SerializedName("CreditBeforeBuy")
    @Expose
    private int creditBeforeBuy;
    @SerializedName("CreditAfterBuy")
    @Expose
    private int creditAfterBuy;
    @SerializedName("InsertDateSh")
    @Expose
    private String insertDateSh;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getContentSubject() {
        return contentSubject;
    }

    public void setContentSubject(String contentSubject) {
        this.contentSubject = contentSubject;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCreditBeforeBuy() {
        return creditBeforeBuy;
    }

    public void setCreditBeforeBuy(int creditBeforeBuy) {
        this.creditBeforeBuy = creditBeforeBuy;
    }

    public int getCreditAfterBuy() {
        return creditAfterBuy;
    }

    public void setCreditAfterBuy(int creditAfterBuy) {
        this.creditAfterBuy = creditAfterBuy;
    }

    public String getInsertDateSh() {
        return insertDateSh;
    }

    public void setInsertDateSh(String insertDateSh) {
        this.insertDateSh = insertDateSh;
    }

}
