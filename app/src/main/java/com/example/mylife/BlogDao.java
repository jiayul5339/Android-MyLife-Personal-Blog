package com.example.mylife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    private BlogDatabaseHelper dbHelper;

    public BlogDao(Context context) {
        dbHelper = new BlogDatabaseHelper(context);
    }

    public void addBlog(Blog blog) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", blog.getTitle());
        values.put("date", blog.getDate());
        values.put("content", blog.getContent());

        db.insert("blogs", null, values);
        db.close();
    }

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();

        String selectQuery = "SELECT * FROM blogs ORDER BY id DESC";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Blog blog = new Blog();
                blog.setId(cursor.getInt(0));
                blog.setTitle(cursor.getString(1));
                blog.setDate(cursor.getString(2));
                blog.setContent(cursor.getString(3));
                blogs.add(blog);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return blogs;
    }

    public void updateBlog(Blog blog) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", blog.getTitle());
        values.put("date", blog.getDate());
        values.put("content", blog.getContent());

        db.update("blogs", values, "id = ?", new String[] { String.valueOf(blog.getId()) });
        db.close();
    }

    public void deleteBlog(Blog blog) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("blogs", "id = ?", new String[] { String.valueOf(blog.getId()) });
        db.close();
    }
}

