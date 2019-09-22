package com.sinanbasormanci.samplewebsocket.Base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sinanbasormanci.samplewebsocket.IConstants;
import com.sinanbasormanci.samplewebsocket.R;
import com.sinanbasormanci.samplewebsocket.Utils.AppUtil;
import com.sinanbasormanci.samplewebsocket.Utils.ViewBinder.ViewBinder;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public abstract class BaseActivity extends AppCompatActivity implements IConstants {

    private final String TAG = "BaseActivity";

    public Context context;
    public Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewID());
        setStatusBarColor();

        context = this;
        extras = getIntent().getExtras();

        onExtras(extras);
        onCreatedActivity();
    }

    public abstract int contentViewID();
    public abstract void onExtras(Bundle extras);
    public abstract void onCreatedActivity();
    public abstract void onBack();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewBinder.bind(this);
    }

    public View getRootView(){
        return getWindow().getDecorView().getRootView();
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(AppUtil.colorIU(this, R.color.statusBar));
        }
    }

    public void setToolbar(Toolbar toolbar){
        if (getSupportActionBar() != null)
        setSupportActionBar(toolbar);
    }

    public void setToolbarBack(Toolbar toolbar){
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void hideToolbar(){
        getSupportActionBar().hide();
    }

    public void isFullScreen(boolean isFullScreen){
        if (isFullScreen){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void setRecyclerView(RecyclerView recyclerView){
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.onBack();
    }

}
