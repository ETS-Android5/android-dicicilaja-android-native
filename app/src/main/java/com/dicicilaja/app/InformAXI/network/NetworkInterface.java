package com.dicicilaja.app.InformAXI.network;


import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.model.AxiRegister;
import com.dicicilaja.app.InformAXI.model.GatheringInfo;
import com.dicicilaja.app.InformAXI.model.Incentive;
import com.dicicilaja.app.InformAXI.model.TripInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("profile/axi/filterDashboard")
    Observable<AxiHome>
    getHomeDataWithFilter(@Header("Authorization") String apiKey,
                          @Query("group") String group,
                          @Query("ot") String orderBy,
                          @Query("limit") int limit,
                          @Query("page") int page,
                          @Query("cabang_id") int cabangId);

    @GET("profile/axi/list")
    Observable<AxiHome>
    getRegListDetail(@Header("Authorization") String apiKey,
                     @Query("date") String date,
                     @Query("cabang_id") int branchId);

    /**
     * For testing purposes only
     *
     * @return
     * @GET("v3/area/branches") Observable<AxiBranch>
     * getBranch();
     */

    @GET("profile/axi/informasi-trip")
    Observable<TripInfo>
    getTripInfo(@Header("Authorization") String apiKey,
                @Query("id") int id,
                @Query("cabang_id") int branchId);

    @GET("profile/axi/gathering")
    Observable<GatheringInfo>
    getGatheringInfo(@Header("Authorization") String apiKey,
                     @Query("id") int id,
                     @Query("cabang_id") int branchId);

    /**
     * Filter in search page with date range
     *
     * @param status
     * @param date
     * @param from
     * @param to
     * @param page
     * @param all
     * @return
     */
    @GET("profile/axi/filter")
    Observable<AxiHome>
    searchWithFilter(@Header("Authorization") String apiKey,
                     @Query("status") String status,
                     @Query("date") String date,
                     @Query("from") String from,
                     @Query("to") String to,
                     @Query("page") int page,
                     @Query("all") String all,
                     @Query("cabang_id") int branchId);

    /**
     * Filter in search page
     *
     * @param status
     * @param date
     * @param page
     * @param all
     * @return
     */
    @GET("profile/axi/filter")
    Observable<AxiHome>
    searchWithFilter(@Header("Authorization") String apiKey,
                     @Query("status") String status,
                     @Query("date") String date,
                     @Query("page") int page,
                     @Query("all") String all,
                     @Query("cabang_id") int branchId);

    @GET("profile/axi/detail-intensif")
    Observable<Incentive>
    getBikeIncentive(@Header("Authorization") String apiKey,
                     @Query("id") int id,
                     @Query("type") String type);

    /**
     * Filter in home page with date range
     *
     * @param status
     * @param date
     * @param from
     * @param to
     * @param page
     * @return
     */
    @Deprecated
    @GET("profile/axi/filter")
    Observable<AxiHome>
    getHomeWithFilter(@Header("Authorization") String apiKey,
                      @Query("status") String status,
                      @Query("date") String date,
                      @Query("from") String from,
                      @Query("to") String to,
                      @Query("page") int page,
                      @Query("cabang_id") int branchId);

    /**
     * Filter in home page
     *
     * @param status
     * @param date
     * @param page
     * @return
     */
    @Deprecated
    @GET("profile/axi/filter")
    Observable<AxiHome>
    getHomeWithFilter(@Header("Authorization") String apiKey,
                      @Query("status") String status,
                      @Query("date") String date,
                      @Query("page") int page,
                      @Query("cabang_id") int branchId);

    @GET("profile/axi/home")
    Observable<AxiHome>
    getHome(@Header("Authorization") String apiKey,
            @Query("page") int page,
            @Query("cabang_id") int branchId,
            @Query("show") int show);

    @GET("profile/axi/detail")
    Observable<AxiProfile>
    getDetail(@Header("Authorization") String apiKey,
              @Query("id") int id);

    @GET("profile/axi/search")
    Observable<AxiHome>
    doSearch(@Header("Authorization") String apiKey,
             @Query("page") int page,
             @Query("all") String keyword,
             @Query("cabang_id") int branchId);

    @GET("profile/axi/registration")
    Observable<AxiRegister>
    getRegistration(@Header("Authorization") String apiKey,
                    @Query("cabang_id") int branchId);

}
