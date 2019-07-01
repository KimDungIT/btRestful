package com.example.btrestful1.service;

import com.example.btrestful1.exception.NotDeleteException;
import com.example.btrestful1.model.Music;
import com.example.btrestful1.repository.MusicRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    MusicRes musicRes;

    @Autowired
    ComposerService composerService;


    //get all
    public Page<Music> getAllMusic(Pageable pageable)
    {
        return musicRes.findAll(pageable);
    }


    //get all music by composerId

    public List getAllMusicByComposerId(int id)
    {
       // Pageable pageable = PageRequest.of(0, 3, Sort.by("title"));
        return musicRes.getAllMusicByComposerId(id);
    }

    //get by id
    public Music getById(int id) {
        return musicRes.findById(id).orElseThrow(()-> new NotDeleteException("ko co"));
    }

    //save
    public Music saveMusic(Music music)
    {

        return musicRes.save(music);
    }

    //check exist
    public boolean isMusicExist(int id) throws Exception {
        if(getById(id)!=null)
        {
            return true;
        }
        return false;
    }

    //delete by id
    public void deleteMusicById(int id)
    {
        musicRes.deleteById(id);
    }



}
