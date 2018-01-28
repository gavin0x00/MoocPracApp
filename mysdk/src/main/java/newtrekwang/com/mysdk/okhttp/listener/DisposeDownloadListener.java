package newtrekwang.com.mysdk.okhttp.listener;

/**
 * Created by dell on 2018/1/28.
 */

public interface DisposeDownloadListener  extends DisposeDataListener{
    /**
     * 进度
     * @param progress
     */
    public void onProgress(int progress);
}
