package com.example.btrestful1.repository;

import com.example.btrestful1.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRes extends JpaRepository<Music, Integer> {

    //get all music by composer_id
    @Query(value = "select * from music where composer_id = ?1", nativeQuery = true)
    List<Music> getAllMusicByComposerId(int id);

    //get all music by composer_id
    List<Music> findAllByComposerId(int id);

    //get list composer_id
    @Query(value = "select distinct composer_id from music", nativeQuery = true)
    List<Integer> getDistinctByComposer_Id2();

}
