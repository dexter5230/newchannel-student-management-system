package com.newchannel.jsonplaceholder;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "jsonplaceholder",
             url = "https://jsonplaceholder.typicode.com/")
public interface JSONPlaceHolderClient {
    @GetMapping(path = "/posts")
    List<Post> getPosts();

    @GetMapping(path = "/posts/{userId}")
    Post getPostByUserId(@PathVariable("userId") Integer userId);
}
