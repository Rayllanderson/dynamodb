package com.rayllanderson.dynamodb.game.controllers;

import com.rayllanderson.dynamodb.game.repository.GameRepository;
import com.rayllanderson.dynamodb.game.responses.FindGameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/games")
public class FindGameController {
    private final GameRepository gameRepository;
    private final Logger log = LoggerFactory.getLogger(FindGameController.class);

    public FindGameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindGameResponse> findById(@PathVariable String id) {
        log.info("Searching game id= {}", id);

        var game = gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        log.info("game found = {}", game);

        return ResponseEntity.ok(FindGameResponse.fromModel(game));
    }
}
