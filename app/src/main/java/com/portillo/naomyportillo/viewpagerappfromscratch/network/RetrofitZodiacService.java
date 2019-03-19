package com.portillo.naomyportillo.viewpagerappfromscratch.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface RetrofitZodiacService {


    String ENDPOINT = "JDVila/storybook/master/zodiac.json";

    @GET(ENDPOINT)
    Call<ZodiacResponse> getZodiac();

}
