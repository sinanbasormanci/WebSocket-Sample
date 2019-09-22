package com.sinanbasormanci.samplewebsocket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class MData {

    @SerializedName("data")
    @Expose
    private List<MItem> data = null;

    public List<MItem> getData() {
        return data;
    }

    public void setData(List<MItem> data) {
        this.data = data;
    }
}

