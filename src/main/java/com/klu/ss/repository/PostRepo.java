package com.klu.ss.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.Post;
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
	@Query("SELECT p FROM Post p WHERE p.isActive = true ORDER BY p.createdAt DESC")
	List<Post> findAllActivePosts();

	@Query("SELECT COUNT(l) FROM Like l WHERE l.post.pid = :postId")
	int getLikeCount(@Param("postId") Long postId);

	@Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Like l WHERE l.post.pid = :postId AND l.profile.prid = :profileId")
	boolean isLikedByProfile(@Param("postId") Long postId, @Param("profileId") Long profileId);
	 @Query("SELECT p FROM Post p WHERE p.user.uid = :userId")
	List<Post> findByUserId(Integer userId);
	 

}
