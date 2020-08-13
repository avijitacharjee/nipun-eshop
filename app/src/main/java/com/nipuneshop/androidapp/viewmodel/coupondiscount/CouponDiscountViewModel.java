package com.nipuneshop.androidapp.viewmodel.coupondiscount;

import com.nipuneshop.androidapp.repository.coupondiscount.CouponDiscountRepository;
import com.nipuneshop.androidapp.utils.AbsentLiveData;
import com.nipuneshop.androidapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androidapp.viewobject.CouponDiscount;
import com.nipuneshop.androidapp.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class CouponDiscountViewModel extends PSViewModel {

    private final LiveData<Resource<CouponDiscount>> couponDiscountData;
    private final MutableLiveData<CouponDiscountViewModel.TmpDataHolder> couponDiscountObj = new MutableLiveData<>();

    @Inject
    CouponDiscountViewModel(CouponDiscountRepository repository) {

        couponDiscountData = Transformations.switchMap(couponDiscountObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCouponDiscount(obj.code);
        });
    }

    public void setCouponDiscountObj(String code) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.code = code;

        couponDiscountObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<CouponDiscount>> getCouponDiscountData() {
        return couponDiscountData;
    }


    class TmpDataHolder {

        public String code = "";
        public String shopId = "";
    }

}
