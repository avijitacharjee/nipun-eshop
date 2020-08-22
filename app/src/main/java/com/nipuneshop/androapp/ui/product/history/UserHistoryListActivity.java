package com.nipuneshop.androapp.ui.product.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.R;
import com.nipuneshop.androapp.databinding.ActivityUserHistoryListBinding;
import com.nipuneshop.androapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androapp.utils.Constants;
import com.nipuneshop.androapp.utils.MyContextWrapper;

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
