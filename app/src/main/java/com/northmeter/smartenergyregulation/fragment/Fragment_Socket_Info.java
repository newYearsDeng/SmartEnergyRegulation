package com.northmeter.smartenergyregulation.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import com.northmeter.smartenergyregulation.Interface.I_ShowDevice;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.base.BaseFragment;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;
import com.northmeter.smartenergyregulation.presenter.DeviceMainPresenter;
import com.northmeter.smartenergyregulation.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dyd on 2019/5/10.
 */

public class Fragment_Socket_Info extends BaseFragment implements I_ShowDevice {

    private static Fragment_Socket_Info fragment;
    Unbinder unbinder;

    @BindView(R.id.tv_title_gl)
    TextView tvTitleGl;
    @BindView(R.id.tv_title_zt)
    TextView tvTitlezt;
    @BindView(R.id.check_box_control)
    CheckBox checkBoxControl;

    AllleafmonitorBean.MeterList meterData;
    @BindView(R.id.tv_comaddress)
    TextView tvComaddress;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_glxx)
    TextView tvGlxx;
    @BindView(R.id.tv_statement)
    TextView tvStatement;
    @BindView(R.id.tv_powertotal)
    TextView tvPowertotal;
    @BindView(R.id.tv_voltage)
    TextView tvVoltage;
    @BindView(R.id.tv_current)
    TextView tvCurrent;
    @BindView(R.id.tv_watts)
    TextView tvWatts;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_frequency)
    TextView tvFrequency;
    @BindView(R.id.tv_powerfactor)
    TextView tvPowerfactor;
    @BindView(R.id.tv_updatetime)
    TextView tvUpdatetime;
    private String checkBuildId;
    DeviceMainPresenter deviceMainPresenter;

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
        checkBuildId = SharedPreferencesUtil.getPrefString(getActivity(), "checkBuildId", "001");
        meterData = (AllleafmonitorBean.MeterList) getActivity().getIntent().getSerializableExtra("meterData");
        deviceMainPresenter = new DeviceMainPresenter(getActivity(), Fragment_Socket_Info.this);

    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        showSocketData(meterData);

    }

    private void showSocketData(AllleafmonitorBean.MeterList meterData) {
        if(meterData!=null){
            tvTitleGl.setText(meterData.getPowertotal()+"W");
            tvTitlezt.setText(meterData.getStatus());
            tvTitlezt.setTextColor(meterData.getStatus().equals("跳闸") ? Color.RED : Color.BLACK);
            checkBoxControl.setChecked(meterData.getStatus().equals("合闸"));

            tvComaddress.setText(meterData.getComaddress());
            tvStatus.setText(meterData.getStatus());
            tvGlxx.setText(meterData.getGlxx()+"");
            tvStatement.setText(meterData.getStatement());
            tvPowertotal.setText(meterData.getPowertotal()+"W");
            tvVoltage.setText(meterData.getVoltage()+"V");
            tvCurrent.setText(meterData.getCurrent()+"A");
            tvWatts.setText(meterData.getWatts()+"kWh");
            tvTemperature.setText(meterData.getTemperature()+"℃");
            tvFrequency.setText(meterData.getFrequency()+"Hz");
            tvPowerfactor.setText(meterData.getPowerfactor()+"");
            tvUpdatetime.setText(meterData.getUpdatetime());
        }
    }


    public void message_dialog_show(AllleafmonitorBean.MeterList meterData) {
        final AlertDialog dialogSex = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialog)).create();
        dialogSex.show();
        Window window = dialogSex.getWindow();
        window.setContentView(R.layout.dialog_layout);
        dialogSex.setCanceledOnTouchOutside(true);
        dialogSex.setCancelable(true);
        // 在此设置显示动画
        window.setWindowAnimations(R.style.AnimBottom_Dialog);
        TextView tvShowYdl = window.findViewById(R.id.tv_show_ydl);
        TextView tvShowDy = window.findViewById(R.id.tv_show_dy);
        TextView tvShowDl = window.findViewById(R.id.tv_show_dl);
        TextView tvShowGl = window.findViewById(R.id.tv_show_gl);
        TextView tvShowDwpl = window.findViewById(R.id.tv_show_dwpl);
        TextView tvShowGlys = window.findViewById(R.id.tv_show_glys);
        TextView tvShowTemp = window.findViewById(R.id.tv_show_temp);
        TextView tvShowTime = window.findViewById(R.id.tv_show_time);

        tvShowYdl.setText(meterData.getWatts() + "kWh");
        tvShowDy.setText(meterData.getVoltage() + "V");
        tvShowDl.setText(meterData.getCurrent() + "A");
        tvShowGl.setText(meterData.getPowertotal() + "W");
        tvShowDwpl.setText(meterData.getFrequency() + "");
        tvShowGlys.setText(meterData.getPowerfactor() + "");
        tvShowTemp.setText(meterData.getTemperature() + "℃");
        tvShowTime.setText(meterData.getUpdatetime());


        dialogSex.show();
        window.findViewById(R.id.relativeLayout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dialogSex.cancel();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_dialog, R.id.img_refresh, R.id.check_box_control})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_dialog:
                message_dialog_show(meterData);
                break;
            case R.id.img_refresh:
                break;
            case R.id.check_box_control:
                String commandID = "3";
                if (checkBoxControl.isChecked()) {
                    commandID = "4";//合闸
                } else {
                    commandID = "3";//跳闸
                }
                deviceMainPresenter.controlRealSave(commandID, new String[]{checkBuildId}, new String[]{meterData.getComaddress()});
                break;
        }
    }

    @Override
    public void showData(List<MonitorHistoryBean.HistoryList> datas) {

    }

    @Override
    public void returnMessage(int code, String msg) {
        if (code != 0) {
            checkBoxControl.setChecked(!checkBoxControl.isChecked());
        }
        showToastMsg(msg);
    }
}
