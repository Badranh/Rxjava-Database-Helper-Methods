package net.cryptobrewery.rxdatabaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.cryptobrewery.rxdatabaseexample.Database.DBHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper();

        disposable = dbHelper.getUsers()
                              .map(rs -> {
                                     List<String> users = new ArrayList<>();
                                     while (rs.next())
                                        users.add(rs.getString(2));
                                     return users;
                                     })
                              .subscribe(strings -> {
                                         for(String a : strings)
                                             Log.d("db",a);
                              });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
