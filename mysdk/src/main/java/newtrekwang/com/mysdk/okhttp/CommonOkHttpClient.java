package newtrekwang.com.mysdk.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import newtrekwang.com.mysdk.okhttp.cookie.SimpleCookieJar;
import newtrekwang.com.mysdk.okhttp.https.HttpsUtils;
import newtrekwang.com.mysdk.okhttp.listener.DisposeDataHandle;
import newtrekwang.com.mysdk.okhttp.response.CommonFileCallback;
import newtrekwang.com.mysdk.okhttp.response.CommonJsonCallback;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2018/1/28.
 */

public class CommonOkHttpClient {
    // 超时时间
    private static int TIME_OUT = 30;
    //okhttp client
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpClientBuilder.addInterceptor(new Interceptor() {//添加拦截器，往请求里统计加上请求头，看个人需求
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent","Imooc-Mobile")
                        .build();
                return chain.proceed(request);
            }
        });
        okHttpClientBuilder.cookieJar(new SimpleCookieJar()); //设置cookie
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
//      trust all the https point
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpClientBuilder.build();

    }

    public static   OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
//    /**
//     * 指定cilent信任指定证书
//     *
//     * @param certificates
//     */
//    public static void setCertificates(InputStream... certificates) {
//        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
//    }

    public static Call get(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static <T> Call post(Request request,DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile (Request request,DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }


}
