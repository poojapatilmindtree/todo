package com.mindtree.socialcentreapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.socialcentreapplication.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	
	public Room findByRoomNumber(int roomNumber);

	@Query(value = "select count(*) Room from social_centre s inner join Rooms r on s.centre_id=r.social_centre_centre_id where r.is_occupied = false group by s.block_name", nativeQuery = true)
	int countOfRoomsByBlockName();
}
