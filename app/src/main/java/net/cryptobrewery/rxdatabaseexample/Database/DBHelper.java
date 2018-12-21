package net.cryptobrewery.rxdatabaseexample.Database;


import android.util.Log;

import java.sql.ResultSet;
import java.util.concurrent.Callable;

import io.reactivex.Observable;


public class DBHelper {

    public DBHelper() {
    }

    private static <T> Observable<T> makeObservable(final Callable<T> function) {
        return  Observable.create(
                emitter -> {
                    try {
                        if(!emitter.isDisposed())
                             emitter.onNext(function.call());
                    } catch(Exception ex) {
                        Log.e("asd", "Error reading from the database", ex);
                    }
                });
    }


      public Observable<ResultSet> getUsers() {
        return makeObservable(new SQLQueryExecuter("select * from users") {
            @Override
            public void failure(Exception ex) {
                Log.d("Database Helper",ex.getLocalizedMessage());
            }
        });
    }



}
