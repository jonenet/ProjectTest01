package com.example.dragger.dialogapp.rx.room;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dragger.dialogapp.MyApplication;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp.rx.room
 * ProjectName: ProjectTest01
 * Date: 2019/7/9 17:40
 */
public class DBManager {

    private AppDatabase mUserDatabase;

    private DBManager() {
        mUserDatabase = Room.databaseBuilder(MyApplication.getContext(), AppDatabase.class, "users.db")
//                .addMigrations(MIGRATION_1_2)
//                .addMigrations(MIGRATION_2_3)
                .build();
    }


    private static DBManager sDBManager;

    public static DBManager getInstance() {
        if (sDBManager == null) {
            synchronized (DBManager.class) {
                if (sDBManager == null) {
                    sDBManager = new DBManager();
                }
            }
        }
        return sDBManager;
    }

    public AppDatabase getUserDatabase() {
        return mUserDatabase;
    }


    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD is_male INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD address TEXT");
        }
    };

}
