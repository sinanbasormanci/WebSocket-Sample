package com.sinanbasormanci.samplewebsocket.View.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.sinanbasormanci.samplewebsocket.Adapter.DataAdapter;
import com.sinanbasormanci.samplewebsocket.Base.BaseActivity;
import com.sinanbasormanci.samplewebsocket.Model.MData;
import com.sinanbasormanci.samplewebsocket.Model.MItem;
import com.sinanbasormanci.samplewebsocket.R;
import com.sinanbasormanci.samplewebsocket.Utils.ViewBinder.FindViewID;


public class MainActivity extends BaseActivity implements IMain.View, View.OnClickListener {

    private static final String TAG = "MainActivity";

    @FindViewID(R.id.toolbar)
    Toolbar toolbar;

    @FindViewID(R.id.recyclerView)
    RecyclerView recyclerView;

    @FindViewID(R.id.editText)
    EditText editText;

    @FindViewID(R.id.button)
    Button button;

    private MainPresenter presenter;
    private DataAdapter dataAdapter;

    @Override
    public int contentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public void onExtras(Bundle extras) {
        if (extras != null){

        }
    }

    @Override
    public void onCreatedActivity() {
        presenter = new MainPresenter(this);
        presenter.onCreated();

        setToolbar(toolbar);

        setRecyclerView(recyclerView);
        button.setOnClickListener(this);
    }

    @Override
    public void onBack() {

    }


    @Override
    public void setToolbarTitle(final String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toolbar.setSubtitle(title);
            }
        });
    }

    @Override
    public void setAdapter(MData data) {
        if (dataAdapter == null){
            dataAdapter = new DataAdapter(context, data.getData());

            recyclerView.setAdapter(dataAdapter);
        }else {
            dataAdapter.notifyReload(data.getData());
        }
    }

    @Override
    public void updateAdapterItem(final int position, final MItem item) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dataAdapter != null){
                    dataAdapter.updateItem(position, item);
                }
            }
        });
    }

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Warning!")
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                presenter.actSend(editText.getText().toString());
                break;
        }
    }

}