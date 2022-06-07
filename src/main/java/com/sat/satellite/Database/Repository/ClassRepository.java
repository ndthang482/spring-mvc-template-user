package com.sat.satellite.Database.Repository;

import com.sat.satellite.Database.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
    @Query(value = "SELECT * FROM demo_1_class WHERE name=?1", nativeQuery = true)
    List<ClassEntity> findByClassName(String name);
}
