package com.nipuneshop.androapp.ui.product.productbycatId;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.R;
import com.nipuneshop.androapp.databinding.ActivityProductListByCatidBinding;
import com.nipuneshop.androapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androapp.utils.Constants;
import com.nipuneshop.androapp.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

public class ProductListByCatIdActivity extends PSAppCompactActivity {

    public final String KEY = Constants.CATEGORY_NAME;
    public String catName;

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductListByCatidBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list_by_catid);
        catName = getIntent().getStringExtra(KEY);
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

    private void initUI(ActivityProductListByCatidBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, catName);

        // setup Fragment
        ProductListByCatIdFragment productListByCatIdFragment = new ProductListByCatIdFragment();
        setupFragment(productListByCatIdFragment);
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }
    //endregion


}
