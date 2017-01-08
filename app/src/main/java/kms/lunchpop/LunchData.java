package kms.lunchpop;

/**
 * Created by KMS_DESKTOP on 2016-12-26.
 */

public class LunchData {
    private String mLunch;
    private String mKey;

    public LunchData(String lunch) {
        this.mLunch = lunch;
    }

    public String toString() {
        return "lunch: " + mLunch + "\nkey: " + mKey;
    }

    public String getLunch() {
        return mLunch;
    }

    public void setLunch(String lunch) {
        this.mLunch = lunch;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String mKey) {
        this.mKey = mKey;
    }
}
