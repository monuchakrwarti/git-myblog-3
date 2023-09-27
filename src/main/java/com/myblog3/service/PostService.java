package com.myblog3.service;

import com.myblog3.entity.Post;
import com.myblog3.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);

    void deletePost(long id);

    List<PostDto> getPost(int pageNo, int pageSize);

    void updatePost(long id, PostDto postDto);
}
