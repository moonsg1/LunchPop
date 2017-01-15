/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */

package kms.lunchpop.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import kms.lunchpop.LunchAdapter;
import kms.lunchpop.LunchData;
import kms.lunchpop.PreferenceMgr;
import kms.lunchpop.R;

public class EditFragment extends LunchFragment {
    private LunchAdapter mAdapter;
    private ListView mListView;
    private EditText mEditText;

    public EditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_edit_view, container, false);

        // SharedPreference 통해서 저장한 데이터 읽어오기
        mPrefMgr = PreferenceMgr.getInstance();
        mSize = mPrefMgr.getSize();

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        mAdapter = new LunchAdapter();

        // Xml에서 추가한 ListView 연결
        mListView = (ListView) mView.findViewById(R.id.menu_list_view);

        // ListView에 어댑터 연결
        mListView.setAdapter(mAdapter);

        // ListView에 아이템 추가
        initializeAdaptFromPreference();

        // edit_text listener
        mEditText = (EditText) mView.findViewById(R.id.edit_text);
        mEditText.setOnClickListener(clickEditText());

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
                // text 가져옴
                String input_text = mEditText.getText().toString();
                if (input_text.equals("")) {
                    showToast("공백은 추가 할 수 없어요~");
                    return;
                }

                // lunchData 저장
                LunchData lunch_data = new LunchData(input_text);
                mPrefMgr.saveLunchData(lunch_data);
                mAdapter.add(lunch_data);

                // listView 갱신
                mAdapter.notifyDataSetChanged();

                // edit text 는 초기화
                mEditText.setText("");
            }
        };
    }

    // edit button listener
    private Button.OnClickListener clickEditText() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        };
    }

    // preference에서 데이타를 꺼내와서 리스트뷰 어댑터에 적용 시킨다.
    private void initializeAdaptFromPreference() {
        int idx = 1;
        for (int i = 1; i < mPrefMgr.getMaxSize(); i++) {
            String key = String.valueOf(i);
            LunchData lunch_data = mPrefMgr.readLunchData(key);
            if (lunch_data != null) {
                lunch_data.setKey(key);
                mAdapter.add(lunch_data);
                idx++;
            }
        }
    }

    // 화면의 리스트를 정리한다.
    public void clearListView() {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }
}
