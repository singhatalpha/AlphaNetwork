package Utils;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class BaseBackPressedPopListener implements OnBackPressedPopListener {
    private final FragmentActivity activity;

    public BaseBackPressedPopListener(FragmentActivity activity) {
        this.activity = activity;
    }


    @Override
    public void doPop() {
        activity.getSupportFragmentManager().popBackStack();
    }
}
