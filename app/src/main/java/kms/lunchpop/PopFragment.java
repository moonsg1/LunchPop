package kms.lunchpop;

/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class PopFragment extends Fragment {
    public PopFragment() {
        // Required empty public constructor
    }

    String[] pop_str = {"떡복이", "치킨", "피자", "자장면"};
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pop_view, container, false);

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
                Random random = new Random();
                int i = random.nextInt(3);
                textView1.setText(pop_str[i]) ;
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

}
