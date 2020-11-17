package mx.uv.fiee.iinf.tyam.API.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import mx.uv.fiee.iinf.tyam.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<String> arrayList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(arrayList.get(position), arrayList.get(position), arrayList.get(position));
    }
    public Adapter(ArrayList<String> arrayList){

        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView text1;
        private TextView text2;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = itemView.findViewById(R.id.text1);
            text1 = itemView.findViewById(R.id.sw_name);
            text2 = itemView.findViewById(R.id.text2);

        }
        public void setData(String title, String text1, String text2 ) {
            this.name.setText(title);
            this.text1.setText(text1);
            this.text2.setText(text2);
        }
    }
}

