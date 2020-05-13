package com.mindtree.socialcentreapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.socialcentreapplication.entity.SocialCentre;
@Repository
public interface SocialCentreRepository extends JpaRepository<SocialCentre, Integer> {
	
	public SocialCentre findByBlockName(String blockName);

}
