package com.klu.ss.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.service.*;
import com.klu.ss.model.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService psp;

	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestParam("caption") String caption,
			@RequestParam("image") MultipartFile image, @RequestParam("userId") Integer userId) {
		 try {
			 
		        Post post = psp.createPost(caption, image, userId);
		        return ResponseEntity.ok(post);
		    } catch (Exception e) {
		        e.printStackTrace(); 
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to create post");
		    }
	}

	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts(@RequestParam("profileId") Long profileId) {
		List<Post> posts = psp.getAllPosts(profileId);
		return ResponseEntity.ok(posts);
	}

	@PostMapping("/{postId}/like")
	public ResponseEntity<Void> likePost(@PathVariable Long postId, @RequestParam Long profileId) {
		psp.likePost(postId, profileId);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{postId}/like/{profileId}")
    public ResponseEntity<Void> unlikePost(
            @PathVariable Long postId,
            @PathVariable Long profileId) {
        psp.unlikePost(postId, profileId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long postId) {
        int count = psp.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{postId}/likes/status")
    public ResponseEntity<Boolean> isLikedByProfile(
            @PathVariable Long postId,
            @RequestParam Long profileId) {
        boolean isLiked = psp.isLikedByProfile(postId, profileId);
        return ResponseEntity.ok(isLiked);
    }
    @GetMapping("/home")
    public ResponseEntity<List<Post>> getAllPostsForHome() {
        List<Post> posts = psp.getAllPostsForHome();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = psp.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

}
