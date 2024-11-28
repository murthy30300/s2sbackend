package com.klu.ss.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.klu.ss.model.Profile;
import com.klu.ss.model.User;
public interface ProfileRepo extends JpaRepository<Profile, Long> {
	@Query("SELECT p FROM Profile p JOIN p.user u WHERE u.uid = :uid")
	Optional<Profile> getByUid(@Param("uid") long uid);
	
	@Query("SELECT p FROM Profile p JOIN p.user u WHERE u.uid = :userId")
   Profile findByUserId(@Param("userId") int userId);
	
	Profile findByUserUsername(String username);
	
	@Query("SELECT p FROM Profile p JOIN p.user u WHERE u.uid = :userId")
    Profile findByUserId(@Param("userId") long userId);
	
	 Optional<Profile> findByUser(User user);
	
}
