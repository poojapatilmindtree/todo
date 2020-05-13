package com.mindtree.socialcentreapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class SocialCentre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centreId;

	@NotBlank(message = "block name must not be blank")
	private String blockName;

	@JsonIgnoreProperties("socialCentre")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "socialCentre")
	private List<Room> roomlist;

	public SocialCentre(int centreId, @NotBlank(message = "block name must not be blank") String blockName) {
		super();
		this.centreId = centreId;
		this.blockName = blockName;
	}

	public SocialCentre(int centreId, @NotBlank(message = "block name must not be blank") String blockName,
			List<Room> rooms) {
		super();
		this.centreId = centreId;
		this.blockName = blockName;
		this.roomlist = rooms;
	}

	public int getCentreId() {
		return centreId;
	}

	public void setCentreId(int centreId) {
		this.centreId = centreId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public List<Room> getRooms() {
		return roomlist;
	}

	public void setRooms(List<Room> rooms) {
		this.roomlist = rooms;
	}

	public SocialCentre() {
		// TODO Auto-generated constructor stub
	}

}
