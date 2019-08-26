package com.northmeter.smartenergyregulation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.bean.MonitorBean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */

public class MainDevice_RvAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MonitorBean.MonitorList> models;

    public interface OnMyClickListener {
        void onItemClick(View view, int position);
    }

    public OnMyClickListener onClickListener;

    public void setOnMyClickListener(OnMyClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MainDevice_RvAdapter(Context context, List<MonitorBean.MonitorList> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_device, parent, false);
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
        private TextView itemComaddress;

        private void assignViews() {
            itemHomeListName = itemView.findViewById(R.id.text_name);
            itemComaddress = itemView.findViewById(R.id.text_comaddress);
        }


        public void setData(MonitorBean.MonitorList bean) {
            itemHomeListName.setText(bean.getMetername());
            itemComaddress.setText(bean.getComAddress());
        }


        @Override
        public void onClick(View v) {
            if (models.get(getLayoutPosition()) != null) {

                onClickListener.onItemClick(v,getLayoutPosition());

            }
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }


}
