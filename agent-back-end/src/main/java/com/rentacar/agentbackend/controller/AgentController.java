package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.dto.AgentDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {


    @PostMapping
    public void createAgent(@RequestBody AgentDTO agentDTO) {

    }

    @GetMapping("/{id}")
    public String getSingleAgent(@PathVariable Long id) {
    	return "getSingleAgent endpoint invoked with id: " + id;
    }

    @GetMapping
    public void getAllAgents() {

    }

    @PutMapping("/{id}")
    public void updateAgent(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Long id) {

    }


}
