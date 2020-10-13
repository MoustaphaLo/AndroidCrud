package sn.learnandroid.androidlearning;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import sn.learnandroid.androidlearning.Etudiant;

@Database(entities = {Etudiant.class}, version = 1)
public abstract class EptDB extends RoomDatabase {
    public  abstract EtudiantDao etudiantDao();
}
