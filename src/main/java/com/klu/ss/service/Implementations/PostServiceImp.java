package com.klu.ss.service.Implementations;

import com.klu.ss.repository.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.klu.ss.model.*;
import com.klu.ss.service.*;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImp implements PostService {
	@Autowired
	private PostRepo postrep;
	@Autowired
	private UserRepo urp;
	@Autowired
	private ProfileRepo porp;
	@Autowired
	private LikeRepo lrp;
	// @Autowired
	// private FileStorage fs;
	private static final long MAX_SIZE = 5 * 1024 * 1024;
	@Autowired
	private Cloudinary cloudinary;

	@Transactional
	public Post createPost(String caption, MultipartFile image, Long uid) {
		User u = urp.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
		String img = null;

		if (image != null && !image.isEmpty()) {
			if (image.getSize() > MAX_SIZE) {
				throw new IllegalArgumentException("Profile picture is too large");
			}

			Map<?, ?> uploadResult = null;
			try {
				uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = (String) uploadResult.get("url");
		}
		;
		Post post = new Post();
		post.setUser(u);
		post.setCaption(caption);
		post.setImageUrl(img);
		post.setCreatedAt(LocalDateTime.now());
		post.setUpdatedAt(LocalDateTime.now());
		// post.setPostType("default_value");
		post.setActive(true);
		return postrep.save(post);
	}

	public List<Post> getAllPosts(Long prid) {
		List<Post> posts = postrep.findAllActivePosts();
		return posts.stream().map(post -> {
			post.setLikes(getLikeCount(post.getPid())); // Change to getPid()
			post.setLiked(isLikedByProfile(post.getPid(), prid)); // Change to getPid()
			return post;
		}).collect(Collectors.toList());
	}

	@Override
	public void likePost(Long pid, Long prid) {
		Post post = postrep.findById(pid).orElseThrow(() -> new RuntimeException("Post not found"));
		Profile profile = porp.findById(prid).orElseThrow(() -> new RuntimeException("Profile not found"));
		if (!isLikedByProfile(pid, prid)) {
			Like like = new Like();
			like.setPost(post);
			like.setProfile(profile);
			lrp.save(like);
		}

	}

	@Override
	public void unlikePost(Long pid, Long prid) {
		Post post = postrep.findById(pid).orElseThrow(() -> new RuntimeException("Post not found"));
		Profile profile = porp.findById(prid).orElseThrow(() -> new RuntimeException("Profile not found"));

		lrp.findByPostAndProfile(post, profile).ifPresent(like -> lrp.delete(like));

	}
	@Override
	public int getLikeCount(Long pid) {
        return postrep.getLikeCount(pid);
	}
	@Override
	public boolean isLikedByProfile(Long pid, Long prid) {
        return postrep.isLikedByProfile(pid, prid);
	}
	@Override
	public List<Post> getAllPostsForHome() {    
	    return postrep.findAllActivePosts(); 
	}
    public List<Post> getPostsByUserId(Long userId) {
        return postrep.findByUserId(userId);
    }

	



}
