package com.example.ayman.training1;

/**
 * Created by AYMAN on 07/09/2018.
 */

public class Endi
{
    private String post_content;

    public String getPost_content ()
    {
        return post_content;
    }

    public void setPost_content (String post_content)
    {
        this.post_content = post_content;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [post_content = "+post_content+"]";
    }
}

