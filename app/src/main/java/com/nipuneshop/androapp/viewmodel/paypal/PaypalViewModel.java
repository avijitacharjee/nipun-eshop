package com.nipuneshop.androapp.viewmodel.paypal;

import com.nipuneshop.androapp.repository.paypal.PaypalRepository;
import com.nipuneshop.androapp.utils.AbsentLiveData;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androapp.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PaypalViewModel extends PSViewModel {

    private final LiveData<Resource<Boolean>> paypalTokenData;
    private MutableLiveData<Boolean> paypalTokenObj = new MutableLiveData<>();


    @Inject
    PaypalViewModel(PaypalRepository repository) {
        paypalTokenData = Transformations.switchMap(paypalTokenObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("paypalTokenData");
            return repository.getPaypalToekn();
        });
    }

    public void setPaypalTokenObj() {
        this.paypalTokenObj.setValue(true);
    }

    public LiveData<Resource<Boolean>> getPaypalTokenData() {
        return paypalTokenData;
    }

}
