package com.eventService;

import org.springframework.stereotype.Service;

@Service
public class EventService {

	public String getText(){
		return "coucou depuis l'autre côté!";
	}
}