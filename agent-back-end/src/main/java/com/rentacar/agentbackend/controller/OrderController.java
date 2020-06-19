package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.dto.ReservationDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@PostMapping()
	public String createOrder(@RequestBody ReservationDTO newReservation) {
		return "createOrder endpoint invoked.";
	}
	
	@GetMapping()
	public String getAllOrders() {
		return "getAllOrders endpoint invoked.";
	}
	
	@GetMapping("/{id}")
	public String getOrder(@PathVariable Long id) {
		return "getOrder endpoint invoked with id: " + id;
	}
	
	@PutMapping("/{id}")
	public String updateOrder(@PathVariable Long id, @RequestBody ReservationDTO newReservation) {
		return "updateOrder endpoint invoked with id: " + id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteOrder(@PathVariable Long id) {
		return "deleteOrder endpoint invoked with id: " + id;
	}
	
	@GetMapping("/users/{id}") //Vraca sve rezervacije od datog korisnika
	public String getOrdersFromUser(@PathVariable Long id) {
		return "getOrdersFromUser endpoint invoked with id: " + id;
	}
	
	@PostMapping("/internal") //Kreira internu rezervaciju koja je automatski prihvacena
	public String createInternalOrder(@RequestBody ReservationDTO newReservation) {
		return "createInternalOrder endpoint invoked.";
	}
	
	@PutMapping("/{id}/respond") //Prihvata ili odbija rezervaciju sa datim id-om
	public String respondToOrder(@PathVariable Long id, @RequestBody boolean response) {
		String retVal = "respondToOrder endpoint invoked with response: ";
		if(response) {
			retVal += "Accepted.";
		}else {
			retVal += "Declined.";
		}
		return retVal;
	}
	
	@PutMapping("/{id}/cancel") //Korisnik otkazuje sam svoju rezervaciju sa datim id-om
	public String cancelOrder(@PathVariable Long id) {
		return "cancelOrder endpoint invoked with id: " + id;
	}
	
}
