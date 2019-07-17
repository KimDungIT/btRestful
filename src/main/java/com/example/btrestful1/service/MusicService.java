package com.example.btrestful1.service;

import com.example.btrestful1.exception.NotDeleteException;
import com.example.btrestful1.model.Music;
import com.example.btrestful1.repository.MusicRes;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MusicService {

    @Autowired
    MusicRes musicRes;

    @Autowired
    ComposerService composerService;

    Logger logger = Logger.getLogger(this.getClass().getName());
    //get all
    public Page<Music> getAllMusic(Pageable pageable)
    {
        logger.info("get all music");
        return musicRes.findAll(pageable);
    }


    //get all music by composerId

    public List getAllMusicByComposerId(int id)
    {
       // Pageable pageable = PageRequest.of(0, 3, Sort.by("title"));
        logger.info("get all music by composerId");
        return musicRes.getAllMusicByComposerId(id);
    }

    //get by id
    public Music getById(int id) {

        logger.info("get music by id");
        return musicRes.findById(id).orElseThrow(()-> new NotDeleteException("ko co"));
    }

    //save
    public Music saveMusic(Music music)
    {
        logger.info("save music");
        return musicRes.save(music);
    }

    //check exist
    public boolean isMusicExist(int id) throws Exception {

        logger.info("check music exist");

        if(getById(id)!=null)
        {

            return true;
        }
        return false;
    }

    //delete by id
    public void deleteMusicById(int id) {
        if(getById(id)!=null)
        {
            logger.info("delete music by id");
            musicRes.deleteById(id);
        }
       else {
           throw new NotDeleteException("Not delete");
        }
    }



}
