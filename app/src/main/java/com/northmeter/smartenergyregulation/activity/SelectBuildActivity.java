package com.northmeter.smartenergyregulation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.CommonAdapter;
import com.northmeter.smartenergyregulation.adapter.ViewHolder;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.CommonResponse;
import com.northmeter.smartenergyregulation.bean.SelectBuildBean;
import com.northmeter.smartenergyregulation.bean.SelectBuildShow;
import com.northmeter.smartenergyregulation.presenter.SelectBuildPresenter;
import com.northmeter.smartenergyregulation.widget.PopupHelper;
import com.northmeter.smartenergyregulation.widget.WidgetHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/1/3.
 * 建筑查询
 */

public class SelectBuildActivity extends BaseActivity implements I_ShowData {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.linear_contain)
    LinearLayout linearContain;

    private ListView listview;//点击建筑按钮是显示的列表
    private View view;
    private PopupWindow popupWindow;
    private Map<Integer,List<SelectBuildBean.TreeBuild>> btnAndListMap;//存储每个建筑按钮的建筑列表
    private Map<Object,SelectBuildBean.TreeBuild> btnSelectMap;//存储选择的建筑信息
    private Map<Integer,Integer> typeList;//判断当前btnSelectMap相同key值的建筑信息父节点建筑还是子节点建筑
    private CommonAdapter commonAdapter;
    private List<SelectBuildShow> buildDatas;

    private List<View> viewList;
    private List<ViewHolder> viewHolderList;
    private int mark=0;
    private static int checkViewHolder=0;
    private String checkedBuild = "0";

    private SelectBuildPresenter selectBuildPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_build;
    }

    @Override
    public void initIntentData() {
        super.initIntentData();
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tvToolbarTitle.setText("建筑筛选");
    }

    @Override
    public void initData() {
        super.initData();
        selectBuildPresenter = new SelectBuildPresenter(this);
        selectBuildPresenter.getTreeBuild(1,"001008","");

        viewList = new ArrayList<>();
        viewHolderList = new ArrayList<>();
        
        buildDatas = new ArrayList<>();
        btnAndListMap = new HashMap<>();//存储默认加载的PopupWindow的建筑列表
        btnSelectMap = new HashMap();//选择建筑后存储该建筑信息
        typeList = new HashMap<>();

        initpopupWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @OnClick({R.id.btn_tb_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tb_back:
                this.finish();
                break;

            case R.id.btn_submit://提交
                Intent i = new Intent();
                i.putExtra("result",checkedBuild);
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }


    private void initpopupWindow(){
        view = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.item_progect_import_poput, null);
        popupWindow = new PopupHelper().getWindow_ALLWRAP(view, getApplicationContext());
        popupWindow.setWidth((int) (WidgetHelper.getWindowWidth(this)));

        listview = view.findViewById(R.id.listview);
        commonAdapter = new CommonAdapter<SelectBuildShow>(this, buildDatas, R.layout.item_progect_import_poput_view) {
            @Override
            public void convert(com.northmeter.smartenergyregulation.adapter.ViewHolder helper, SelectBuildShow item) {
                helper.getTextViewSet(R.id.tv_build_addr, item.getBuildingname());
            }
        };

        listview.setAdapter(commonAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(typeList.get(mark-1)==0){
                    viewHolderList.get(checkViewHolder).tv_progect_addr.setText(buildDatas.get(position).getBuildingname());
                    if(buildDatas.get(position).getIsleaf()==0){
                        checkedBuild = btnAndListMap.get(mark-1).get(position).getBuildinginfoEntityList().get(0).getBuildingid();
                        initAddView(mark, btnAndListMap.get(mark-1).get(position).getBuildinginfoEntityList().get(0).getBuildingname());
                        //btnAndListMap.put(mark, btnAndListMap.get(mark-1));
                        typeList.put(mark,1);
                        btnSelectMap.put(mark-1,btnAndListMap.get(mark-1).get(position));
                        mark++;

                    }
                }else{
                    if (buildDatas.get(position).getIsleaf()==0){
                        selectBuildPresenter.getTreeBuild(1,"001008",buildDatas.get(position).getBuildingid());
                        checkedBuild  = buildDatas.get(position).getBuildingid();
                    }
                }
                popupWindow.dismiss();
            }
        });
    }

    private void initAddView(int mark,String buildName){
        View addView = LayoutInflater.from(SelectBuildActivity.this).inflate(R.layout.item_select_build_view, null);
        addView.setId(mark);
        linearContain.addView(addView, mark);
        getViewInstance(addView,buildName);
    }

    private void getViewInstance(View view ,String buildName){
        ViewHolder vh = new ViewHolder();
        vh.id = view.getId();
        vh.relative_content = (RelativeLayout) view.findViewById(R.id.relative_content);
        vh.tv_progect_addr = (TextView) view.findViewById(R.id.tv_progect_addr);
        vh.tv_progect_addr.setText(buildName);
        // 设置监听
        vh.relative_content.setOnClickListener(selectProListener);
        viewHolderList.add(vh);
        viewList.add(view);

    }


    public class ViewHolder {
        private int id = -1;
        private RelativeLayout relative_content;
        private TextView tv_progect_addr;
    }

    View.OnClickListener selectProListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            View view = (View) v.getParent();
            for (int i = 0; i < linearContain.getChildCount(); i++) {
                ViewHolder viewHolder = viewHolderList.get(i);
                Log.v("Import", "view.getId()==" + view.getId() + "  viewHolder.id==" + viewHolder.id);
                if (view.getId() == viewHolder.id) {
                    removeViews(i);
                    checkViewHolder = i;
                    System.out.println(view.getId() + "/" + viewHolder.id);
                    popupWindow.showAsDropDown(v, 0, 0, Gravity.CENTER);

                    buildDatas.clear();
                    if (typeList.get(mark-1) == 0) {
                        List<SelectBuildBean.TreeBuild> data = btnAndListMap.get(view.getId());
                        for (SelectBuildBean.TreeBuild item : data) {
                            SelectBuildShow selectBuildShow = new SelectBuildShow(item.getBuildingid(), item.getBuildingname(),
                                    item.getParentid(), item.getAddress(), item.getBuildinglevel(), item.getBuildingtype(), item.getIsleaf());
                            buildDatas.add(selectBuildShow);
                        }
                    } else {
                        List<SelectBuildBean.BuildinginfoEntityList> entityList = btnSelectMap.get(mark-2).getBuildinginfoEntityList();
                        for (SelectBuildBean.BuildinginfoEntityList item : entityList) {
                            SelectBuildShow selectBuildShow = new SelectBuildShow(item.getBuildingid(), item.getBuildingname(),
                                    item.getParentid(), item.getAddress(), item.getBuildinglevel(), item.getBuildingtype(), item.getIsleaf());
                            buildDatas.add(selectBuildShow);
                        }
                        commonAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    };

    /**
     *删除排在被点击按钮后面按钮*/
    private void removeViews(int index){
        int childNum = linearContain.getChildCount();
        for(int i = childNum-1; i >= 0; i--){
            if(i > index){
                linearContain.removeViewAt(i);
                viewHolderList.remove(i);
                viewList.remove(i);
                typeList.remove(i);
            }
        }
        mark = index+1;
    }


    @Override
    public void returnMessage(String message) {

    }

    @Override
    public void showData(CommonResponse commonResponse) {
        SelectBuildBean selectBuildBean = (SelectBuildBean) commonResponse;
        List<SelectBuildBean.TreeBuild> treeBuildList = selectBuildBean.getList();

        if(!treeBuildList.isEmpty()){
            if(mark==0){
                initAddView(mark,treeBuildList.get(0).getBuildingname());
                btnAndListMap.put(mark,treeBuildList);
                typeList.put(mark,0);
                btnSelectMap.put(mark,treeBuildList.get(0));
                checkedBuild = treeBuildList.get(0).getBuildingid();
                mark++;
            }else{
                initAddView(mark, treeBuildList.get(0).getBuildinginfoEntityList().get(0).getBuildingname());
                btnAndListMap.put(mark, treeBuildList);
                typeList.put(mark,1);
                btnSelectMap.put(mark-1,treeBuildList.get(0));
                checkedBuild = treeBuildList.get(0).getBuildinginfoEntityList().get(0).getBuildingid();
                mark++;
            }

        }

    }


}