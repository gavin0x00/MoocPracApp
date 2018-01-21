package newtrekwang.com.moocpracapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import newtrekwang.com.moocpracapp.R;
import newtrekwang.com.moocpracapp.activity.base.BaseActivity;
import newtrekwang.com.moocpracapp.view.fragment.home.HomeFragment;
import newtrekwang.com.moocpracapp.view.fragment.home.MessageFragment;
import newtrekwang.com.moocpracapp.view.fragment.home.MineFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private Fragment mCommonFragmentOne;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrent;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor(R.color.color_fed952);
        setContentView(R.layout.activity_home_layout);
        initView();// 初始化控件

        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mMineFragment = new MineFragment();
        fm = getFragmentManager();
        // 默认选中HomeFragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initView(){
        mHomeLayout = findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = findViewById(R.id.home_image_view);
        mPondView = findViewById(R.id.fish_image_view);
        mMessageView = findViewById(R.id.message_image_view);
        mMineView = findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }
    // hide 碎片
    private void hideFragment(Fragment fragment,FragmentTransaction fragmentTransaction){
        if (fragmentTransaction != null){
            fragmentTransaction.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
      final  FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_layout_view:
                 changeStatusBarColor(R.color.color_fed952);
                 mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                 mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                 mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                 mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                 hideFragment(mCommonFragmentOne,fragmentTransaction);
                 hideFragment(mMessageFragment,fragmentTransaction);
                 hideFragment(mMineFragment,fragmentTransaction);
                 if (mHomeFragment == null){
                     mHomeFragment = new HomeFragment();
                     fragmentTransaction.add(R.id.content_layout,mHomeFragment);
                 }else {
                     mCurrent = mHomeFragment;
                     fragmentTransaction.show(mHomeFragment);
                 }
                break;
            case R.id.message_layout_view:
                changeStatusBarColor(R.color.color_e3e3e3);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mCommonFragmentOne,fragmentTransaction);
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                if (mMessageFragment == null){
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout,mMessageFragment);
                }else {
                    mCurrent = mMessageFragment;
                    fragmentTransaction.show(mMessageFragment);
                }
                break;
            case R.id.mine_layout_view:
                changeStatusBarColor(android.R.color.white);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);

                hideFragment(mCommonFragmentOne,fragmentTransaction);
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMessageFragment,fragmentTransaction);
                if (mMineFragment == null){
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout,mMineFragment);
                }else {
                    mCurrent = mMineFragment;
                    fragmentTransaction.show(mMineFragment);
                }
                break;
                default:
                    break;
        }
        fragmentTransaction.commit();
    }
}
