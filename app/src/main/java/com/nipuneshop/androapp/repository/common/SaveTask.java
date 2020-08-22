package com.nipuneshop.androapp.repository.common;

import com.nipuneshop.androapp.api.PSApiService;
import com.nipuneshop.androapp.db.PSCoreDb;
import com.nipuneshop.androapp.viewobject.Category;
import com.nipuneshop.androapp.viewobject.common.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * General Save Task Sample
 * Created by Panacea-Soft on 12/6/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class SaveTask implements Runnable {


    //region Variables

    private final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

    public final PSApiService service;
    private final PSCoreDb db;
    private final Object obj;

    //endregion


    //region Constructor

    /**
     * Constructor of SaveTask.
     * @param service Panacea-Soft API Service Instance
     * @param db Panacea-Soft DB Instance
     * @param obj Object to Save
     *
     */
    SaveTask(PSApiService service, PSCoreDb db, Object obj) {
        this.service = service;
        this.db = db;
        this.obj = obj;
    }

    //endregion


    //region Override Methods

    @Override
    public void run() {
        try {
            try{
                db.beginTransaction();

                if(obj instanceof Category) {
                    //db.categoryDao().insert((Category) obj);
                    db.setTransactionSuccessful();
                }

            }finally {
                db.endTransaction();
            }
            statusLiveData.postValue(Resource.success(true));
        } catch (Exception e) {
            statusLiveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    //endregion


    //region public SyncCategory Methods

    /**
     * This function will return Status of Process
     * @return statusLiveData
     */
    public LiveData<Resource<Boolean>> getStatusLiveData() { return statusLiveData; }

    //endregion

}