package com.nipuneshop.androapp.repository.blog;

import com.nipuneshop.androapp.AppExecutors;
import com.nipuneshop.androapp.Config;
import com.nipuneshop.androapp.api.ApiResponse;
import com.nipuneshop.androapp.api.PSApiService;
import com.nipuneshop.androapp.db.BlogDao;
import com.nipuneshop.androapp.db.PSCoreDb;
import com.nipuneshop.androapp.repository.common.NetworkBoundResource;
import com.nipuneshop.androapp.repository.common.PSRepository;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewobject.Blog;
import com.nipuneshop.androapp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

@Singleton
public class BlogRepository extends PSRepository {

    private final BlogDao blogDao;

    @Inject
    BlogRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, BlogDao blogDao) {
        super(psApiService, appExecutors, db);
        this.blogDao = blogDao;
    }

    public LiveData<Resource<List<Blog>>> getNewsFeedList(String limit, String offset) {
        return new NetworkBoundResource<List<Blog>, List<Blog>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Blog> itemList) {
                Utils.psLog("SaveCallResult of getNewsFeedList.");

                db.beginTransaction();

                try {
                    blogDao.deleteAll();
                    blogDao.insertAll(itemList);

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction of getNewsFeedList.", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Blog> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Blog>> loadFromDb() {
                Utils.psLog("Load getNewsFeedList From Db");

                return blogDao.getAllNewsFeed();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Blog>>> createCall() {
                Utils.psLog("Call API Service to getNewsFeedList.");

                return psApiService.getAllNewsFeed(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getNewsFeedList) : " + message);
            }
        }.asLiveData();
    }

//    public LiveData<Resource<List<Blog>>> getNewsFeedListByShopId( String limit, String offset) {
//        return new NetworkBoundResource<List<Blog>, List<Blog>>(appExecutors) {
//
//            @Override
//            protected void saveCallResult(@NonNull List<Blog> itemList) {
//                Utils.psLog("SaveCallResult of getNewsFeedListByShopId.");
//
//                db.beginTransaction();
//
//                try {
//
//                    blogDao.insertAll(itemList);
//
//                    db.setTransactionSuccessful();
//
//                } catch (Exception e) {
//                    Utils.psErrorLog("Error in doing transaction of getNewsFeedListByShopId.", e);
//                } finally {
//                    db.endTransaction();
//                }
//            }
//
//            @Override
//            protected boolean shouldFetch(@Nullable List<Blog> data) {
//
//                // Recent news always load from server
//                return connectivity.isConnected();
//
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<List<Blog>> loadFromDb() {
//                Utils.psLog("Load getNewsFeedListByShopId From Db");
//
//                return blogDao.getAllNewsFeed();
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<ApiResponse<List<Blog>>> createCall() {
//
//                Utils.psLog("Call API Service to getNewsFeedListByShopId.");
//                return psApiService.getAllNewsFeed(Config.API_KEY,  limit, offset);
//
//            }
//
//            @Override
//            protected void onFetchFailed(String message) {
//                Utils.psLog("Fetch Failed (getNewsFeedListByShopId) : " + message);
//            }
//        }.asLiveData();
//    }


    public LiveData<Resource<Boolean>> getNextPageNewsFeedList(String apiKey, String limit, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        LiveData<ApiResponse<List<Blog>>> apiResponse = psApiService.getAllNewsFeed(apiKey, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection ConstantConditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {

                    try {

                        db.beginTransaction();

                        db.blogDao().insertAll(response.body);

                        db.setTransactionSuccessful();

                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }

                    statusLiveData.postValue(Resource.success(true));
                });

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }
        });

        return statusLiveData;

    }

    public LiveData<Resource<Blog>> getBlogById(String id) {
        return new NetworkBoundResource<Blog, Blog>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Blog blog) {
                Utils.psLog("SaveCallResult of getBlogById.");

                db.beginTransaction();

                try {

                    blogDao.insert(blog);

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction of getBlogById.", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Blog blog) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<Blog> loadFromDb() {

                Utils.psLog("Load getBlogById From Db");
                return blogDao.getBlogById(id);

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Blog>> createCall() {

                Utils.psLog("Call API Service to getBlogById.");
                return psApiService.getNewsById(Config.API_KEY, id);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getBlogById) : " + message);
            }
        }.asLiveData();
    }

}