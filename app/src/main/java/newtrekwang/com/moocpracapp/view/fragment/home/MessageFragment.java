package newtrekwang.com.moocpracapp.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import newtrekwang.com.moocpracapp.R;
import newtrekwang.com.moocpracapp.view.fragment.base.BaseFragment;

public class MessageFragment extends BaseFragment {


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_layout, container, false);
    }

}
