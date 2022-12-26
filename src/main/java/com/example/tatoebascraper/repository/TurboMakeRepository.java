package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.MakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurboMakeRepository extends JpaRepository<MakeEntity, Long> {
    @Override
    List<MakeEntity> findAll();

    MakeEntity getByMakeIgnoreCase(String make);

    MakeEntity getByMakeAndMakeId(String make, int makeId);

    MakeEntity getMakeEntityByIdAndAndMakeId(Long id, Long makeID);
}
