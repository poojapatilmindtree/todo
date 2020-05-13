package com.mindtree.socialcentreapplication.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;

	@Column(unique = true)
	@Min(value = 1, message = "room number must be positive")
	private int roomNumber;

//	@Column(unique = true)
//	private String key1;
//
//	@Column(unique = true)
//	private String key2;

//	@Column(name="keys")
//	@ElementCollection(targetClass=String.class)
//	Set<String> keys= new HashSet<String>();
	
	
	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name="roomId"))
    @Column(name = "keys_u",unique = true)
	@NotEmpty(message = "keys cannot be empty")
	@Size(min = 2, max = 2, message = "size must be 2")
	Set<String> keys1 = new HashSet<String>();

	@NotNull(message = "isoccupied cannot be null")
	private boolean isOccupied;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	SocialCentre socialCentre;

	public Room(int roomId, @Min(value = 1, message = "room number must be positive") int roomNumber,
			@NotEmpty(message = "keys cannot be empty") @Size(min = 2, max = 2, message = "size must be 2") Set<String> keys1,
			@NotNull(message = "isoccupied cannot be null") boolean isOccupied) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.keys1 = keys1;
		this.isOccupied = isOccupied;
	}

	public Room(int roomId, @Min(value = 1, message = "room number must be positive") int roomNumber,
			@NotEmpty(message = "keys cannot be empty") @Size(min = 2, max = 2, message = "size must be 2") Set<String> keys1,
			@NotNull(message = "isoccupied cannot be null") boolean isOccupied, SocialCentre socialCentre) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.keys1 = keys1;
		this.isOccupied = isOccupied;
		this.socialCentre = socialCentre;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Set<String> getKeys1() {
		return keys1;
	}

	public void setKeys1(Set<String> keys1) {
		this.keys1 = keys1;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public SocialCentre getSocialCentre() {
		return socialCentre;
	}

	public void setSocialCentre(SocialCentre socialCentre) {
		this.socialCentre = socialCentre;
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}

}
