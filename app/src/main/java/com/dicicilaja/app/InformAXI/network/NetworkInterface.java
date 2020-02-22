package com.dicicilaja.app.InformAXI.network;


import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.model.AxiRegister;
import com.dicicilaja.app.InformAXI.model.GatheringInfo;
import com.dicicilaja.app.InformAXI.model.Incentive;
import com.dicicilaja.app.InformAXI.model.TripInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Husni with ❤
 */

public interface NetworkInterface {

    @GET("v2/profile/axi/filterDashboard")
    Observable<AxiHome>
    getHomeDataWithFilter(@Query("group") String group,
                          @Query("ot") String orderBy,
                          @Query("limit") int limit,
                          @Query("page") int page,
                          @Query("cabang_id") int cabangId);

    @GET("v2/profile/axi/list")
    Observable<AxiHome>
    getRegListDetail(@Query("date") String date,
                     @Query("cabang_id") int branchId);

    /**
     * For testing purposes only
     *
     * @return
     * @GET("v2/area/branches") Observable<AxiBranch>
     * getBranch();
     */

    @GET("v2/profile/axi/informasi-trip")
    Observable<TripInfo>
    getTripInfo(@Query("id") int id,
                @Query("cabang_id") int branchId);

    @GET("v2/profile/axi/gathering")
    Observable<GatheringInfo>
    getGatheringInfo(@Query("id") int id,
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
    @GET("v2/profile/axi/filter")
    Observable<AxiHome>
    searchWithFilter(@Query("status") String status,
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
    @GET("v2/profile/axi/filter")
    Observable<AxiHome>
    searchWithFilter(@Query("status") String status,
                     @Query("date") String date,
                     @Query("page") int page,
                     @Query("all") String all,
                     @Query("cabang_id") int branchId);

    @GET("v2/profile/axi/detail-intensif")
    Observable<Incentive>
    getBikeIncentive(@Query("id") int id,
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
    @GET("v2/profile/axi/filter")
    Observable<AxiHome>
    getHomeWithFilter(@Query("status") String status,
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
    @GET("v2/profile/axi/filter")
    Observable<AxiHome>
    getHomeWithFilter(@Query("status") String status,
                      @Query("date") String date,
                      @Query("page") int page,
                      @Query("cabang_id") int branchId);

    @GET("v2/profile/axi/home")
    Observable<AxiHome>
    getHome(@Query("page") int page,
            @Query("cabang_id") int branchId,
            @Query("show") int show);

    @GET("v2/profile/axi/detail")
    Observable<AxiProfile>
    getDetail(@Query("id") int id);

    @GET("v2/profile/axi/search")
    Observable<AxiHome>
    doSearch(@Query("page") int page,
             @Query("all") String keyword,
             @Query("cabang_id") int branchId);

    @GET("v2/profile/axi/registration")
    Observable<AxiRegister>
    getRegistration(@Query("cabang_id") int branchId);

}
