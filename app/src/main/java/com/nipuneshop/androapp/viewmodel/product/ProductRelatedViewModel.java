package com.nipuneshop.androapp.viewmodel.product;

import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.repository.product.ProductRepository;
import com.nipuneshop.androapp.utils.AbsentLiveData;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewmodel.common.PSViewModel;
import com.nipuneshop.androapp.viewobject.Product;
import com.nipuneshop.androapp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProductRelatedViewModel extends PSViewModel {
    //for product detail list

    private final LiveData<Resource<List<Product>>> productRelatedData;
    private MutableLiveData<ProductRelatedViewModel.TmpDataHolder> productRelatedListObj = new MutableLiveData<>();

    //region Constructor

    @Inject
    ProductRelatedViewModel(ProductRepository productRepository) {
        //  product detail List
        productRelatedData = Transformations.switchMap(productRelatedListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("ProductRelatedViewModel.");
            return productRepository.getRelatedList(Config.API_KEY, obj.productId, obj.catId);
        });

    }

    //endregion
    //region Getter And Setter for product detail List

    public void setProductRelatedListObj(String productId, String catId) {
        if (!isLoading) {
            ProductRelatedViewModel.TmpDataHolder tmpDataHolder = new ProductRelatedViewModel.TmpDataHolder();
            tmpDataHolder.productId = productId;
            tmpDataHolder.catId = catId;
            productRelatedListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Product>>> getProductRelatedData() {
        return productRelatedData;
    }

    //endregion

    //region Holder
    class TmpDataHolder {
        public String offset = "";
        public String productId = "";
        public String catId = "";
        public String shopId = "";
        public Boolean isConnected = false;
    }
    //endregion
}