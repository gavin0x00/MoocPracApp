package newtrekwang.com.mysdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import newtrekwang.com.mysdk.okhttp.exception.OkHttpException;
import newtrekwang.com.mysdk.okhttp.listener.DiaposeHandleCookieListener;
import newtrekwang.com.mysdk.okhttp.listener.DisposeDataHandle;
import newtrekwang.com.mysdk.okhttp.listener.DisposeDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by dell on 2018/1/28.
 */

public class CommonJsonCallback implements Callback {
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle hander) {
        this.mListener = hander.mListener;
        this.mClass = hander.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
            final String  result = response.body().string();
            final ArrayList<String> cookieLists = handleCookie(response.headers());
            mDeliveryHandler.post(new Runnable() {
                @Override
                public void run() {
                    handleResponse(result);
                    if (mListener instanceof DiaposeHandleCookieListener){
                        ((DiaposeHandleCookieListener) mListener).onCookie(cookieLists);
                    }
                }
            });
    }

    // 解析Headers 滤出Cookie
    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String > tempList = new ArrayList<>();
        for (int i= 0;i<headers.size();i++){
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)){
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    /**
     * 解析JSon
     * @param responseObj
     */
    private void handleResponse(Object responseObj){
        if(responseObj == null || responseObj.toString().trim().equals("")){
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }

        try {
            // 原始json解析
            if(mClass == null){//类型为null
                mListener.onSuccess(new JSONObject(responseObj.toString()));
            }else {
                // fastJson解析
                Object var = JSON.parseObject(responseObj.toString(),mClass);
                if(var != null){
                    mListener.onSuccess(var);
                }else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                }
            }

        } catch (JSONException e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
            e.printStackTrace();
        }

    }

}
