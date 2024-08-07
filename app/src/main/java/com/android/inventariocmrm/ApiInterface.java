package com.android.inventariocmrm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String CACHE = "Cache-Control: max-age=0";
    String AGENT = "Data-Agent: CMRM - Inventário";

    @Headers({CACHE, AGENT})
    @GET("api/getequipamentosdetalhes")
    Call<CallbackEquipamentosDetalhes> getEquipamentosDetalhes(
            @Query("id") long id
    );

    @Headers({CACHE, AGENT})
    @GET("api/getequipamentos/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL")
    Call<CallbackEquipamentos> getRecentesEquipamentos(
            @Query("page") int page,
            @Query("count") int count
    );

    @Headers({CACHE, AGENT})
    @GET("api/get_search_results/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL")
    Call<CallbackEquipamentos> getPesquisaEquipamentos(
            @Query("search") String search,
            @Query("count") int count
    );
}
