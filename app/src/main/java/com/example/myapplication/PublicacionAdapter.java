package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {
    private final List<Publicacion> publicaciones;

    public PublicacionAdapter(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion, parent, false);
        return new PublicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        Publicacion publicacion = publicaciones.get(position);
        holder.titulo.setText(publicacion.getTitulo());
        holder.ubicacion.setText(publicacion.getUbicacion());
        holder.descripcion.setText(publicacion.getDescripcion());
        holder.review.setText(publicacion.getReview());
        holder.foto.setImageResource(publicacion.getFoto()); // Cargar imagen

        // Configurar el OnClickListener
        holder.itemView.setOnClickListener(v -> {
            // Crear un intent para abrir la actividad Info
            Intent intent = new Intent(v.getContext(), Info.class);

            // Pasar los datos de la publicación seleccionada
            intent.putExtra("titulo", publicacion.getTitulo());
            intent.putExtra("ubicacion", publicacion.getUbicacion());
            intent.putExtra("descripcion", publicacion.getDescripcion());
            intent.putExtra("review", publicacion.getReview());
            intent.putExtra("presupuesto", publicacion.getPresupuesto());
            intent.putExtra("grupo", publicacion.getTamañoGrupo());
            intent.putExtra("tipoTurismo", publicacion.getTipoLugar());
            intent.putExtra("imagen", publicacion.getFoto()); // Pasar la imagen como recurso

            // Iniciar la nueva actividad
            v.getContext().startActivity(intent);
        });
    }




    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView ubicacion;
        TextView descripcion;
        TextView review;
        ImageView foto;

        PublicacionViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tvTitulo);
            ubicacion = itemView.findViewById(R.id.location);
            descripcion = itemView.findViewById(R.id.description);
            review = itemView.findViewById(R.id.review);
            foto = itemView.findViewById(R.id.image);
        }
    }
}
