package kms.lunchpop;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by KMS_DESKTOP on 2017-01-08.
 */

public class PreferenceHelper {
    private SharedPreferences mPreference;
    private int mSize;

    // 생성자, activity를 가져와서 생성
    public PreferenceHelper(Activity activity) {
        mPreference = activity.getSharedPreferences("Lunch", MODE_PRIVATE);
        mSize = countDataSize();
    }

    public void saveLunchData(LunchData lunch_data) {
        SharedPreferences.Editor prefsEditor = mPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lunch_data);
        String key = String.valueOf(countDataSize());
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    public LunchData readLunchData(String key) {
        Gson gson = new Gson();
        String json = mPreference.getString(key, "");
        LunchData data = gson.fromJson(json, LunchData.class);
        return data;
    }

    public int countDataSize() {
        int idx = 1;
        while (true) {
            String num_str = String.valueOf(idx);
            LunchData lunch_data = readLunchData(num_str);
            if (lunch_data == null) {
                return idx;
            }
            idx++;
        }
    }

    public int getSize() {
        return mSize;
    }
}