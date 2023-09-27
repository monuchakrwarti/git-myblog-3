package com.myblog3.controller;

import com.myblog3.entity.Post;
import com.myblog3.payload.PostDto;
import com.myblog3.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //http://localhost:8080/api/post
    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto){
        PostDto dto = postService.savePost(postDto);

        return new ResponseEntity<PostDto>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")long id){
        postService.deletePost(id);
        return new ResponseEntity<String>("delete this record", HttpStatus.OK);
    }

    //http://localhost:8080/api/post?pageNo=0&pageSize=3
    @GetMapping
    public List<PostDto> getPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize
    ){
        List<PostDto> postDtos = postService.getPost(pageNo, pageSize);
        return postDtos;
    }

    //http://localhost:8080/api/post
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id")long id, @RequestBody PostDto postDto){
        postService.updatePost(id, postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
}
