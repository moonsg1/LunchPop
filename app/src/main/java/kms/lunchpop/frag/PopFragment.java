/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */

package kms.lunchpop.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kms.lunchpop.LunchData;
import kms.lunchpop.PreferenceMgr;
import kms.lunchpop.R;

public class PopFragment extends LunchFragment {
    private ArrayList<LunchData> mLunchDataList;

    public PopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pop_view, container, false);

        // preference 등록
        this.mPrefMgr = PreferenceMgr.getInstance();
        mLunchDataList = mPrefMgr.getDataList();

        // pop button listener 등록
        Button pop_button = (Button) mView.findViewById(R.id.pop_button) ;
        pop_button.setOnClickListener(clickPopButton());

        // reset button listener 등록
        Button reset_button = (Button) mView.findViewById(R.id.reset_button) ;
        reset_button.setOnClickListener(clickResetButton());

        return mView;
    }

    // pop button listener
    private Button.OnClickListener clickPopButton() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = (TextView) mView.findViewById(R.id.pop_text);
                LunchData pop_data = getNextPopLunch();
                if (pop_data == null) {
                    showToast("다시 시도해주세요!");
                }else{
                    textView1.setText(pop_data.getLunch()) ;
                }
            }
        };
    }

    // reset button listener
    private Button.OnClickListener clickResetButton() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("개발중이에요");
            }
        };
    }

    // pop data를 가져온다.
    private LunchData getNextPopLunch() {
        int size = mLunchDataList.size();
        if (size <= 0) {
            return new LunchData("등록한 메뉴가 없어요!");
        }

        Random random = new Random();
        int idx = random.nextInt(size); // random : 0 ~ 파라미터 - 1

        return mLunchDataList.get(idx);
    }

    public void clearDataList() {
        this.mLunchDataList.clear();
    }

}
