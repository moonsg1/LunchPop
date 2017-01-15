package kms.lunchpop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

/**
 * Created by KMS_DESKTOP on 2017-01-15.
 */

public class EditPopupWindow extends PopupWindow {
    private EditPopupWindow mPopupWind;
    private EditText mEditText;
    protected PreferenceMgr mPrefMgr;
    private LunchAdapter mAdapter;
    private LunchData mLunchData;

    public EditPopupWindow(View v, LunchAdapter adapter, LunchData lunch_data) {
        super(v);

        mPopupWind = this;
        mPrefMgr = PreferenceMgr.getInstance();
        mAdapter = adapter;
        mLunchData = lunch_data;


        //클릭시 팝업 윈도우 생성
        LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //팝업으로 띄울 커스텀뷰를 설정하고
        View view = inflater.inflate(R.layout.popup_edit, null);
        this.setContentView(view);
        //팝업의 크기 설정
        this.setWindowLayoutMode(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //팝업 뷰 터치 되도록
        this.setTouchable(true);
        //팝업 뷰 포커스도 주고
        this.setFocusable(true);
        //팝업 뷰 이외에도 터치되게 (터치시 팝업 닫기 위한 코드)
        this.setOutsideTouchable(true);
        // 이건 뭐지
        //popup.setBackgroundDrawable(new BitmapDrawable());
        // 인자로 넘겨준 v 위에 위치시킴
        //this.showAsDropDown(v);

        // edit text 설정
        mEditText = (EditText) view.findViewById(R.id.edit_popup_edit_text);
        mEditText.setText(lunch_data.getLunch());

        // 버튼리스너 붙임
        Button edit_btn = (Button) view.findViewById(R.id.edit_popup_edit_btn);
        edit_btn.setOnClickListener(clickEditButton());
        Button close_btn = (Button) view.findViewById(R.id.edit_popup_close_btn);
        close_btn.setOnClickListener(clickCloseButton());
    }

/**
* showAsDropDown(anchor, xoff, yoff)
* @View anchor : anchor View를 기준으로 바로 아래 왼쪽에 표시.
* @예외 : 하지만 anchor View가 화면에 가장 하단 View라면 시스템이 자동으로 위쪽으로 표시되게 한다.
* xoff, yoff : anchor View를 기준으로 PopupWindow가 xoff는 x좌표, yoff는 y좌표 만큼 이동된 위치에 표시되게 한다.
* @int xoff : -숫자(화면 왼쪽으로 이동), +숫자(화면 오른쪽으로 이동)
* @int yoff : -숫자(화면 위쪽으로 이동), +숫자(화면 아래쪽으로 이동)
* achor View 를 덮는 것도 가능.
* 화면바깥 좌우, 위아래로 이동 가능. (짤린 상태로 표시됨)
*/

/**
* showAtLocation(parent, gravity, x, y)
* @praent : PopupWindow가 생성될 parent View 지정
* View v = (View) findViewById(R.id.btn_click)의 형태로 parent 생성
* @gravity : parent View의 Gravity 속성 지정 Popupwindow 위치에 영향을 줌.
* @x : PopupWindow를 (-x, +x) 만큼 좌,우 이동된 위치에 생성
* @y : PopupWindow를 (-y, +y) 만큼 상,하 이동된 위치에 생성
*/

    // edit button listener
    private Button.OnClickListener clickEditButton() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // text 가져옴
                String input_text = mEditText.getText().toString();
                if (input_text.equals("")) {
                    //showToast("공백은 추가 할 수 없어요~");
                    return;
                }

                LunchData lunch_data = new LunchData(input_text);
                String key = mLunchData.getKey();
                mPrefMgr.editLunchData(key, lunch_data);

                // listView 갱신
                mAdapter.notifyDataSetChanged();

                mPopupWind.dismiss();
            }
        };
    }

    // close button listener
    private Button.OnClickListener clickCloseButton() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWind.dismiss();
            }
        };
    }
}
