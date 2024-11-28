package com.klu.ss.repository;

import com.klu.ss.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface LikeRepo extends JpaRepository<Like, Long>{
	 Optional<Like> findByPostAndProfile(Post post, Profile profile);
	    void deleteByPostAndProfile(Post post, Profile profile);

}
