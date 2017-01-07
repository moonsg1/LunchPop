package kms.lunchpop;

/**
 * Created by KMS_DESKTOP on 2016-12-26.
 */

public class LunchData {
    private String m_lunch;


    public LunchData(String lunch) {
        this.m_lunch = lunch;
    }

    public String toString() {
        return "lunch: " + m_lunch;
    }

    public String getLunch() {
        return m_lunch;
    }

    public void setLunch(String lunch) {
        this.m_lunch = lunch;
    }
}
