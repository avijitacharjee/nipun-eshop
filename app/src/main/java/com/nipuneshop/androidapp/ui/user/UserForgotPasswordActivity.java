package com.nipuneshop.androidapp.ui.user;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityUserForgotPasswordBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androidapp.utils.Constants;
import com.nipuneshop.androidapp.utils.MyContextWrapper;

public class UserForgotPasswordActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_forgot_password);

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

    private void initUI(ActivityUserForgotPasswordBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.forgot_password__title));

        // setup Fragment
        setupFragment(new UserForgotPasswordFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    //endregion


}