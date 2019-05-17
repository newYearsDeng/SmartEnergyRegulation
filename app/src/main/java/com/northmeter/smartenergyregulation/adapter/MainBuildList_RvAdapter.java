package com.northmeter.smartenergyregulation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.bean.BuildListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */

public class MainBuildList_RvAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BuildListBean.BuildList> models;

    public MainBuildList_RvAdapter(Context context,List<BuildListBean.BuildList> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_build, parent, false);
        return new HomeListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof HomeListItemHolder)) {
            return;
        }
        HomeListItemHolder homeListItemHolder = (HomeListItemHolder) holder;
        homeListItemHolder.setData(models.get(position));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    class HomeListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public HomeListItemHolder(View itemView) {
            super(itemView);
            assignViews();
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private TextView itemHomeListName;

        private void assignViews() {
            itemHomeListName = (TextView) itemView.findViewById(R.id.text_name);
        }


        public void setData(BuildListBean.BuildList bean) {
            itemHomeListName.setText(bean.getBuildingname());
        }


        @Override
        public void onClick(View v) {
            if (models.get(getLayoutPosition()) != null) {
//                Device device = devices.get(getLayoutPosition());
//                ActivityControlMain.openActivity(context, device.type, device.meterCode, device.mac);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }


}
