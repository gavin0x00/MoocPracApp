package newtrekwang.com.moocpracapp.network.http;

import newtrekwang.com.moocpracapp.module.user.User;
import newtrekwang.com.mysdk.okhttp.CommonOkHttpClient;
import newtrekwang.com.mysdk.okhttp.listener.DisposeDataHandle;
import newtrekwang.com.mysdk.okhttp.listener.DisposeDataListener;
import newtrekwang.com.mysdk.okhttp.request.CommonRequest;
import newtrekwang.com.mysdk.okhttp.request.RequestParams;

/**
 * Created by dell on 2018/1/28.
 */

public class RequestCenter {
    //根据参数发送所有post请求
    public static  void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    public static void login(String userName,String passwd,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("mb",userName);
        params.put("pwd",passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN,params,listener,User.class );
    }
}
