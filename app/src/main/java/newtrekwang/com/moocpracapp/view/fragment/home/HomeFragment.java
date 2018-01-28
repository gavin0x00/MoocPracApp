package newtrekwang.com.moocpracapp.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import newtrekwang.com.moocpracapp.R;
import newtrekwang.com.moocpracapp.view.fragment.base.BaseFragment;
import newtrekwang.com.mysdk.okhttp.CommonOkHttpClient;
import okhttp3.OkHttpClient;


public class HomeFragment extends BaseFragment {
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        OkHttpClient okHttpClient = CommonOkHttpClient.getOkHttpClient()


        return inflater.inflate(R.layout.fragment_home_layout, container, false);
    }





}
