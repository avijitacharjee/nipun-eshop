package com.nipuneshop.androapp.viewmodel.apploading;

import com.nipuneshop.androapp.repository.apploading.AppLoadingRepository;
import com.nipuneshop.androapp.utils.AbsentLiveData;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androapp.viewobject.PSAppInfo;
import com.nipuneshop.androapp.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class AppLoadingViewModel extends PSViewModel {

    private final LiveData<Resource<PSAppInfo>> deleteHistoryData;
    private MutableLiveData<TmpDataHolder> deleteHistoryObj = new MutableLiveData<>();
    public PSAppInfo psAppInfo;

    @Inject
    AppLoadingViewModel(AppLoadingRepository repository) {
        deleteHistoryData = Transformations.switchMap(deleteHistoryObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("AppLoadingViewModel");
            return repository.deleteTheSpecificObjects(obj.startDate, obj.endDate);
        });
    }

    public void setDeleteHistoryObj(String startDate, String endDate) {

        TmpDataHolder tmpDataHolder = new TmpDataHolder(startDate, endDate);

        this.deleteHistoryObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<PSAppInfo>> getDeleteHistoryData() {
        return deleteHistoryData;
    }

    class TmpDataHolder {
        String startDate, endDate;

        public TmpDataHolder(String startDate, String endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

}
