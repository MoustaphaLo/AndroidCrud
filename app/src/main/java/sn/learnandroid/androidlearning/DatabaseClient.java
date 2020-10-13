package sn.learnandroid.androidlearning;
import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context ctx;
    private static DatabaseClient mInstance;

    private EptDB eptDB;
    private  DatabaseClient(Context ctx) {
        this.ctx = ctx;

        eptDB = Room.databaseBuilder(ctx, EptDB.class, "EtudiantsEpt").build();
    }

    public static synchronized  DatabaseClient getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(ctx);
        }
        return  mInstance;
    }

    public EptDB getEptDB() {
        return eptDB;
    }
}
