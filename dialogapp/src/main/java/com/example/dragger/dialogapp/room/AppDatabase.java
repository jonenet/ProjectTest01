package com.example.dragger.dialogapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp.room
 * ProjectName: ProjectTest01
 * Date: 2019/7/9 17:37
 */
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}