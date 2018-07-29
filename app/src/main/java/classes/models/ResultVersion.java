package classes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultVersion {
    @SerializedName("AppVersionId")
    @Expose
    private Integer appVersionId;
    @SerializedName("AppType")
    @Expose
    private Integer appType;
    @SerializedName("IsForceUpdate")
    @Expose
    private Boolean isForceUpdate;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("LastVersionCode")
    @Expose
    private Integer lastVersionCode;
    @SerializedName("LastVersion")
    @Expose
    private String lastVersion;
    @SerializedName("DownloadLink")
    @Expose
    private String downloadLink;
    @SerializedName("InsertDateMi")
    @Expose
    private String insertDateMi;
    @SerializedName("InsertDateSh")
    @Expose
    private String insertDateSh;

    public Integer getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Integer appVersionId) {
        this.appVersionId = appVersionId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Boolean getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(Boolean isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getLastVersionCode() {
        return lastVersionCode;
    }

    public void setLastVersionCode(Integer lastVersionCode) {
        this.lastVersionCode = lastVersionCode;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getInsertDateMi() {
        return insertDateMi;
    }

    public void setInsertDateMi(String insertDateMi) {
        this.insertDateMi = insertDateMi;
    }

    public String getInsertDateSh() {
        return insertDateSh;
    }

    public void setInsertDateSh(String insertDateSh) {
        this.insertDateSh = insertDateSh;
    }
}
