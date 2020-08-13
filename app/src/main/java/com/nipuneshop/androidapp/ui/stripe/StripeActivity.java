package com.nipuneshop.androidapp.ui.stripe;

import android.os.Bundle;

import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityStripeBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;

import androidx.databinding.DataBindingUtil;

public class StripeActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStripeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_stripe);

        initUI(binding);

    }

    private void initUI(ActivityStripeBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.checkout__stripe));

        // setup Fragment
        setupFragment(new StripeFragment());

    }
}

