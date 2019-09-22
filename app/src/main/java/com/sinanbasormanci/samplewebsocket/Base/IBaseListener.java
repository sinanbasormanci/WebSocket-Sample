package com.sinanbasormanci.samplewebsocket.Base;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public interface IBaseListener {

    interface Timer {
        void onSecond(int secont);
        void onFinish();
    }

    interface HandlerDelay {
        void delayAction();
    }

    interface Adapter<T> {
        void onItemClick(T item, int position);
        void onLoadMore(int listSize);
        void onListEmpty();
    }
}
