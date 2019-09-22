package com.sinanbasormanci.samplewebsocket.ApiService;

import android.util.Log;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class ApiServiceGateway<T> {

    private static final String TAG = "ApiServiceGateway";

    private final int SUCCESS       = 200;
    private final int UNAUTHORIZED  = 401;

    private final int BADREQUEST    = 400;
    private final int NOTFOUND      = 404;
    private final int FAILED        = 500;

    private IApiResp<T> listener;
    private Response<T> obResponse;

    public ApiServiceGateway(Observable<Response<T>> observable) {
        setObservable(observable);
    }

    private void setObservable(final Observable<Response<T>> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Response<T> response) {
                        obResponse = response;
                    }
                    @Override
                    public void onError(Throwable e) {
                        handleError(e);

                        if (listener != null)
                            listener.onError();
                    }
                    @Override
                    public void onComplete() {
                        if (obResponse != null)
                        setGateway(obResponse);
                    }
                });
    }


    private void handleError(Throwable throwable) {
        Log.d(TAG, "onError: " + throwable.getMessage());

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException)throwable;
            int statusCode = httpException.code();
            // handle different HTTP error codes here (4xx)
            Log.d(TAG, "handleError: " + statusCode);
        } else if (throwable instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
        } else if (throwable instanceof IOException) {
            // file was not found, do something
        } else {

        }
    }

    private void setGateway(Response<T> response) {
        switch (response.code()) {
            case SUCCESS:
                if (listener != null)
                listener.onSuccess(response.body());
                break;
            case UNAUTHORIZED:
                listener.onUnauthorized();
                break;

                default:
                if (listener != null)
                listener.onError();
                break;
        }
    }


    public void onResponse(IApiResp<T> listener) {
        this.listener = listener;
    }

}
