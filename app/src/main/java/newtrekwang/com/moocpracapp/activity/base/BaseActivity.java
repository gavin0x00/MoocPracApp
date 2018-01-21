package newtrekwang.com.moocpracapp.activity.base;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import newtrekwang.com.moocpracapp.util.StatusBarUtil;

/**
 * Created by dell on 2018/1/21.
 * 所有Activity的基类，用来处理一些公共事件，如数据统计
 */

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    // 改变状态栏颜色
    public void changeStatusBarColor(@ColorRes int color){
        StatusBarUtil.setStatusBarColor(this,color);
    }
    //调整状态栏为亮模式，这样状态栏的文字颜色就为深模式了
    private void reverseStatusColor(){
        StatusBarUtil.statusBarLightMode(this);
    }

}
