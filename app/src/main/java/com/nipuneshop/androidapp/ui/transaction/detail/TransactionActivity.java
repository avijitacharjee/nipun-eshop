package com.nipuneshop.androidapp.ui.transaction.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityTransactionBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androidapp.utils.Constants;
import com.nipuneshop.androidapp.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

public class TransactionActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTransactionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }
    //endregion


    //region Private Methods


    private void initUI(ActivityTransactionBinding binding) {
        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.transaction_detail__title));

        // setup Fragment
        setupFragment(new TransactionFragment());

    }
    //endregion
}