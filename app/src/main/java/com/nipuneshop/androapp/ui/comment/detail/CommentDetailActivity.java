package com.nipuneshop.androapp.ui.comment.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.R;
import com.nipuneshop.androapp.databinding.ActivityCommentDetailBinding;
import com.nipuneshop.androapp.ui.common.PSAppCompactActivity;
import com.nipuneshop.androapp.utils.Constants;
import com.nipuneshop.androapp.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

/**
 * Created by Panacea-Soft
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class CommentDetailActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCommentDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_detail);

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


    //region Private Methods

    private void initUI(ActivityCommentDetailBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.comment__detail_title));

        // setup Fragment
        CommentDetailFragment commentDetailFragment = new CommentDetailFragment();
        setupFragment(commentDetailFragment);
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    //endregion

}
