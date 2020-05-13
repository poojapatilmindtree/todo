package com.mindtree.socialcentreapplication.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.mindtree.socialcentreapplication.entity.Room;
import com.mindtree.socialcentreapplication.entity.SocialCentre;

public interface SocialCentreService {
	public String addSocialCentre(SocialCentre centre);

	String assignRoomsToCentre(@Valid int id,@Valid List<Room> rooms) throws CentreNotFoundException;
	
	Map<String, List<Room>> getBlocks();
	
	Set<String> getKeysByRoomNumber(int roomNumber);
	
	String deleteByblockName(String blockName);

	int countOfroomsByBlock();
}
