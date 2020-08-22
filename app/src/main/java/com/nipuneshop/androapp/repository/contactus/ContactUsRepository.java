package com.nipuneshop.androapp.repository.contactus;

import com.nipuneshop.androapp.AppExecutors;
import com.nipuneshop.androapp.api.ApiResponse;
import com.nipuneshop.androapp.api.PSApiService;
import com.nipuneshop.androapp.db.PSCoreDb;
import com.nipuneshop.androapp.repository.common.PSRepository;
import com.nipuneshop.androapp.utils.Utils;
import com.nipuneshop.androapp.viewobject.ApiStatus;
import com.nipuneshop.androapp.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

/**
 * Created by Panacea-Soft on 7/2/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */

public class ContactUsRepository extends PSRepository {

    @Inject
    ContactUsRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);

    }

    /**
     * Post Contact Us
     * @param apiKey APIKey to access Web Service
     * @param contactName Name
     * @param contactEmail Email
     * @param contactDesc Desc
     * @return Status of Post
     */
    public LiveData<Resource<Boolean>> postContactUs(String apiKey, String contactName, String contactEmail, String contactDesc, String contactPhone) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();
        appExecutors.networkIO().execute(() -> {
            try {

                // Call the API Service
                Response<ApiStatus> response = psApiService.rawPostContact(apiKey, contactName, contactEmail, contactDesc, contactPhone).execute();

                // Wrap with APIResponse Class
                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                Utils.psLog("apiResponse " + apiResponse);
                // If response is successful
                if (apiResponse.isSuccessful()) {

                    statusLiveData.postValue(Resource.success(true));
                } else {

                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, true));
                }
            } catch (Exception e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), true));
            }

        });

        return statusLiveData;

    }

}
