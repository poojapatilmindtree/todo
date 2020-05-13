package com.mindtree.socialcentreapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.socialcentreapplication.dto.CommonResponseDTO;
import com.mindtree.socialcentreapplication.entity.Room;
import com.mindtree.socialcentreapplication.entity.SocialCentre;
import com.mindtree.socialcentreapplication.service.CentreNotFoundException;
import com.mindtree.socialcentreapplication.service.SocialCentreService;

@RestController
public class SocialCentreController {
	@Autowired
	SocialCentreService service;

	@PostMapping("/addcentre")
	public ResponseEntity<?> addCentres(@RequestBody @Valid SocialCentre centre) {
		try {
			return new ResponseEntity<CommonResponseDTO>(
					new CommonResponseDTO(service.addSocialCentre(centre), null, true), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO(e.getMessage(), null, true),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/assignroomstocentre/{id}")
	public ResponseEntity<?> assign(@RequestBody @Valid List<Room> rooms, @PathVariable @Valid int id) throws Exception {
		try {
		return new ResponseEntity<CommonResponseDTO>(
				new CommonResponseDTO(service.assignRoomsToCentre(id, rooms), null, true), HttpStatus.ACCEPTED);}
		catch(CentreNotFoundException e) {
			return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO( null,e.getMessage(), false),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getroomkeysbyrooomnumber/{roomNumber}")
	public ResponseEntity<?> getRoomKeysByRoomNumber(@PathVariable int roomNumber) {
		try {
		return new ResponseEntity<CommonResponseDTO>(
				new CommonResponseDTO(service.getKeysByRoomNumber(roomNumber), null, true), HttpStatus.ACCEPTED);}
		catch(Exception e) {
			return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO(e.getMessage(), null, true),
					HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteblock/{blockName}")
	public ResponseEntity<?> delete(@PathVariable String blockName) {
		try {
		return new ResponseEntity<CommonResponseDTO>(
				new CommonResponseDTO(service.deleteByblockName(blockName), null, true), HttpStatus.ACCEPTED);}
		catch(Exception e) {
			return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO(e.getMessage(), null, true),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/count")
	public ResponseEntity<?> count() {
		try {
		return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO(service.countOfroomsByBlock(), null, true),
				HttpStatus.ACCEPTED);}
		catch(Exception e) {
			return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO(e.getMessage(), null, true),
					HttpStatus.BAD_REQUEST);
		}

	}

	@ResponseStatus
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArguementNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<String, String>();
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;

	}
}
