package com.sinanbasormanci.samplewebsocket.Base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sinanbasormanci.samplewebsocket.IConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IConstants {

    protected Context context;
    protected List<T> mListObjects;

    private int loadMoreSize = -1;

    public BaseRecyclerAdapter(Context context, List<T> listObjects){
        this.context = context;
        if (listObjects != null){
            mListObjects = listObjects;
        }else {
            mListObjects = new ArrayList<>();
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
    public Context getContext(){ return context; }

    public void setList(List<T> listObjects){
        if (listObjects != null){
            this.mListObjects = listObjects;
        }
    }

    public List<T> getList(){
        return mListObjects;
    }

    protected abstract void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position);
    protected abstract RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup v, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateBaseViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindBaseViewHolder(holder,position);

        if(position >= getItemCount()-1 && listener != null && loadMoreSize != getItemCount()-1){
            loadMoreSize = getItemCount()-1;
            listener.onLoadMore(getItemCount());
        }
    }

    @Override
    public int getItemCount() {
        if (mListObjects.size() == 0 && listener != null){ listener.onListEmpty(); }
        return mListObjects.size();
    }

    public boolean isEmptyList(){
        if (getItemCount() == 0){
            return true;
        }else {
            return false;
        }
    }

    public void removeItem(int position) {
        mListObjects.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListObjects.size());
    }

    public void updateItem(final int position, final T obj) {
        mListObjects.set(position, obj);
        notifyDataSetChanged();
    }

    public void notifyDataSet(List<T> list){
        mListObjects.addAll(list);
        notifyDataSetChanged();
    }

    public void notifyReload(List<T> list){
        mListObjects.clear();
        mListObjects.addAll(list);
        notifyDataSetChanged();
    }

    protected IBaseListener.Adapter<T> listener;
    public void setOnActionListener(IBaseListener.Adapter<T> listener){
        this.listener = listener;
    }

}
