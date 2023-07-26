package com.example.mylife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.BlogViewHolder> {

    private static List<Blog> blogs;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_row, parent, false);
        return new BlogViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogs.get(position);
        holder.titleTextView.setText(blog.getTitle());
        holder.dateTextView.setText(blog.getDate());
        holder.contentTextView.setText(blog.getContent());
    }

    @Override
    public int getItemCount() {
        return blogs != null ? blogs.size() : 0;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Blog blog);
        void onDeleteClick(Blog blog);
    }

    static class BlogViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView contentTextView;
        ImageButton editButton;
        ImageButton deleteButton;

        BlogViewHolder(View view, OnItemClickListener listener) {
            super(view);
            titleTextView = view.findViewById(R.id.text_view_title);
            dateTextView = view.findViewById(R.id.text_view_date);
            contentTextView = view.findViewById(R.id.text_view_content);
            editButton = view.findViewById(R.id.button_edit);
            deleteButton = view.findViewById(R.id.button_delete);

            editButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(blogs.get(position));
                }
            });

            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(blogs.get(position));
                }
            });
        }
    }
}


