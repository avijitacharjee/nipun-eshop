package com.nipuneshop.androidapp.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityNotificationSettingBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androidapp.ui.notification.setting.NotificationSettingFragment;
import com.nipuneshop.androidapp.utils.Constants;
import com.nipuneshop.androidapp.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

public class NotificationSettingActivity extends PSAppCompactActivity {

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNotificationSettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_setting);

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

    private void initUI(ActivityNotificationSettingBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.notification_setting__title));

        // setup Fragment
        setupFragment(new NotificationSettingFragment());

    }

    //endregion

}
