package com.example.mylife;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditBlogDialog extends Dialog {

    private EditText titleEditText;
    private EditText contentEditText;
    private BlogDao blogDao;
    private Blog blog;

    public EditBlogDialog(@NonNull Context context, BlogDao blogDao, Blog blog) {
        super(context);
        this.blogDao = blogDao;
        this.blog = blog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_blog);

        titleEditText = findViewById(R.id.edit_text_title);
        contentEditText = findViewById(R.id.edit_text_content);

        if (blog != null) {
            titleEditText.setText(blog.getTitle());
            contentEditText.setText(blog.getContent());
        }

        Button button = findViewById(R.id.button_submit);
        button.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();

            if (blog == null) {
                blog = new Blog();
            }

            blog.setTitle(title);
            blog.setContent(content);
            blog.setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

            if (blog.getId() == 0) {
                blogDao.addBlog(blog);
            } else {
                blogDao.updateBlog(blog);
            }

            dismiss();
        });
    }
}
