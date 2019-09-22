package com.sinanbasormanci.samplewebsocket.WebSocket;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class MSocket extends WebSocketListener{
    private static final String TAG = "MSocket";

    private final String urlSocket = "wss://echo.websocket.org";

    public WebSocket webSocket;

    private OkHttpClient client;
    private Request request;

    public MSocket() {
        client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        request = new Request.Builder().url(urlSocket).build();
        webSocket = client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.d(TAG, "onOpen: ");
    }

    @Override
    public void onMessage(WebSocket webSocket, final String text) {
        super.onMessage(webSocket, text);
        Log.d(TAG, "onMessage: " + text);
        if (listener != null) listener.onMessage(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        Log.d(TAG, "onMessage: ");
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        Log.d(TAG, "onClosing: ");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.d(TAG, "onClosed: ");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        Log.d(TAG, "onFailure: " + t.getMessage());
    }


    private OnSocketListener listener;
    public interface OnSocketListener{
        void onMessage(String message);
    }

    public void setOnSocketListener(OnSocketListener listener) {
        this.listener = listener;
    }
}