package com.example.tatoebascraper.service;

import com.example.tatoebascraper.entity.MakeEntity;

import java.io.IOException;
import java.util.List;

public interface MakeService {

    void updateMakes() throws IOException;

    List<MakeEntity> getMakeList();

    MakeEntity getMakeByMakeName(String makeName);

}
