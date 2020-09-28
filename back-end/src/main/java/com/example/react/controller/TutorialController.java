package com.example.react.controller;

import com.example.react.exception.AppException;
import com.example.react.model.Tutorial;
import com.example.react.payload.response.MessageResponse;
import com.example.react.service.tutorial.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private ITutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
        try {
            List<Tutorial> tutorials = tutorialService.findByTitleContaining(title);
            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (AppException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id){
        Optional<Tutorial> tutorial = tutorialService.findById(id);
        return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<MessageResponse> createTutorial(@RequestBody Tutorial tutorial){
        tutorialService.save(tutorial);
        return new ResponseEntity<>(new MessageResponse("Create tutorial successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/tutorials")
    public ResponseEntity<MessageResponse> updateTutorial(@RequestBody Tutorial tutorial){
        tutorialService.save(tutorial);
        return new ResponseEntity<>(new MessageResponse("Update tutorial successfully!"), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<MessageResponse> deleteTutorialById(@PathVariable("id") Long id){
//        try {
            tutorialService.deleteById(id);
            return new ResponseEntity<>(new MessageResponse("Delete tutorial successfully!"), HttpStatus.NO_CONTENT);
//        } catch (AppException e){
//            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<MessageResponse> deleteAllTutorials(){
//        try {
            tutorialService.deleteAllTutorials();
            return new ResponseEntity<>(new MessageResponse("Delete all tutorials successfully!"), HttpStatus.NO_CONTENT);
//        }catch (AppException e){
//            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<?> findTutorialsByPublished(){
        try {
            List<Tutorial> tutorials = tutorialService.findByPublished(true);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }catch (AppException e){
            return new ResponseEntity<>(new MessageResponse("Error: Unauthorized!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
