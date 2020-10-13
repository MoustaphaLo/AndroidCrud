package sn.learnandroid.androidlearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class updateEtudiant extends AppCompatActivity {

    private EditText _nom_, _prenom_, _email_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_etudiant);

        _nom_ = findViewById(R.id._nom);
        _prenom_ = findViewById(R.id._prenom);
        _email_ = findViewById(R.id._email);

        final Etudiant etudiant = (Etudiant) getIntent().getSerializableExtra("etudiant");

        loadEtudiant(etudiant);
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Modifié avec succès", Toast.LENGTH_LONG).show();
                updateEtudiant(etudiant);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(updateEtudiant.this);
                builder.setTitle("Etes-vous sûr?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteEtudiant(etudiant);
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private  void loadEtudiant(Etudiant etudiant) {
        _nom_.setText(etudiant.getNom());
        _prenom_.setText(etudiant.getPrenom());
        _email_.setText(etudiant.getEmail());

    }

    private void  updateEtudiant(final  Etudiant etudiant) {
        final  String nom_ = _nom_.getText().toString().trim();
        final  String prenom_ = _prenom_.getText().toString().trim();
        final String email_ = _email_.getText().toString().trim();


        if(nom_.isEmpty()) {
            _nom_.setError("Le nom est obligatoire!");
            _nom_.requestFocus();
            return;
        }

        if(prenom_.isEmpty()) {
            _prenom_.setError("Le prenom est obligatoire!");
            _prenom_.requestFocus();
            return;
        }

        if(email_.isEmpty()) {
            _email_.setError("L'email est obligatoire");
            _email_.requestFocus();
            return;
        }

        class UpdateEtudiant extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                etudiant.setNom(nom_);
                etudiant.setPrenom(prenom_);
                etudiant.setEmail(email_);

                DatabaseClient.getInstance(getApplicationContext()).getEptDB().etudiantDao().update(etudiant);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(updateEtudiant.this, MainActivity.class));
            }
        }
        UpdateEtudiant ue = new UpdateEtudiant();
        ue.execute();

    }

    private void deleteEtudiant(final Etudiant etudiant) {
        class  DeletEtudiant extends  AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getEptDB().etudiantDao().delete(etudiant);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Supprimé!", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(updateEtudiant.this, MainActivity.class));
            }
        }

        DeletEtudiant de = new DeletEtudiant();
        de.execute();
    }
}
