package com.sinanbasormanci.samplewebsocket.View.Activitys;

import com.sinanbasormanci.samplewebsocket.ApiService.ApiService;
import com.sinanbasormanci.samplewebsocket.ApiService.IApiResp;
import com.sinanbasormanci.samplewebsocket.Model.MData;
import com.sinanbasormanci.samplewebsocket.Model.MItem;
import com.sinanbasormanci.samplewebsocket.Utils.Convert;
import com.sinanbasormanci.samplewebsocket.WebSocket.DataCheck;
import com.sinanbasormanci.samplewebsocket.WebSocket.MSocket;

import java.util.Random;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class MainPresenter implements IMain.Presenter, MSocket.OnSocketListener, DataCheck.OnDataCheckListener {
    private static final String TAG = "MainPresenter";

    private IMain.View view;
    private MData data;
    private MSocket socket;
    private DataCheck dataCheck;

    public MainPresenter(IMain.View view) {
        this.view = view;
    }

    @Override
    public void onCreated() {
        callData();

        socket = new MSocket();
        socket.setOnSocketListener(this);

        dataCheck = new DataCheck(data);
        dataCheck.setOnSocketListener(this);
    }

    @Override
    public void actSend(String message) {
        socket.webSocket.send(message);
    }

    private void dataControl(String value){
        if (value.contains("-")){
            MItem item = Convert.toMItem(value);
            if (item != null){
                for (int i = 0; i < data.getData().size(); i++){
                    if (item.getId() == data.getData().get(i).getId()){
                        view.updateAdapterItem(i, item);
                    }
                }
            }
        }else if (value.toLowerCase().equals("logout")){
            view.setToolbarTitle("Signed Out");
        }else if (value.toLowerCase().equals("login" )){
            setToolRandomTitle();
        }else {
            view.showMessage("The entered value is not valid!");
        }
    }

    private void callData(){
        ApiService.getApiService()
                .getData().onResponse(new IApiResp<MData>() {
            @Override
            public void onSuccess(MData mData) {
                data = mData;
                view.setAdapter(mData);
                setToolRandomTitle();

                dataCheck.setData(mData);
            }

            @Override
            public void onUnauthorized() {

            }

            @Override
            public void onError() {
                view.showMessage("An error was encountered!");
            }
        });
    }

    private void setToolRandomTitle(){
        view.setToolbarTitle(data.getData().get(new Random().nextInt(data.getData().size())).getName());
    }


    @Override
    public void onMessage(String message) {
        dataCheck.check(message);
    }

    @Override
    public void onUpdateItem(int position, MItem item) {
        view.updateAdapterItem(position, item);
    }

    @Override
    public void onLogOut() {
        view.setToolbarTitle("Signed Out");
    }

    @Override
    public void onLogIn() {
        setToolRandomTitle();
    }

    @Override
    public void onNothing() {
        view.showMessage("The entered value is not valid!");
    }



}
