package com.nipuneshop.androidapp.ui.forceupdate;

import android.os.Bundle;

import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityForceUpdateBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;

import androidx.databinding.DataBindingUtil;

public class ForceUpdateActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityForceUpdateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_force_update);

        // Init all UI
        initUI(binding);

    }

    private void initUI(ActivityForceUpdateBinding binding) {

        // Toolbar
//        initToolbar(binding.toolbar, getResources().getString(R.string.comment__title));

        setupFragment(new ForceUpdateFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    @Override
    public void onBackPressed() {

    }
}
