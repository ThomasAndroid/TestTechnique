package com.thomas.test.di.modules;



import android.content.Context;
import com.apphero.testdagger.di.qualifier.NetworkInterceptor;
import com.apphero.testdagger.di.qualifier.OfflineInterceptor;
import com.thomas.test.Constant;
import com.thomas.test.Utils;
import com.thomas.test.di.scope.ApplicationScope;
import com.thomas.test.retrofit.APIInterface;
import dagger.Module;
import dagger.Provides;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    APIInterface getApiInterface(Retrofit retroFit) {
        return retroFit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                 @NetworkInterceptor Interceptor netWorkInterceptor,
                                 Cache cache,
                                 @OfflineInterceptor Interceptor offlineInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(netWorkInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }


    @Provides
    @ApplicationScope
    Cache provideCache(Context context) {
        long cacheSize = 20 * 1024 * 1024;
        return new Cache(context.getCacheDir(),cacheSize);
    }


    @Provides
    @ApplicationScope
    @OfflineInterceptor
    Interceptor provideOfflineInterceptor(final Context context) {
        return new Interceptor(){

            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl;
                if(Utils.hasNetwork(context)){
                    cacheControl = new CacheControl.Builder().maxAge(1, TimeUnit.MINUTES).build();
                } else {
                    cacheControl = new CacheControl.Builder().maxAge(30, TimeUnit.DAYS).build();
                }
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control",cacheControl.toString()).build();
            }
        };
    }

    @Provides
    @ApplicationScope
    @NetworkInterceptor
    Interceptor provideNetWorkInterceptor() {
        return new Interceptor(){

            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = new CacheControl.Builder().maxAge(30, TimeUnit.DAYS).build();
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control",cacheControl.toString()).build();
            }
        };
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }
}
