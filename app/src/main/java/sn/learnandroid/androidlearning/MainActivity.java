package sn.learnandroid.androidlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddEtudiant;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_etudiants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         buttonAddEtudiant = findViewById(R.id.floating_button_add);
         buttonAddEtudiant.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
         Intent intent = new Intent(MainActivity.this, AddEtudiantActivity.class);
         startActivity(intent);
    }
  });

  getEtudiants();

    }

    private void getEtudiants() {
        class GetEtudiants extends AsyncTask<Void, Void, List<Etudiant>> {

            @Override
            protected List<Etudiant> doInBackground(Void... voids) {
                List<Etudiant> etudiantList = DatabaseClient.getInstance(getApplicationContext()).getEptDB().etudiantDao().getAllEtudiants();
                return etudiantList;
            }

            @Override
            protected void onPostExecute(List<Etudiant> etudiants) {
                super.onPostExecute(etudiants);
                EtudiantAdapter adapter = new EtudiantAdapter(MainActivity.this, etudiants);
                recyclerView.setAdapter(adapter);
            }
        }
        GetEtudiants getEtudiants = new GetEtudiants();
        getEtudiants.execute();
    }
}
