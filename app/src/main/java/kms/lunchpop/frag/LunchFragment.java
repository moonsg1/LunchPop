/**
 * Created by KMS_DESKTOP on 2017-01-08.
 */

package kms.lunchpop.frag;

import android.app.Fragment;
import android.view.View;
import android.widget.Toast;

import kms.lunchpop.PreferenceMgr;

public abstract class LunchFragment extends Fragment {
    protected View mView;
    protected PreferenceMgr mPrefMgr;
    protected int mSize;

    protected void showToast(String message) {
        Toast.makeText (mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
