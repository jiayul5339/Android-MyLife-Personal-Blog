package com.example.mylife;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BlogDao blogDao;
    private BlogsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BlogsAdapter();
        recyclerView.setAdapter(adapter);

        blogDao = new BlogDao(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            showEditBlogDialog(null);
        });

        loadBlogs();
    }

    private void loadBlogs() {
        adapter.setBlogs(blogDao.getAllBlogs());

        adapter.setOnItemClickListener(new BlogsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Blog blog) {
                showEditBlogDialog(blog);
            }

            @Override
            public void onDeleteClick(Blog blog) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Are you sure you want to delete this blog?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                blogDao.deleteBlog(blog);
                                dialog.dismiss();
                                loadBlogs();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void showEditBlogDialog(Blog blog) {
        EditBlogDialog dialog = new EditBlogDialog(this, blogDao, blog);
        dialog.setOnDismissListener(dialogInterface -> loadBlogs());
        dialog.show();
    }
}

