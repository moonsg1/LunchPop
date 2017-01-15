package kms.lunchpop;

import android.app.Activity;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by KMS_DESKTOP on 2017-01-08.
 *
 * Sigleton : initialization on demand holder idiom
 */

public class PreferenceMgr {
    private SharedPreferences mPreference;
    private int mMaxDataSize = 30;

    public static int STATE_EMPTY = 1024;
    public static int STATE_NO_LUNCH = 512;

    private static class Singleton {
        private static final PreferenceMgr instance = new PreferenceMgr();
    }

    public static PreferenceMgr getInstance () {
        return Singleton.instance;
    }

    private PreferenceMgr() {
    }

    // activity를 통해 shared preference 를 설정
    public void setPreference(Activity activity) {
        if (mPreference == null){
            mPreference = activity.getSharedPreferences("Lunch", MODE_PRIVATE);
        }
    }

    // LunchData를 preference에 저장한다.
    public void saveLunchData(LunchData lunch_data) {
        SharedPreferences.Editor prefsEditor = mPreference.edit();
        Gson gson = new Gson();

        // lunch data 객체를 json형식으로 저장 (객체지만 일단... 데이터수준)
        String key = String.valueOf(findEmptyIndex());
        lunch_data.setKey(key);
        String json = gson.toJson(lunch_data);

        // 실제 저장
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    // LunchData를 preference에서 읽어온다
    public LunchData readLunchData(String key) {
        Gson gson = new Gson();
        String json = mPreference.getString(key, "");
        LunchData data = gson.fromJson(json, LunchData.class);
        return data;
    }

    // LunchData를 preference에 저장한다.
    public void editLunchData(String key, LunchData lunch_data) {
        SharedPreferences.Editor prefsEditor = mPreference.edit();
        Gson gson = new Gson();

        // lunch data 객체를 json형식으로 저장 (객체지만 일단... 데이터수준)
        lunch_data.setKey(key);
        String json = gson.toJson(lunch_data);

        // 실제 저장
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    // LunchData를 preference에서 삭제한다
    public void removeLunchData(String key) {
        SharedPreferences.Editor prefsEditor = mPreference.edit();
        prefsEditor.remove(key);
        prefsEditor.apply();
    }

    // LunchData를 전부 삭제 한다
    public void removeAll() {
        SharedPreferences.Editor prefsEditor = mPreference.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }

    // LunchData 비어있는 인덱스를 찾는다
    private int findEmptyIndex() {
        int ret_idx = 0;
        for (int i = 1; i < mMaxDataSize ; i++) {
            boolean isContain = mPreference.contains(String.valueOf(i));
            if ((!isContain)) {
                ret_idx = i;
                break;
            }
        }
        return ret_idx;
    }

    // 순수한 사이즈 체크
    public int getSize() {
        return mPreference.getAll().size();
    }

    // 최대사이즈를 반환
    public int getMaxSize() {
        return mMaxDataSize;
    }

    // preference 내의 데이타를 리스트로 반환
    public ArrayList<LunchData> getDataList() {
        ArrayList<LunchData> l_data = new ArrayList<>();
        for (int i = 1; i < getMaxSize(); i++) {
            String key = String.valueOf(i);
            LunchData lunch_data = readLunchData(key);
            if (lunch_data != null) {
                l_data.add(lunch_data);
            }
        }

        return l_data;
    }
}