package com.nipuneshop.androapp.db;

import com.nipuneshop.androapp.db.common.Converters;
import com.nipuneshop.androapp.viewobject.AboutUs;
import com.nipuneshop.androapp.viewobject.Basket;
import com.nipuneshop.androapp.viewobject.Blog;
import com.nipuneshop.androapp.viewobject.Category;
import com.nipuneshop.androapp.viewobject.CategoryMap;
import com.nipuneshop.androapp.viewobject.City;
import com.nipuneshop.androapp.viewobject.Comment;
import com.nipuneshop.androapp.viewobject.CommentDetail;
import com.nipuneshop.androapp.viewobject.Country;
import com.nipuneshop.androapp.viewobject.DeletedObject;
import com.nipuneshop.androapp.viewobject.DiscountProduct;
import com.nipuneshop.androapp.viewobject.FavouriteProduct;
import com.nipuneshop.androapp.viewobject.FeaturedProduct;
import com.nipuneshop.androapp.viewobject.HistoryProduct;
import com.nipuneshop.androapp.viewobject.Image;
import com.nipuneshop.androapp.viewobject.LatestProduct;
import com.nipuneshop.androapp.viewobject.LikedProduct;
import com.nipuneshop.androapp.viewobject.Noti;
import com.nipuneshop.androapp.viewobject.PSAppInfo;
import com.nipuneshop.androapp.viewobject.PSAppVersion;
import com.nipuneshop.androapp.viewobject.Product;
import com.nipuneshop.androapp.viewobject.ProductAttributeDetail;
import com.nipuneshop.androapp.viewobject.ProductAttributeHeader;
import com.nipuneshop.androapp.viewobject.ProductCollection;
import com.nipuneshop.androapp.viewobject.ProductCollectionHeader;
import com.nipuneshop.androapp.viewobject.ProductColor;
import com.nipuneshop.androapp.viewobject.ProductListByCatId;
import com.nipuneshop.androapp.viewobject.ProductMap;
import com.nipuneshop.androapp.viewobject.ProductSpecs;
import com.nipuneshop.androapp.viewobject.Rating;
import com.nipuneshop.androapp.viewobject.RelatedProduct;
import com.nipuneshop.androapp.viewobject.ShippingMethod;
import com.nipuneshop.androapp.viewobject.Shop;
import com.nipuneshop.androapp.viewobject.ShopByTagId;
import com.nipuneshop.androapp.viewobject.ShopMap;
import com.nipuneshop.androapp.viewobject.ShopTag;
import com.nipuneshop.androapp.viewobject.SubCategory;
import com.nipuneshop.androapp.viewobject.TransactionDetail;
import com.nipuneshop.androapp.viewobject.TransactionObject;
import com.nipuneshop.androapp.viewobject.User;
import com.nipuneshop.androapp.viewobject.UserLogin;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        Category.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        Product.class,
        LatestProduct.class,
        DiscountProduct.class,
        FeaturedProduct.class,
        SubCategory.class,
        ProductListByCatId.class,
        Comment.class,
        CommentDetail.class,
        ProductColor.class,
        ProductSpecs.class,
        RelatedProduct.class,
        FavouriteProduct.class,
        LikedProduct.class,
        ProductAttributeHeader.class,
        ProductAttributeDetail.class,
        Noti.class,
        TransactionObject.class,
        ProductCollectionHeader.class,
        ProductCollection.class,
        TransactionDetail.class,
        Basket.class,
        HistoryProduct.class,
        Shop.class,
        ShopTag.class,
        Blog.class,
        Rating.class,
        ShippingMethod.class,
        ShopByTagId.class,
        ProductMap.class,
        ShopMap.class,
        CategoryMap.class,
        PSAppInfo.class,
        PSAppVersion.class,
        DeletedObject.class,
        Country.class,
        City.class

}, version = 7, exportSchema = false)
//V2.4 = DBV 7
//V2.3 = DBV 7
//V2.2 = DBV 7
//V2.1 = DBV 7
//V2.0 = DBV 7
//V1.9 = DBV 7
//V1.8 = DBV 7
//V1.7 = DBV 6
//V1.6 = DBV 5
//V1.5 = DBV 4
//V1.4 = DBV 3
//V1.3 = DBV 2


@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public ProductColorDao productColorDao();

    abstract public ProductSpecsDao productSpecsDao();

    abstract public ProductAttributeHeaderDao productAttributeHeaderDao();

    abstract public ProductAttributeDetailDao productAttributeDetailDao();

    abstract public BasketDao basketDao();

    abstract public HistoryDao historyDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public CountryDao countryDao();

    abstract public CityDao cityDao();

    abstract public RatingDao ratingDao();

    abstract public CommentDao commentDao();

    abstract public CommentDetailDao commentDetailDao();

    abstract public ProductDao productDao();

    abstract public CategoryDao categoryDao();

    abstract public SubCategoryDao subCategoryDao();

    abstract public NotificationDao notificationDao();

    abstract public ProductCollectionDao productCollectionDao();

    abstract public TransactionDao transactionDao();

    abstract public TransactionOrderDao transactionOrderDao();

    abstract public ShopDao shopDao();

    abstract public BlogDao blogDao();

    abstract public ShippingMethodDao shippingMethodDao();

    abstract public ProductMapDao productMapDao();

    abstract public CategoryMapDao categoryMapDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();


//    /**
//     * Migrate from:
//     * version 1 - using Room
//     * to
//     * version 2 - using Room where the {@link } has an extra field: addedDateStr
//     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN addedDateStr INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}