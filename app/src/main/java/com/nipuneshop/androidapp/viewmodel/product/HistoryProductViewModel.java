package com.nipuneshop.androidapp.viewmodel.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.nipuneshop.androidapp.repository.product.ProductRepository;
import com.nipuneshop.androidapp.utils.AbsentLiveData;
import com.nipuneshop.androidapp.utils.Utils;
import com.nipuneshop.androidapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androidapp.viewobject.HistoryProduct;

import java.util.List;

import javax.inject.Inject;

public class HistoryProductViewModel extends PSViewModel {
    private final LiveData<List<HistoryProduct>> historyListData;
    private MutableLiveData<HistoryProductViewModel.TmpDataHolder> historyListObj = new MutableLiveData<>();


    @Inject
    HistoryProductViewModel(ProductRepository productRepository) {
        //  basket List

        historyListData = Transformations.switchMap(historyListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("get basket");
            return productRepository.getAllHistoryList(obj.offset);
        });


    }
    //endregion
    //region Getter And Setter for basket List

    public void setHistoryProductListObj(String offset) {
        HistoryProductViewModel.TmpDataHolder tmpDataHolder = new HistoryProductViewModel.TmpDataHolder();
        tmpDataHolder.offset = offset;
        historyListObj.setValue(tmpDataHolder);
    }

    public LiveData<List<HistoryProduct>> getAllHistoryProductList() {
        return historyListData;
    }

    //endregion


    //region Holder
    class TmpDataHolder {
        public int id = 0;
        public String productId = "";
        public String loginUserId = "";
        public String offset = "";
        public Boolean isConnected = false;
    }
//endregion
}