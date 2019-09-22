package com.sinanbasormanci.samplewebsocket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sinanbasormanci.samplewebsocket.Base.BaseRecyclerAdapter;
import com.sinanbasormanci.samplewebsocket.Base.BaseRecyclerHolder;
import com.sinanbasormanci.samplewebsocket.Model.MItem;
import com.sinanbasormanci.samplewebsocket.R;
import com.sinanbasormanci.samplewebsocket.Utils.ViewBinder.FindViewID;

import java.util.List;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class DataAdapter extends BaseRecyclerAdapter<MItem> {

    public DataAdapter(Context context, List<MItem> listObjects) {
        super(context, listObjects);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MItem ob = mListObjects.get(position);
        final ItemViewHolder vH = (ItemViewHolder) holder;

        vH.txtID.setText(ob.getId().toString());
        vH.txtName.setText(ob.getName());
        vH.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(ob, position);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup v, int viewType) {
        View mView = LayoutInflater.from(v.getContext())
                .inflate(R.layout.item_data, v, false);
        return new ItemViewHolder(mView);
    }


    public class ItemViewHolder extends BaseRecyclerHolder {

        @FindViewID(R.id.list_item)
        CardView list_item;

        @FindViewID(R.id.txtID)
        TextView txtID;

        @FindViewID(R.id.txtName)
        TextView txtName;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
