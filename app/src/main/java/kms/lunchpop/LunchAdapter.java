package kms.lunchpop;

import android.view.Gravity;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kms.lunchpop.popup.EditPopupWindow;

/**
 * Created by KMS_DESKTOP on 2017-01-01.
 */
public class LunchAdapter extends BaseAdapter {
    // 문자열을 보관 할 ArrayList
    private ArrayList<LunchData> m_List;
    private PreferenceMgr mPrefHelper;
    private EditPopupWindow mPopupWindow;
    private LunchAdapter mAdapter;

    // 생성자
    public LunchAdapter() {
        mAdapter = this;
        this.m_List = new ArrayList<>();
        this.mPrefHelper = PreferenceMgr.getInstance();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        //if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_lunch, parent, false);

            // TextView에 현재 position의 문자열 추가
            TextView text = (TextView) convertView.findViewById(R.id.menu_text);
            String lunch = m_List.get(position).getLunch();
            text.setText(lunch);

            // 버튼리스너 붙임
            {
                Button edit_btn = (Button) convertView.findViewById(R.id.menu_item_edit_btn);
                edit_btn.setTag(position);
                edit_btn.setOnClickListener(mEditBtnListener);

                Button delete_btn = (Button) convertView.findViewById(R.id.menu_item_delete_btn);
                delete_btn.setTag(position);
                delete_btn.setOnClickListener(mClickBtnListener);
            }
        //}

        return convertView;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(LunchData lunch_data) {
        m_List.add(lunch_data);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
    public void clear() {
        m_List.clear();
    }

    // edit button listener
    private Button.OnClickListener mEditBtnListener = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            // preference에서 삭제
            LunchData lunch_data = (LunchData)getItem(position);

            // 수정 팝업 생성
            mPopupWindow = new EditPopupWindow(v, mAdapter, lunch_data);
            // 애니메이션 설정(-1:설정, 0:설정안함)
            mPopupWindow.setAnimationStyle(-1);
            // 중앙에 위치 시킴
            mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
    };

    // delete button listener
    private Button.OnClickListener mClickBtnListener = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            // preference에서 삭제
            LunchData lunch_data = (LunchData)getItem(position);
            String key = lunch_data.getKey();
            mPrefHelper.removeLunchData(key);

            // adapter list에서 삭제
            remove(position);

            // adpater 갱신
            notifyDataSetChanged();
        }
    };

}
