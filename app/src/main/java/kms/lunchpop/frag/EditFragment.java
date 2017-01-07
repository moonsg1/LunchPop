package kms.lunchpop.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import kms.lunchpop.LunchAdapter;
import kms.lunchpop.LunchData;
import kms.lunchpop.PreferenceHelper;
import kms.lunchpop.R;

/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */

public class EditFragment extends LunchFragment {
    public EditFragment() {
    }

    private LunchAdapter mAdapter;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_edit_view, container, false);

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        mAdapter = new LunchAdapter();

        // Xml에서 추가한 ListView 연결
        mListView = (ListView) mView.findViewById(R.id.menu_list_view);

        // ListView에 어댑터 연결
        mListView.setAdapter(mAdapter);

        // SharedPreference 통해서 저장한 데이터 읽어오기
        mPrefHelper = new PreferenceHelper(getActivity());

        // ListView에 아이템 추가
        initializeAdaptFromPreference();

        // 버튼리스너 붙임
        Button edit_btn = (Button) mView.findViewById(R.id.add_btn);
        edit_btn.setOnClickListener(clickAddButton());

        return mView;
    }

    // edit button listener
    private Button.OnClickListener clickAddButton() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_text = (EditText) mView.findViewById(R.id.edit_text);
                String input_text = edit_text.getText().toString();

                // lunchData 저장
                LunchData lunch_data = new LunchData(input_text);
                mPrefHelper.saveLunchData(lunch_data);
                mAdapter.add(lunch_data);

                // listView 갱신
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    // preference에서 데이타를 꺼내와서 리스트뷰 어댑터에 적용 시킨다.
    private void initializeAdaptFromPreference() {
        int idx = 1;
        while (true) {
            LunchData lunch_data = mPrefHelper.readLunchData(String.valueOf(idx));
            if (lunch_data == null) { break; }
            mAdapter.add(lunch_data);
            idx++;
        }
    }
}
