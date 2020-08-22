package com.nipuneshop.androapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nipuneshop.androapp.viewmodel.aboutus.AboutUsViewModel;
import com.nipuneshop.androapp.viewmodel.blog.BlogViewModel;
import com.nipuneshop.androapp.viewmodel.category.CategoryViewModel;
import com.nipuneshop.androapp.viewmodel.city.CityViewModel;
import com.nipuneshop.androapp.viewmodel.clearalldata.ClearAllDataViewModel;
import com.nipuneshop.androapp.viewmodel.collection.ProductCollectionProductViewModel;
import com.nipuneshop.androapp.viewmodel.collection.ProductCollectionViewModel;
import com.nipuneshop.androapp.viewmodel.comment.CommentDetailListViewModel;
import com.nipuneshop.androapp.viewmodel.comment.CommentListViewModel;
import com.nipuneshop.androapp.viewmodel.common.NotificationViewModel;
import com.nipuneshop.androapp.viewmodel.common.PSNewsViewModelFactory;
import com.nipuneshop.androapp.viewmodel.contactus.ContactUsViewModel;
import com.nipuneshop.androapp.viewmodel.country.CountryViewModel;
import com.nipuneshop.androapp.viewmodel.coupondiscount.CouponDiscountViewModel;
import com.nipuneshop.androapp.viewmodel.homelist.HomeFeaturedProductViewModel;
import com.nipuneshop.androapp.viewmodel.homelist.HomeLatestProductViewModel;
import com.nipuneshop.androapp.viewmodel.homelist.HomeSearchProductViewModel;
import com.nipuneshop.androapp.viewmodel.homelist.HomeTrendingCategoryListViewModel;
import com.nipuneshop.androapp.viewmodel.homelist.HomeTrendingProductViewModel;
import com.nipuneshop.androapp.viewmodel.image.ImageViewModel;
import com.nipuneshop.androapp.viewmodel.notification.NotificationsViewModel;
import com.nipuneshop.androapp.viewmodel.paypal.PaypalViewModel;
import com.nipuneshop.androapp.viewmodel.product.BasketViewModel;
import com.nipuneshop.androapp.viewmodel.product.HistoryProductViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductAttributeDetailViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductAttributeHeaderViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductColorViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductDetailViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductFavouriteViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductListByCatIdViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductRelatedViewModel;
import com.nipuneshop.androapp.viewmodel.product.ProductSpecsViewModel;
import com.nipuneshop.androapp.viewmodel.product.TouchCountViewModel;
import com.nipuneshop.androapp.viewmodel.apploading.AppLoadingViewModel;
import com.nipuneshop.androapp.viewmodel.rating.RatingViewModel;
import com.nipuneshop.androapp.viewmodel.shippingmethod.ShippingMethodViewModel;
import com.nipuneshop.androapp.viewmodel.shop.ShopViewModel;
import com.nipuneshop.androapp.viewmodel.subcategory.SubCategoryViewModel;
import com.nipuneshop.androapp.viewmodel.transaction.TransactionListViewModel;
import com.nipuneshop.androapp.viewmodel.transaction.TransactionOrderViewModel;
import com.nipuneshop.androapp.viewmodel.user.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Panacea-Soft on 11/16/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PSNewsViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel.class)
    abstract ViewModel bindAboutUsViewModel(AboutUsViewModel aboutUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel.class)
    abstract ViewModel bindImageViewModel(ImageViewModel imageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RatingViewModel.class)
    abstract ViewModel bindRatingViewModel(RatingViewModel ratingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel notificationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel.class)
    abstract ViewModel bindCountryViewModel(CountryViewModel countryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel.class)
    abstract ViewModel bindCityViewModel(CityViewModel cityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel.class)
    abstract ViewModel bindContactUsViewModel(ContactUsViewModel contactUsViewModel);

  /*  @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel.class)
    abstract ViewModel bindProductViewModel(ProductViewModel productViewModel);*/

    @Binds
    @IntoMap
    @ViewModelKey(CommentListViewModel.class)
    abstract ViewModel bindCommentViewModel(CommentListViewModel commentListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommentDetailListViewModel.class)
    abstract ViewModel bindCommentDetailViewModel(CommentDetailListViewModel commentDetailListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    abstract ViewModel bindProductDetailViewModel(ProductDetailViewModel productDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TouchCountViewModel.class)
    abstract ViewModel bindTouchCountViewModel(TouchCountViewModel touchCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductColorViewModel.class)
    abstract ViewModel bindProductColorViewModel(ProductColorViewModel productColorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductSpecsViewModel.class)
    abstract ViewModel bindProductSpecsViewModel(ProductSpecsViewModel productSpecsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel.class)
    abstract ViewModel bindBasketViewModel(BasketViewModel basketViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryProductViewModel.class)
    abstract ViewModel bindHistoryProductViewModel(HistoryProductViewModel historyProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductAttributeHeaderViewModel.class)
    abstract ViewModel bindProductAttributeHeaderViewModel(ProductAttributeHeaderViewModel productAttributeHeaderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductAttributeDetailViewModel.class)
    abstract ViewModel bindProductAttributeDetailViewModel(ProductAttributeDetailViewModel productAttributeDetailViewModel);
/*

    @Binds
    @IntoMap
    @ViewModelKey(DiscountProductViewModel.class)
    abstract ViewModel bindDiscountProductViewModel(DiscountProductViewModel discountProductViewModel);
*/

    @Binds
    @IntoMap
    @ViewModelKey(ProductRelatedViewModel.class)
    abstract ViewModel bindRelatedProductViewModel(ProductRelatedViewModel productRelatedViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductFavouriteViewModel.class)
    abstract ViewModel bindProductFavouriteViewModel(ProductFavouriteViewModel productFavouriteViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(ProductLikedViewModel.class)
//    abstract ViewModel bindProductLikedViewModel(ProductLikedViewModel productLikedViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SubCategoryViewModel.class)
    abstract ViewModel bindSubCategoryViewModel(SubCategoryViewModel subCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductListByCatIdViewModel.class)
    abstract ViewModel bindProductListByCatIdViewModel(ProductListByCatIdViewModel productListByCatIdViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(TrendingProductsViewModel.class)
//    abstract ViewModel bindTrendingProductsViewModel(TrendingProductsViewModel trendingProductsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeLatestProductViewModel.class)
    abstract ViewModel bindHomeLatestProductViewModel(HomeLatestProductViewModel homeLatestProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeSearchProductViewModel.class)
    abstract ViewModel bindHomeFeaturedProductViewModel(HomeSearchProductViewModel homeSearchProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeTrendingProductViewModel.class)
    abstract ViewModel bindHomeTrendingProductViewModel(HomeTrendingProductViewModel homeTrendingProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeFeaturedProductViewModel.class)
    abstract ViewModel bindHomeCategory1ProductViewModel(HomeFeaturedProductViewModel homeFeaturedProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductCollectionViewModel.class)
    abstract ViewModel bindProductCollectionViewModel(ProductCollectionViewModel productCollectionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel.class)
    abstract ViewModel bindNotificationListViewModel(NotificationsViewModel notificationListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TransactionListViewModel.class)
    abstract ViewModel bindTransactionListViewModel(TransactionListViewModel transactionListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TransactionOrderViewModel.class)
    abstract ViewModel bindTransactionOrderViewModel(TransactionOrderViewModel transactionOrderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeTrendingCategoryListViewModel.class)
    abstract ViewModel bindHomeTrendingCategoryListViewModel(HomeTrendingCategoryListViewModel transactionListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductCollectionProductViewModel.class)
    abstract ViewModel bindProductCollectionProductViewModel(ProductCollectionProductViewModel productCollectionProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShopViewModel.class)
    abstract ViewModel bindShopViewModel(ShopViewModel shopViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel.class)
    abstract ViewModel bindNewsFeedViewModel(BlogViewModel blogViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShippingMethodViewModel.class)
    abstract ViewModel bindShippingMethodViewModel(ShippingMethodViewModel shippingMethodViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PaypalViewModel.class)
    abstract ViewModel bindPaypalViewModel(PaypalViewModel paypalViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CouponDiscountViewModel.class)
    abstract ViewModel bindCouponDiscountViewModel(CouponDiscountViewModel couponDiscountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AppLoadingViewModel.class)
    abstract ViewModel bindPSAppInfoViewModel(AppLoadingViewModel psAppInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ClearAllDataViewModel.class)
    abstract ViewModel bindClearAllDataViewModel(ClearAllDataViewModel clearAllDataViewModel);
}


