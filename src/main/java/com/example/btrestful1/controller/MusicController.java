package com.example.btrestful1.controller;

import com.example.btrestful1.model.Music;
import com.example.btrestful1.repository.MusicRes;
import com.example.btrestful1.service.ComposerService;
import com.example.btrestful1.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
public class MusicController {

    @Autowired
    MusicService musicService;

    @Autowired
    ComposerService composerService;

    @Autowired
    MusicRes musicRes;

    //get all
    @RequestMapping(value = "/musics", method = RequestMethod.GET)
    public Page<Music> listMusic(@PageableDefault Pageable pageable)
    {
        return musicService.getAllMusic(pageable);
    }

    //get by id
    @RequestMapping(value = "/musics/{id}", method = RequestMethod.GET)
    public Music getMusicById(@PathVariable("id") int id) throws Exception {
        return musicService.getById(id);
    }

    //insert music
    @RequestMapping(value = "/musics", method = RequestMethod.POST)
    public Music insertMusic(@RequestBody Music music) throws Exception {
        if(composerService.getById(music.getComposer().getId())!=null)
        {
            return musicService.saveMusic(music);
        }
        return null;
    }

    //delete music
    @RequestMapping(value = "/musics/{id}", method = RequestMethod.DELETE)
    public Music deleteMusic(@PathVariable("id") int id) throws Exception {

       musicService.deleteMusicById(id);

        return  musicService.getById(id);

    }

    //update music
    @RequestMapping(value = "/musics/{id}", method = RequestMethod.PUT)
    public Music updateMusic(@PathVariable("id") int id, @RequestBody Music music) throws Exception {

        if(musicService.isMusicExist(id))
        {
            Music music1 = musicService.getById(id);
            music1.setTitle(music.getTitle());
            music1.setYear(music.getYear());
            music1.setComposer(music.getComposer());

            musicService.saveMusic(music1);
            return music1;
        }

        return null;
    }

}
