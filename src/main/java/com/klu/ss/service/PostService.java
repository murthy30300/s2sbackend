package com.klu.ss.service;

import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.*;

import java.util.*;

public interface PostService {
	Post createPost(String caption, MultipartFile image, Integer uid);
	List<Post> getAllPosts(Long prid);
	void likePost(Long pid,Long prid);
	void unlikePost(Long pid, Long prid);
	int getLikeCount(Long pid);
	boolean isLikedByProfile(Long pid,Long prid);
	public List<Post> getAllPostsForHome();
	public List<Post> getPostsByUserId(Integer userId);
}
