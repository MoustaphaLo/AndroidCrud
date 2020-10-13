package sn.learnandroid.androidlearning;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EtudiantDao {
    @Insert
    Long insert(Etudiant etudiant);
    @Query("SELECT * FROM `users`")
    List<Etudiant> getAllEtudiants();

    @Query("SELECT * FROM `users` WHERE `id` =:id")
    Etudiant getEtudiant(int id);

    @Update
    void update(Etudiant etudiant);
    @Delete
    void delete(Etudiant etudiant);
}
