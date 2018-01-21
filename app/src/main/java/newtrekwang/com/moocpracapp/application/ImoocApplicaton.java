package newtrekwang.com.moocpracapp.application;

import android.app.Application;

/**
 * Created by dell on 2018/1/21.
 */

public class ImoocApplicaton extends Application {
    private static ImoocApplicaton mApplication = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
    public static ImoocApplicaton getInstance(){
        return mApplication;
    }
}
