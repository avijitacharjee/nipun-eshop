package com.nipuneshop.androapp.ui.terms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.R;
import com.nipuneshop.androapp.binding.FragmentDataBindingComponent;
import com.nipuneshop.androapp.databinding.FragmentTermsAndConditionsBinding;
import com.nipuneshop.androapp.ui.common.PSFragment;
import com.nipuneshop.androapp.utils.AutoClearedValue;
import com.nipuneshop.androapp.utils.Constants;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewmodel.shop.ShopViewModel;
import com.nipuneshop.androapp.viewobject.Shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

public class TermsAndConditionsFragment extends PSFragment {

    //region Variables
    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ShopViewModel shopViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentTermsAndConditionsBinding> binding;
    //endregion

    //region Override Methods
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentTermsAndConditionsBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms_and_conditions, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.get().doneTextView.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).onBackPressed();
        });
    }

    @Override
    protected void initUIAndActions() {

    }

    @Override
    protected void initViewModels() {
        shopViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShopViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        getIntentData();
        shopViewModel.setShopObj(Config.API_KEY);
        shopViewModel.getShopData().observe(this, resource -> {

            if (resource != null) {

                Utils.psLog("Got Data" + resource.message + resource.toString());

                switch (resource.status) {
                    case LOADING:
                        // Loading State
                        // Data are from Local DB

                        if (resource.data != null) {

                            fadeIn(binding.get().getRoot());

                            binding.get().setShop(resource.data);
                            setAboutUsData(resource.data);
                        }
                        break;
                    case SUCCESS:
                        // Success State
                        // Data are from Server

                        if (resource.data != null) {

                            binding.get().setShop(resource.data);
                            setAboutUsData(resource.data);
                        }

                        break;
                    case ERROR:
                        // Error State

                        break;
                    default:
                        // Default

                        break;
                }

            } else {

                // Init Object or Empty Data
                Utils.psLog("Empty Data");

            }


            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (resource != null) {
                Utils.psLog("Got Data Of About Us.");


            } else {
                //noinspection ConstantConditions
                Utils.psLog("No Data of About Us.");
            }
        });


    }

    private void getIntentData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent().getExtras() != null) {
                    shopViewModel.flag = getActivity().getIntent().getExtras().getString(Constants.SHOP_TERMS_FLAG);
                }
            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }
    }

    private void setAboutUsData(Shop shop) {
        binding.get().setShop(shop);
//        shopViewModel.aboutId = shop.id;

        getIntentData();
        if (shopViewModel.flag.equals(Constants.SHOP_TERMS)) {
            binding.get().termsAndConditionTextView.setText(shop.terms);
        } else {
            binding.get().termsAndConditionTextView.setText(shop.refundPolicy);
        }

    }

}