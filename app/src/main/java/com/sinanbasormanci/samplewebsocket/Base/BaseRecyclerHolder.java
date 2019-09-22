package com.sinanbasormanci.samplewebsocket.Base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.sinanbasormanci.samplewebsocket.Utils.ViewBinder.ViewBinder;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public abstract class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        ViewBinder.bindView(this, itemView);
    }
}
