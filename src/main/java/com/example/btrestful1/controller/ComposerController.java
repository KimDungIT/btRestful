package com.example.btrestful1.controller;

import com.example.btrestful1.model.Composer;
import com.example.btrestful1.model.SearchOperation;
import com.example.btrestful1.repository.ComposerRes;
import com.example.btrestful1.service.ComposerService;
import com.example.btrestful1.service.MusicService;
import com.example.btrestful1.specification.ComposerSpecificationBuilder;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class ComposerController {

    @Autowired
    ComposerRes composerRes;

    @Autowired
    ComposerService composerService;

    @Autowired
    MusicService musicService;

    private static final Logger LOG = LoggerFactory.getLogger(ComposerController.class);

    //get all
    @RequestMapping(value = "/composers", method = RequestMethod.GET)
    public Page<Composer> listComposer(@PageableDefault(size = 5) Pageable pageable)
    {
        LOG.trace("This is TRACE");
        LOG.debug("This is DEBUG");
        LOG.info("This is INFO");
        LOG.warn("This is WARN");
        LOG.error("This is ERROR");

        return composerService.getAllComposer(pageable);

    }

    //get all music by composerId
    @RequestMapping(value = "/composers/{id}/musics", method = RequestMethod.GET)
    public List listMusicByComposerid(@PathVariable("id") int id)
    {
        return musicService.getAllMusicByComposerId(id);
    }


    //get by id
    @RequestMapping(value = "/composers/{id}", method = RequestMethod.GET)
    public Composer getComposerById(@PathVariable("id") int id) throws Exception {

        return composerService.getById(id);
    }

//    //get by id
//    @RequestMapping(value = "/composers", method = RequestMethod.GET)
//    public Composer getComposerById(@RequestParam("id") int id) throws Exception {
//
//        return composerService.getById(id);
//    }

    //insert composer
    @RequestMapping(value = "/composers", method = RequestMethod.POST)
    public Composer insertComposer(@RequestBody Composer composer) throws Exception {

        composerService.saveComposer(composer);
        return composer;
    }

    //delete composer
    @RequestMapping(value = "/composers/{id}", method = RequestMethod.DELETE)
    public Composer deleteComposer(@PathVariable("id") int id) throws Exception {

        Composer composer = composerService.getById(id);
        composerService.deleteComposerById(id);
        return composer;
    }

    //update composer
    @RequestMapping(value = "/composers/{id}", method = RequestMethod.PUT)
    public Composer updateComposer(@PathVariable("id") int id, @RequestBody Composer composer1) throws Exception {

        if(composerService.getById(id)!=null)
        {
            Composer composer = composerService.getById(id);

            composer.setName(composer1.getName());
            composer.setAge(composer1.getAge());
            composer.setHometown(composer1.getHometown());

            composerService.saveComposer(composer);
            return composer;
        }

        return null;
    }

    @RequestMapping(value = "/composers/spec", method = RequestMethod.GET)
    @ResponseBody
    public List<Composer> findAllBySpecification(@RequestParam(value = "search") String search)
    {
        ComposerSpecificationBuilder builder = new ComposerSpecificationBuilder();

        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);

        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<Composer> spec = builder.build();
        return composerRes.findAll(spec);
    }

    //search age > 60
    @RequestMapping(value = "/composers/search", method = RequestMethod.GET)
    public List<Composer> getComposerByAgeGreatherThan60()
    {
        return composerService.givenMinAge_whenGettingListOfUsers_thenCorrect();
    }

}
