package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("Operator")
    @Expose
    private String operator;
    @SerializedName("OsName")
    @Expose
    private String osName;
    @SerializedName("OsVersion")
    @Expose
    private String osVersion;
    @SerializedName("ApiVersion")
    @Expose
    private String apiVersion;
    @SerializedName("Dpi")
    @Expose
    private String dpi;
    @SerializedName("Resolotion")
    @Expose
    private String resolotion;
    @SerializedName("Manufacture")
    @Expose
    private String manufacture;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ModelName")
    @Expose
    private String modelName;
    @SerializedName("NetworkType")
    @Expose
    private String networkType;
    @SerializedName("NetworkName")
    @Expose
    private String networkName;
    @SerializedName("Hardware")
    @Expose
    private String hardware;
    @SerializedName("Cpu")
    @Expose
    private String cpu;
    @SerializedName("Ram")
    @Expose
    private String ram;

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getResolotion() {
        return resolotion;
    }

    public void setResolotion(String resolotion) {
        this.resolotion = resolotion;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

}
