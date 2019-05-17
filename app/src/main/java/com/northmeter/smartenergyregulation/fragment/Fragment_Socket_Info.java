package com.northmeter.smartenergyregulation.fragment;

import android.os.Bundle;

import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.base.BaseFragment;

/**
 * Created by dyd on 2019/5/10.
 */

public class Fragment_Socket_Info extends BaseFragment{

    private static Fragment_Socket_Info fragment;

    public static Fragment_Socket_Info newInstance(int getType) {
        fragment = new Fragment_Socket_Info();
        Bundle bundle = new Bundle();
        bundle.putInt("getType", getType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_socket_info;
    }

    @Override
    protected void startGetArgument(Bundle savedInstanceState) {

    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {

    }
}
