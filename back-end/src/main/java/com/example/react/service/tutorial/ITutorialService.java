package com.example.react.service.tutorial;

import com.example.react.model.Tutorial;
import com.example.react.service.IService;

import java.util.List;

public interface ITutorialService extends IService<Tutorial> {
    List<Tutorial> findByPublished(Boolean published);

    List<Tutorial> findByTitleContaining(String title);

    void deleteAllTutorials();
}
