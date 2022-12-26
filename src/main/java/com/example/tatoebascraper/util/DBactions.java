package com.example.tatoebascraper.util;

import com.example.tatoebascraper.entity.MakeEntity;
import com.example.tatoebascraper.entity.ModelEntity;
import com.example.tatoebascraper.repository.TurboMakeRepository;
import com.example.tatoebascraper.repository.TurboModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBactions {

    @Autowired
    TurboMakeRepository turboMakeRepository;

    @Autowired
    TurboModelRepository turboModelRepository;

    public void updateMakeTable(MakeEntity makeEntity) {
        if (turboMakeRepository.getByMakeAndMakeId(makeEntity.getMake(), makeEntity.getMakeId()) == null) {
            turboMakeRepository.save(makeEntity);
        }
    }

    public void updateModelTable(ModelEntity modelEntity) {
        if (turboModelRepository.getByModelAndModelIdAndMakeId(modelEntity.getModel(), modelEntity.getModelId(), modelEntity.getMakeId()) == null) {
            turboModelRepository.save(modelEntity);
        }
    }
}
