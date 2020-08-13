package com.nipuneshop.androidapp.repository.image;

import com.nipuneshop.androidapp.AppExecutors;
import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.api.ApiResponse;
import com.nipuneshop.androidapp.api.PSApiService;
import com.nipuneshop.androidapp.db.ImageDao;
import com.nipuneshop.androidapp.db.PSCoreDb;
import com.nipuneshop.androidapp.repository.common.NetworkBoundResource;
import com.nipuneshop.androidapp.repository.common.PSRepository;
import com.nipuneshop.androidapp.utils.Utils;
import com.nipuneshop.androidapp.viewobject.Image;
import com.nipuneshop.androidapp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

/**
 * Created by Panacea-Soft on 12/8/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Singleton
public class ImageRepository extends PSRepository {


    //region Variables

    private final ImageDao imageDao;

    //endregion


    //region Constructor

    @Inject
    ImageRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ImageDao imageDao) {
        super(psApiService, appExecutors, db);

        this.imageDao = imageDao;
    }

    //endregion


    //region News Repository Functions for ViewModel

    /**
     * Load Image List
     *
     * @param imgType Image Type
     * @param imgParentId Image Parent Id
     * @return Image List filter by news id
     */
    public LiveData<Resource<List<Image>>> getImageList(String imgType, String imgParentId) {

        String functionKey = "getImageList"+imgParentId;

        return new NetworkBoundResource<List<Image>, List<Image>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Image> item) {
                Utils.psLog("SaveCallResult of getImageList.");

                db.beginTransaction();
                try {
                    imageDao.deleteByImageIdAndType(imgParentId,imgType);

                    imageDao.insertAll(item);

                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    Utils.psErrorLog("Error", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Image> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Image>> loadFromDb() {
                Utils.psLog("Load image list from db");
                return imageDao.getByImageIdAndType(imgParentId,imgType);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Image>>> createCall() {
                Utils.psLog("Call API webservice to get image list."+psApiService.getImageList(Config.API_KEY, imgParentId,imgType));
                return psApiService.getImageList(Config.API_KEY, imgParentId,imgType);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of getting image list.");
                rateLimiter.reset(functionKey);
            }

        }.asLiveData();
    }
    //endregion


}
