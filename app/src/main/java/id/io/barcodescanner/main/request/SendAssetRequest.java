package id.io.barcodescanner.main.request;

import java.io.Serializable;

/**
 * Dibuat oleh petersam
 */
public class SendAssetRequest implements Serializable {
    private String locationName;
    private String assetCode;
    private String buildingName;
    private int memberCode;
    private int rate;
    private String geoLocation;
    private String photo;
    private String note;

    public SendAssetRequest() {
    }

    public SendAssetRequest(String locationName, String assetCode, String buildingName, int memberCode, int rate, String geoLocation, String photo, String note) {
        this.locationName = locationName;
        this.assetCode = assetCode;
        this.buildingName = buildingName;
        this.memberCode = memberCode;
        this.rate = rate;
        this.geoLocation = geoLocation;
        this.photo = photo;
        this.note = note;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
