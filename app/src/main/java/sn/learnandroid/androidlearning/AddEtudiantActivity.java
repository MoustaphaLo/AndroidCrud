package sn.learnandroid.androidlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class AddEtudiantActivity extends AppCompatActivity {

    private EditText nom_, prenom_, email_;
    private Button btnAdd;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        nom_ = findViewById(R.id._nom);
        prenom_ = findViewById(R.id._prenom);
        email_ = findViewById(R.id._email);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEtudiant();
            }
        });
    }

    private void saveEtudiant() {
        final String _nom_ = nom_.getText().toString().trim();
        final String _prenom_ = prenom_.getText().toString().trim();
        final String _email_ = email_.getText().toString().trim();

        if (_nom_.isEmpty()) {
            nom_.setError("Le nom est obligatoire!");
            nom_.requestFocus();
            return;

        }

        if (_prenom_.isEmpty()) {
            prenom_.setError("Le prenom est obligatoire!");
            prenom_.requestFocus();
            return;

        }

        if (_email_.isEmpty()) {
            email_.setError("L'émail est obligatoire!");
            email_.requestFocus();
            return;
        }

        class SaveEtudiant extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... voids) {
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(_nom_);
                etudiant.setPrenom(_prenom_);
                etudiant.setEmail(_email_);

                DatabaseClient.getInstance(getApplicationContext()).getEptDB().etudiantDao().insert(etudiant);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveEtudiant saveStudent = new SaveEtudiant();
        saveStudent.execute();
    }
}