package com.sinanbasormanci.samplewebsocket.WebSocket;

import com.sinanbasormanci.samplewebsocket.Model.MData;
import com.sinanbasormanci.samplewebsocket.Model.MItem;
import com.sinanbasormanci.samplewebsocket.Utils.Convert;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class DataCheck {

    private MData data;

    public DataCheck(MData data) {
        this.data = data;
    }

    public MData getData() {
        return data;
    }

    public void setData(MData data) {
        this.data = data;
    }

    public void check(String value){
        if (value.contains("-")){
            if (data != null && Convert.toMItem(value) != null){
                MItem item = Convert.toMItem(value);
                if (item != null){
                    for (int i = 0; i < data.getData().size(); i++){
                        if (item.getId() == data.getData().get(i).getId()){
                            if (listener != null) listener.onUpdateItem(i, item);
                        }
                    }
                }
            }else{
                if (listener != null) listener.onNothing();
            }
        }else if (value.toLowerCase().equals("logout")){
            if (listener != null) listener.onLogOut();
        }else if (value.toLowerCase().equals("login" )){
            if (listener != null) listener.onLogIn();
        }else {
            if (listener != null) listener.onNothing();
        }
    }

    private OnDataCheckListener listener;
    public interface OnDataCheckListener{
        void onUpdateItem(int position, MItem item);
        void onLogOut();
        void onLogIn();
        void onNothing();
    }

    public void setOnSocketListener(OnDataCheckListener listener) {
        this.listener = listener;
    }

}
