package com.example.btrestful1.repository;

import com.example.btrestful1.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComposerRes extends JpaRepository<Composer, Integer>, JpaSpecificationExecutor<Composer> {

    //lay danh sach cac Composer co ten chua "name" vaf tuoi lon hon "age"
    List<Composer> findAllByNameContainingAndAgeGreaterThan(String name, int age);

    //lay danh sach cac composer co id thuoc list id
    List<Composer> findAllByIdIn(List<Integer> id);

    //Composer: Object
    @Query("select c from Composer c where c.age > 40")
    List<Composer> getListComposer();

    //composer: table
    @Query(value = "select * from composer where age > 40", nativeQuery = true)
    List<Composer> getListComposer2();



}
