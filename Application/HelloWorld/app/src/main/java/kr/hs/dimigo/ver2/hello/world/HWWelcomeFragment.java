package kr.hs.dimigo.ver2.hello.world;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import cn.refactor.lib.colordialog.PromptDialog;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HWWelcomeFragment extends Fragment {

    private View mRootView;

    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.hw_welcome_layout, null);

        mButton = (Button)mRootView.findViewById(R.id.hw_welcome_layout_BUTTON);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()) {
                    Intent mIntent = new Intent(getActivity(), HWMainActivity.class);
                    mIntent.putExtra("isAccepted", true);
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(mIntent);
                    getActivity().finish();
                } else {
                    new PromptDialog(getActivity()).setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                            .setTitleText(getResources().getString(R.string.hw_hardware_not_supported_TITLE)).setContentText(getResources().getString(R.string.hw_hardware_not_supported_SUMMARY))
                            .setPositiveListener(getResources().getString(R.string.hw_ok), new PromptDialog.OnPositiveListener() {
                                @Override
                                public void onClick(PromptDialog dialog) {
                                    getActivity().finish();
                                }
                            }).show();
                    return;
                }
            }
        });

        return mRootView;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
