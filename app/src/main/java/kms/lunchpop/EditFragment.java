package kms.lunchpop;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by KMS_DESKTOP on 2016-12-25.
 */

public class EditFragment extends Fragment {
    public EditFragment() {
    }

    String[] pop_str = {"떡복이", "치킨", "피자", "자장면"};
    private LunchAdapter mAdapter;
    private ListView mListView;
    private View mView;

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

        // ListView에 아이템 추가
        mAdapter.add("하스스톤");
        mAdapter.add("몬스터 헌터");
        mAdapter.add("디아블로");
        mAdapter.add("와우");
        mAdapter.add("리니지");
        mAdapter.add("안드로이드");
        mAdapter.add("아이폰");

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

            }
        };
    }
}
