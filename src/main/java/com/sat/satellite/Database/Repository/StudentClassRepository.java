package com.sat.satellite.Database.Repository;

import com.sat.satellite.Database.Entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
    @Query(value = "SELECT * FROM `demo_1_studentclass` WHERE studentid=?1", nativeQuery = true)
    List<StudentClass> findByStudentID(int studentid);
    @Query(value = "SELECT * FROM `demo_1_studentclass` WHERE classid=?1", nativeQuery = true)
    List<StudentClass> findByClassID(int classid);
    @Query(value = "SELECT * FROM `demo_1_studentclass` WHERE studentid1=?1 AND classid=?2", nativeQuery = true)
    List<StudentClass> findByStudentIDAndClassID(int studentid,int classid);
}
