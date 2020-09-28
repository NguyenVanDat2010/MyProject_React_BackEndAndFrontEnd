package com.example.react.service.tutorial;

import com.example.react.exception.AppException;
import com.example.react.model.Tutorial;
import com.example.react.repository.ITutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService implements ITutorialService{
    @Autowired
    private ITutorialRepository tutorialRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tutorial> findById(Long id) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        if (!tutorial.isPresent()){
            throw new AppException("This tutorial is not found. Pls try again!");
        }
        return tutorial;
    }

    @Override
    public Tutorial save(Tutorial tutorial) {
        if (tutorial == null){
            throw new AppException("Tutorial can not be null!");
        }
        return tutorialRepository.save(tutorial);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        if (!tutorial.isPresent()){
            throw new AppException("Can't delete this tutorial!");
        }
        tutorialRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findByPublished(Boolean published) {
        return tutorialRepository.findByPublished(published);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findByTitleContaining(String title) {
        List<Tutorial> tutorials = new ArrayList<>();
        if (title == null){
            tutorialRepository.findAll().forEach(tutorials::add);
//                tutorials.addAll(tutorialService.findAll());
        }else {
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }
        return tutorials;
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }
}
