package com.url.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.Model.UrlMapping;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
	
	Optional<UrlMapping> findByShortCode(String shortCode);

}
