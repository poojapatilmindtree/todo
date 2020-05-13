package com.mindtree.socialcentreapplication.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.socialcentreapplication.entity.Room;
import com.mindtree.socialcentreapplication.entity.SocialCentre;
import com.mindtree.socialcentreapplication.repository.RoomRepository;
import com.mindtree.socialcentreapplication.repository.SocialCentreRepository;
import com.mindtree.socialcentreapplication.service.CentreNotFoundException;
import com.mindtree.socialcentreapplication.service.SocialCentreService;
@Service
public class SocialCentreServiceImpl implements SocialCentreService{
	SocialCentreRepository socialRepo;
	RoomRepository roomRepo;

	@Autowired
	public SocialCentreServiceImpl(SocialCentreRepository socialRepo, RoomRepository roomRepo) {
		super();
		this.socialRepo = socialRepo;
		this.roomRepo = roomRepo;
	}

	@Override
	public String addSocialCentre(SocialCentre centre) {
		// TODO Auto-generated method stub
		socialRepo.save(centre);
		return "centre added !";
	}

	@Override
	public String assignRoomsToCentre(@Valid int id, @Valid List<Room> rooms) throws CentreNotFoundException {
		SocialCentre centre = socialRepo.findById(id).orElseThrow(()-> new CentreNotFoundException("centre not present!"));
		rooms.forEach(room -> {
			room.setSocialCentre(centre);
		});
		centre.setRooms(rooms);
		socialRepo.save(centre);
		return "rooms added !";
	}

	@Override
	public Map<String, List<Room>> getBlocks() {
		Map<String, List<Room>> map = new HashMap<String, List<Room>>();
		socialRepo.findAll().forEach(block -> {
			List<Room> rooms = block.getRooms();
			map.put(block.getBlockName(), rooms);
		});
		return map;
	}

//	@Override
//	public List<String> getKeysByRoomNumber(int roomNumber) {
//		List<String> keys = new ArrayList<String>();
//		Room room = roomRepo.findByRoomNumber(roomNumber);
//		String s1 = room.getKey1();
//		String s2 = room.getKey2();
//		keys.add(s1);
//		keys.add(s2);
//		return keys;
//	}
	
	

	@Override
	public String deleteByblockName(String blockName) {
		// TODO Auto-generated method stub
		SocialCentre centre= socialRepo.findByBlockName(blockName);
		socialRepo.delete(centre);
		return "deleted";
	}

	@Override
	public int countOfroomsByBlock() {
		return roomRepo.countOfRoomsByBlockName();
	}

	@Override
	public Set<String> getKeysByRoomNumber(int roomNumber) {
		Room room = roomRepo.findByRoomNumber(roomNumber);
		return room.getKeys1();
	}


}
