package com.sat.satellite.Database.Repository;

import com.sat.satellite.Database.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query(value = "SELECT * FROM demo_1_student WHERE name=?1", nativeQuery = true)
    List<StudentEntity> findByStudentName(String name);
}
