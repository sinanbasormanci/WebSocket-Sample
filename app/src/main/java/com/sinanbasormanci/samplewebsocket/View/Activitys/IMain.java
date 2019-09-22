package com.sinanbasormanci.samplewebsocket.View.Activitys;

import com.sinanbasormanci.samplewebsocket.Base.IBaseUI;
import com.sinanbasormanci.samplewebsocket.Model.MData;
import com.sinanbasormanci.samplewebsocket.Model.MItem;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public interface IMain {
    interface View extends IBaseUI.View {
        void setToolbarTitle(String title);
        void setAdapter(MData data);
        void updateAdapterItem(int position, MItem item);
        void showMessage(String message);
    }

    interface Presenter extends IBaseUI.Presenter{
        void actSend(String message);
    }
}
