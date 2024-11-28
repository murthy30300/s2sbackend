package com.klu.ss.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klu.ss.model.LogisticsProvider;

public interface LogisticsProviderRepository extends JpaRepository<LogisticsProvider, Integer> {

	@Query("SELECT lp FROM LogisticsProvider lp "
			+ "WHERE FUNCTION('distance', lp.latitude, lp.longitude, :latitude, :longitude) <= :radius")
	List<LogisticsProvider> findNearbyProviders(@Param("latitude") Double latitude,
			@Param("longitude") Double longitude, @Param("radius") double radius);
}
