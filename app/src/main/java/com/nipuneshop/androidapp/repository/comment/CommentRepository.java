package com.nipuneshop.androidapp.repository.comment;

import com.nipuneshop.androidapp.AppExecutors;
import com.nipuneshop.androidapp.Config;
import com.nipuneshop.androidapp.api.ApiResponse;
import com.nipuneshop.androidapp.api.PSApiService;
import com.nipuneshop.androidapp.db.CommentDao;
import com.nipuneshop.androidapp.db.PSCoreDb;
import com.nipuneshop.androidapp.repository.common.NetworkBoundResource;
import com.nipuneshop.androidapp.repository.common.PSRepository;
import com.nipuneshop.androidapp.utils.Utils;
import com.nipuneshop.androidapp.viewobject.Comment;
import com.nipuneshop.androidapp.viewobject.common.Resource;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

@Singleton
public class CommentRepository extends PSRepository {
    //region Variables

    private final CommentDao commentDao;

    //endregion


    //region Constructor
    @Inject
    CommentRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, CommentDao commentDao1) {
        super(psApiService, appExecutors, db);
        this.commentDao = commentDao1;
    }
    //endregion

    //Get comment list
    public LiveData<Resource<List<Comment>>> getCommentList(String apiKey, String productId, String limit, String offset) {

        return new NetworkBoundResource<List<Comment>, List<Comment>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Comment> itemList) {
                Utils.psLog("SaveCallResult of getCommentList.");

                db.beginTransaction();

                try {

                    commentDao.deleteAllCommentList(productId);

                    commentDao.insertAllCommentList(itemList);

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction of getCommentList.", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Comment> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Comment>> loadFromDb() {
                Utils.psLog("Load getCommentList From Db");
                return commentDao.getAllCommentList(productId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Comment>>> createCall() {

                return psApiService.getCommentList(apiKey, productId, limit, offset);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getCommentList) : " + message);
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextPageCommentList(String productId, String limit, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<Comment>>> apiResponse = psApiService.getCommentList(Config.API_KEY, productId, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection ConstantConditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {


                    try {
                        db.beginTransaction();

                        if (response.body != null) {
                            db.commentDao().insertAllCommentList(response.body);
                        }

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
                statusLiveData.postValue(Resource.error(response.errorMessage, false));
            }

        });

        return statusLiveData;

    }

    public LiveData<Resource<Boolean>> uploadCommentHeaderToServer(String product_id,
                                                                   String userId,
                                                                   String headerComment
    ) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<List<Comment>> response;

            try {
                response = psApiService.rawCommentHeaderPost(
                        Config.API_KEY,
                        product_id,
                        userId,
                        headerComment).execute();

                ApiResponse<List<Comment>> apiResponse = new ApiResponse<>(response);

                if (response.isSuccessful()) {

                    try {
                        db.beginTransaction();

                        if (apiResponse.body != null) {
                            db.commentDao().insertAllCommentList(apiResponse.body);
                        }

                        db.setTransactionSuccessful();
                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }


                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }

        });

        return statusLiveData;
    }


    public LiveData<Resource<Boolean>> getCommentDetailReplyCount(String comment_id) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            try {
                // Call the API Service
                Response<Comment> response;

                response = psApiService
                        .getRawCommentDetailCount(Config.API_KEY, comment_id).execute();


                // Wrap with APIResponse Class
                ApiResponse<Comment> apiResponse = new ApiResponse<>(response);

                // If response is successful
                if (apiResponse.isSuccessful()) {

                    try {
                        db.beginTransaction();

                        if (apiResponse.body != null) {
                            db.commentDao().insert(apiResponse.body);
                        }

                        db.setTransactionSuccessful();
                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }

                    statusLiveData.postValue(Resource.success(apiResponse.getNextPage() != null));

                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }
        });

        return statusLiveData;

    }
}
