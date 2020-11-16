package pl.adriankurek.quicknotegreendao.data.database;

import android.app.Application;

import pl.adriankurek.quicknotegreendao.data.model.DaoMaster;
import pl.adriankurek.quicknotegreendao.data.model.DaoSession;

public class GreenDao extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DbOpenHelper(this, "notes.db").getWritableDb()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
