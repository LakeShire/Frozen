package com.github.lakeshire.frozen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuhan on 17/9/1.
 */

public class ListEntity<T> {
    public int start;
    public int count;
    public int total;
    public String title;

    @SerializedName("subjects")
    public List<T> list;
}
