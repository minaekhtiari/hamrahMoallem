package com.hillavas.filemanaging.helpers;



import com.hillavas.filemanaging.classes.FileForUpload;
import com.hillavas.filemanaging.classes.ResultJsonBase;
import com.hillavas.filemanaging.classes.ResultJsonGetFileByteArray;
import com.hillavas.filemanaging.classes.ResultJsonGetFileURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by A.Mohammadi on 8/9/2017.
 */

public interface IRetrofitFileManaging {

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("file/UploadString")
    Call<ResultJsonBase> uploadFile(@Body FileForUpload fileForUpload);

    @GET("file/getFileUrl")
    Call<ResultJsonGetFileURL> getFileUrl(@Query("fileId") String fileId,
                                          @Query("fileType") String fileType);

    //99873ef7-dc59-4e24-81a5-35e4f9a1a7b0

    @GET("file/downloadByteArray")
    Call<ResultJsonGetFileByteArray> getFileByteArray(@Query("fileId") String fileId,
                                                       @Query("fileType") String fileType);



}
