package org.yurov.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yurov.services.MazeServiceImpl;

import java.util.Objects;

@RestController
@RequestMapping("/api/mazes")
public class MazeController {

    private final MazeServiceImpl mazeService;

    @Autowired
    public MazeController(MazeServiceImpl mazeService) {
        this.mazeService = mazeService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Integer[][] maze) {
        mazeService.create(maze);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/random")
    public ResponseEntity<?> createRandom(@RequestBody Integer[] array) {
        mazeService.createRandom(array);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/reade/{index}")
    public ResponseEntity<Integer[][]> findPath(@PathVariable("index") int index) {
        final Integer[][] maze = mazeService.read(index);

        if (maze == null || maze.length == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(maze, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{index}")
    public ResponseEntity<String> updateCells(@RequestBody int[] point,
                                              @PathVariable("index") int index) {
        mazeService.update(point, index);
        return ResponseEntity.ok("Point in maze updated successfully.");
    }

    @DeleteMapping(value = "/delete/{index}")
    public ResponseEntity<String> delete(@PathVariable("index") int index) {
        mazeService.delete(index);
        return ResponseEntity.ok("Maze in " + index + " was deleted");
    }

}
