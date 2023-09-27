package com.myblog3.service;

import com.myblog3.entity.Post;
import com.myblog3.exception.ResourceNotFound;
import com.myblog3.payload.PostDto;
import com.myblog3.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savePost = postRepository.save(post);
        PostDto dto = mapToDto(savePost);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getPost(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Post> findPost = postRepository.findAll(pageRequest);
        List<Post> postContent = findPost.getContent();
        List<PostDto> postDtos = postContent.stream().map(post->mapToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void updatePost(long id, PostDto postDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("post not found with the is = " + id)
        );
        Post post = mapToEntity(postDto);
        postRepository.save(post);

    }
    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
        
    }
}
