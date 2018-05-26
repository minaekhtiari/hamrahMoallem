
package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("PackageId")
    @Expose
    private int packageId;
    @SerializedName("ChargCodeId")
    @Expose
    private int chargCodeId;
    @SerializedName("Price")
    @Expose
    private int price;
    @SerializedName("Credit")
    @Expose
    private int credit;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("InsertDateSh")
    @Expose
    private String insertDateSh;
    @SerializedName("InsertDateMi")
    @Expose
    private String insertDateMi;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getChargCodeId() {
        return chargCodeId;
    }

    public void setChargCodeId(int chargCodeId) {
        this.chargCodeId = chargCodeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertDateSh() {
        return insertDateSh;
    }

    public void setInsertDateSh(String insertDateSh) {
        this.insertDateSh = insertDateSh;
    }

    public String getInsertDateMi() {
        return insertDateMi;
    }

    public void setInsertDateMi(String insertDateMi) {
        this.insertDateMi = insertDateMi;
    }

}
