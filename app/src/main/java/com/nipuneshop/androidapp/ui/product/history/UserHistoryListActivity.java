package com.nipuneshop.androidapp.ui.product.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.databinding.ActivityUserHistoryListBinding;
import com.nipuneshop.androidapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androidapp.utils.Constants;
import com.nipuneshop.androidapp.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

public class UserHistoryListActivity extends PSAppCompactActivity {


    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserHistoryListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_history_list);

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

    private void initUI(ActivityUserHistoryListBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getString(R.string.history__title));

        // setup Fragment
        setupFragment(new HistoryFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }


}
