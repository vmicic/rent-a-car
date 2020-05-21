package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.dto.AgentDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {


    @PostMapping
    public void createAgent(@RequestBody AgentDTO agentDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleAgent(@PathVariable Long id) {

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
