package com.example.tatoebascraper.service;

import com.example.tatoebascraper.entity.ModelEntity;

import java.io.IOException;
import java.util.List;

public interface ModelService {

    void updateModels() throws IOException;

    List<ModelEntity> getModelList(int makeId);

    ModelEntity getModelByModelName(String modelName);

}
