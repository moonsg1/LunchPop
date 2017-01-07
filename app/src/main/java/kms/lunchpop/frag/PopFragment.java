package kms.lunchpop.frag;

/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kms.lunchpop.LunchData;
import kms.lunchpop.PreferenceHelper;
import kms.lunchpop.R;

public class PopFragment extends LunchFragment {
    public PopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pop_view, container, false);

        // preference 등록
        this.mPrefHelper = new PreferenceHelper(getActivity());

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

            }
        };
    }

    // pop data를 가져온다.
    private LunchData getNextPopLunch() {
        int size = mPrefHelper.getSize() - 1;
        if (size <= 0) {
            return null;
        }
        // random 0 ~ 파라미터 - 1 return
        Random random = new Random();
        int idx = random.nextInt(size) + 1;

        String num_str = String.valueOf(idx);
        LunchData lunch_data = mPrefHelper.readLunchData(num_str);

        return lunch_data;
    }

}
