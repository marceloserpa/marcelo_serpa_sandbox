package com.marceloserpa.toxicproxypoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("characters/{id}")
    public Character findById(@PathVariable("id") long id){
        return characterService.findById(id).orElseThrow(() -> new RuntimeException("Failed"));
    }
}
