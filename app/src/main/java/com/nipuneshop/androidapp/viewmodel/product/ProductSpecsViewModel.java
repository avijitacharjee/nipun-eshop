package com.nipuneshop.androidapp.viewmodel.product;

import com.nipuneshop.androidapp.repository.product.ProductRepository;
import com.nipuneshop.androidapp.utils.AbsentLiveData;
import com.nipuneshop.androidapp.utils.Utils;
import com.nipuneshop.androidapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androidapp.viewobject.ProductSpecs;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProductSpecsViewModel extends PSViewModel {
    //for product specs list

    public boolean isSpecsData = false;
    private final LiveData<List<ProductSpecs>> productSpecsListData;
    private MutableLiveData<ProductSpecsViewModel.TmpDataHolder> productSpecsObj = new MutableLiveData<>();

    //region Constructor

    @Inject
    public ProductSpecsViewModel(ProductRepository productRepository) {
        //  product specs List
        productSpecsListData = Transformations.switchMap(productSpecsObj, (ProductSpecsViewModel.TmpDataHolder obj) -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product color List.");
            return productRepository.getProductSpecs(obj.productId);
        });

    }

    //endregion
    //region setProductSpecsListObj

    public void setProductSpecsListObj(String productId) {

        ProductSpecsViewModel.TmpDataHolder tmpDataHolder = new ProductSpecsViewModel.TmpDataHolder();
        tmpDataHolder.productId = productId;
        productSpecsObj.setValue(tmpDataHolder);

    }

    public LiveData<List<ProductSpecs>> getProductSpecsListData() {
        return productSpecsListData;
    }

    //endregion

    //region Holder

    class TmpDataHolder {
        public String offset = "";
        public String productId = "";
        public Boolean isConnected = false;
    }
    //endregion
}
