
package classes.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPurchase {

    @SerializedName("TotalRecord")
    @Expose
    private int totalRecord;
    @SerializedName("PageNumber")
    @Expose
    private int pageNumber;
    @SerializedName("RowCount")
    @Expose
    private int rowCount;
    @SerializedName("Records")
    @Expose
    private List<RecordsPurchase> recordsPurchase = null;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<RecordsPurchase> getRecordsPurchase() {
        return recordsPurchase;
    }

    public void setRecordsPurchase(List<RecordsPurchase> recordsPurchase) {
        this.recordsPurchase = recordsPurchase;
    }

}
