package classes.tools.helpers;

import classes.models.ContentGiver;
import classes.models.ContentsGiver;
import classes.models.ProfileNew;
import classes.models.RequestCodeGiverModel;
import classes.models.ResultJson;
import classes.models.ResultJsonCategory;
import classes.models.ResultJsonCategoryWithParentChild;
import classes.models.ResultJsonForBuy;
import classes.models.ResultJsonForProfileInfo;
import classes.models.ResultJsonForViewCount;
import classes.models.ResultJsonLevels;
import classes.models.ResultJsonObject;
import classes.models.ResultJsonPackage;
import classes.models.ResultJsonPurchase;
import classes.models.SignUpMemberModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by A.Mohammadi on 7/23/2017.
 */

public interface IRetrofit {


    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("OTP/SubScribeRequest")
    Call<ResultJson> register(@Body SignUpMemberModel signUpMemberModel);

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/OTP/ConfirmOtp")
    Call<ResultJson> requestCodeGiver(@Body RequestCodeGiverModel requestCodeGiverModel);

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("OAuth/LoginMember")
    Call<ResultJson> getGUID(@Body RequestCodeGiverModel requestCodeGiverModel);

    @GET("level/getlevels")
    Call<ResultJsonLevels> getLevels(@Query("token") String token);

    @GET("Category/GetCategory")
    Call<ResultJsonCategory> getCategories(@Query("token") String token,
                                           @Query("levelId") int levelId);
    @GET("Category/GetCategoryTree")
    Call<ResultJsonCategoryWithParentChild> getAllCategoryOfALevel(@Query("token") String token,
                                                                   @Query("levelId") int levelId);
    @GET("category/GetCategory")
    Call<ResultJsonCategory> getCategories(@Query("token") String token,
                                           @Query("levelId") int levelId,
                                           @Query("categoryid") int categoryid);

    @GET("Purchase/AddMemberCredit")
    Call<ResultJsonForBuy> buy(@Query("token") String token,
                               @Query("ContentId") int contentId,
                               @Query("Price") int price);

    @GET("Member/GetProfileInfo")
    Call<ResultJsonForProfileInfo> getProfileInfo(@Query("token") String token);

    @GET("Purchase/GetAllPurchases")
    Call<ResultJsonPurchase> getAllPurchases(@Query("token") String token,
                                             @Query("pagenumber") int pagenumber,
                                             @Query("rowcount") int rowcount);

    @GET("Package/GetPackages")
    Call<ResultJsonPackage> getPackages(@Query("token") String token);


    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/Member/UpdateProfile")
    Call<ResultJson> updateProfile(@Body ProfileNew pn);



    @GET("Content/GetAllPredicate")
    Call<ContentsGiver> getContents(@Query("token") String token,
                                    @Query("categoryid") int categoryid,
                                    @Query("pagenumber") int pagenumber,
                                    @Query("rowcount") int rowcount);

    @GET("content/GetContentById")
    Call<ContentGiver> getContentById(@Query("token") String token,
                                      @Query("contentid") int contentId);


    @GET("content/GetContentViewCount")
    Call<ResultJsonForViewCount> getContentViewCount(@Query("token") String token,
                                                     @Query("contentId") int contentId);

    @GET("content/GetLikeContent")
    Call<ResultJson> getContentLikeCount(@Query("token") String token,
                                         @Query("recordId") int recordId);

    @GET("content/GetIsLikedMe")
    Call<ResultJson> getisLikedOrNot(    @Query("token") String token,
                                         @Query("recordId") int recordId);

    @GET("Package/GetPackages")
    Call<ResultJsonForBuy> buyAPackage(@Query("token") String token);


}
