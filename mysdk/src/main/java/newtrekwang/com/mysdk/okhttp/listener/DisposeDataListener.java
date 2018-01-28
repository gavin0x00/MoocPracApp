package newtrekwang.com.mysdk.okhttp.listener;

/**
 * Created by WJX on 2018/1/28.
 * 业务逻辑层真正处理的地方，包括java层异常和业务层异常
 */

public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     * @param reasonObj
     */
    public void onFailure(Object reasonObj);
}
