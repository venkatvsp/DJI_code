package dji.com.challenge.takehome.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.dji.challenge.R;
import dji.com.challenge.takehome.lib.BaseFragment;
import dji.com.challenge.takehome.utils.LogUtil;

/**
 * Main fragment
 */
public class AirCraftControlFragment extends BaseFragment {
    private static final String DJI_URL = "www.dji.com";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with_bus, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Optional
    @OnClick(R.id.dji_website_button)
    public void onButtonClick() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("http", DJI_URL, null));
            startActivity(intent);
        } catch (Exception e) {
            LogUtil.logError("button click error:", e);
        }
    }
    //endregion
}
