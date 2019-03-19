package com.portillo.naomyportillo.viewpagerappfromscratch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.portillo.naomyportillo.viewpagerappfromscratch.model.ZodiacModel;
import com.portillo.naomyportillo.viewpagerappfromscratch.network.RetrofitSingleton;
import com.portillo.naomyportillo.viewpagerappfromscratch.network.RetrofitZodiacService;
import com.portillo.naomyportillo.viewpagerappfromscratch.network.ZodiacResponse;
import com.portillo.naomyportillo.viewpagerappfromscratch.viewpager.ViewPagerFragment;
import com.portillo.naomyportillo.viewpagerappfromscratch.viewpager.ZodiacViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {

    private static final String TAG = ".MainActivity";
    private List<ZodiacModel> zodiacModelList;
    List<Fragment> fragmentList = new ArrayList<>();
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.mainActivity_viewPager);
        retrofitCall();
    }

    private void retrofitCall() {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        RetrofitZodiacService zodiacService = retrofit.create(RetrofitZodiacService.class);
        Call<ZodiacResponse> zodiacCall = zodiacService.getZodiac();
        zodiacCall.enqueue(new Callback<ZodiacResponse>() {
            @Override
            public void onResponse(Call<ZodiacResponse> call, Response<ZodiacResponse> response) {

                zodiacModelList = response.body().getZodiac();
                Log.d(TAG, "Nummy - This retrofit call was successful" + response.body().toString());
                viewPagerSetUp();
            }

            @Override
            public void onFailure(Call<ZodiacResponse> call, Throwable t) {
                Log.d(TAG, "Nummy - On Failure, This retrofit call was not successful" + t.getMessage());

            }
        });
    }

    private void viewPagerSetUp() {
        for (int i = 0; i < zodiacModelList.size(); i++) {
            fragmentList.add(ViewPagerFragment.newInstance(zodiacModelList.get(i).getName(), zodiacModelList.get(i).getImage(), zodiacModelList.get(i).getNumber()));
        }
        viewPager.setAdapter(new ZodiacViewPagerAdapter(getSupportFragmentManager(), fragmentList));
    }
}
