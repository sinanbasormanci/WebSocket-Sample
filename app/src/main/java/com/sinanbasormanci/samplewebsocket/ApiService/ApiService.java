package com.sinanbasormanci.samplewebsocket.ApiService;

import com.sinanbasormanci.samplewebsocket.Model.MData;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class ApiService {

    private static ApiService apiService;
    public static ApiService getApiService(){
        if (apiService == null){
            apiService = new ApiService();
        }
        return apiService;
    }

    private ApiRetrofit apiRetrofit;
    private IApiService iApiService;

    public ApiService() {
        apiRetrofit = new ApiRetrofit();
        iApiService = apiRetrofit.getRetrofit().create(IApiService.class);
    }

    public ApiServiceGateway<MData> getData(){
        return new ApiServiceGateway<>(iApiService.getData());
    }
}
