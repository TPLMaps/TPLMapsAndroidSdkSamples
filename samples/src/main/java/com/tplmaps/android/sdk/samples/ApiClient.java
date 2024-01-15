package com.tplmaps.android.sdk.samples;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit retrofit = null;
    public static boolean isClosed = false;

    @SuppressLint("NewApi")
    public static synchronized ApiInterface getInstance() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .validateEagerly(true)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getRequestHeader())
                    .build();

        }

        return retrofit.create(ApiInterface.class);
    }


    private static OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(25);
        dispatcher.setMaxRequestsPerHost(20);

        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(new TokenInterceptor())

                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = null;
                        Response response = null;
                        boolean responseOK = false;
                        int tryCount = 0;

                        synchronized (this) {
                            while (!responseOK && tryCount < 5) {

                                try {
//                                    if (!Utils.restoreSession(MyApp.getContext()).containsKey("token")
//                                            || TextUtils.isEmpty(Utils.restoreSession(MyApp.getContext()).get("token").toString())) {
//                                        Utils.generateToken(MyApp.getContext());
//                                    }
                                    request = chain.request()
                                            .newBuilder()
                                            .addHeader("Authorization", "")
                                            .build();

                                    response = chain.proceed(request);
                                    if (response.isSuccessful() || response.code() == 400 || response.code() == 404) {
                                        responseOK = true;
                                    }
                                    if (!responseOK && response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                       // Utils.generateToken(MyApp.getContext());
                                        request = chain.request()
                                                .newBuilder()
                                                .addHeader("Authorization", "")
                                                .build();
                                        response = chain.proceed(request);
                                        if (response.isSuccessful() || response.code() == 400 || response.code() == 404) {
                                            responseOK = true;
                                        }
                                    }
                                } catch (Exception e) {
                                    try {
                                        Log.e("intercept", "Request is not successful - " + tryCount);
                                        //Thread.sleep(API_RETRY_DELAY_MS);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } finally {
                                    tryCount++;
                                }

                            }
                        }
                        if (response == null) {
                            response = new Response.Builder()
                                    .code(500)
                                    .message("retry")
                                    .request(chain.request())
                                    .protocol(Protocol.HTTP_1_0)
                                    .body(ResponseBody.create(MediaType.parse("application/json"), "retry"))
                                    .addHeader("content-type", "application/json")
                                    .build();
                        }
                        return response;
                    }
                })

                .dispatcher(dispatcher)
                .addInterceptor(interceptor)
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)

                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .followSslRedirects(false)
                .build();

        return httpClient;
    }


}
