package sn.learnandroid.androidlearning;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EtudiantAdapter extends  RecyclerView.Adapter<EtudiantAdapter.EtudiantView> {

    private Context mCtx;
    private List<Etudiant> etudiantList;
    public EtudiantAdapter(Context mCtx, List<Etudiant> etudiantList) {
        this.mCtx = mCtx;
        this.etudiantList = etudiantList;
    }

    @Override
    public EtudiantView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_students, parent, false);
        return new EtudiantView(view);
    }

    @Override
    public void onBindViewHolder(EtudiantView holder, int position) {
        Etudiant e = etudiantList.get(position);
        holder.textViewNom.setText(e.getNom());
        holder.textViewPrenom.setText(e.getPrenom());
        holder.textViewEmail.setText(e.getEmail());

    }

    @Override
    public int getItemCount() {
        return etudiantList.size();
    }

    class EtudiantView extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewNom, textViewPrenom, textViewEmail;

        public EtudiantView(View itemView) {
            super(itemView);

            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewPrenom = itemView.findViewById(R.id.textViewPrenom);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);

            itemView.setOnClickListener(this);
        }

        @Override
        public  void onClick(View view) {
            Etudiant etudiant = etudiantList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, updateEtudiant.class);
            intent.putExtra("etudiant", etudiant);

            mCtx.startActivity(intent);
        }

    }
}
