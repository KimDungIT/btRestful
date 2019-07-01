package com.example.btrestful1.service;

import com.example.btrestful1.exception.NotDeleteException;
import com.example.btrestful1.exception.NotFoundException;
import com.example.btrestful1.model.Composer;
import com.example.btrestful1.model.SearchCriteria;
import com.example.btrestful1.model.SearchOperation;
import com.example.btrestful1.repository.ComposerRes;
import com.example.btrestful1.repository.MusicRes;
import com.example.btrestful1.specification.ComposerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposerService {
    @Autowired
    ComposerRes composerRes;
    @Autowired
    MusicRes musicRes;

    //get all

   public Page<Composer> getAllComposer(Pageable pageable)
    {
givenMinAge_whenGettingListOfUsers_thenCorrect();
       return composerRes.findAll(pageable);
    }


    //get by id
    public Composer getById(int id) {

        return composerRes.findById(id).orElseThrow(() -> new NotFoundException("ko co "));
                //.orElse(new Composer(000000, "Ko co", 00000, "ko co"));
    }

    //save
    public Composer saveComposer(Composer composer)
    {
        return composerRes.save(composer);
    }


    //delete composer
    public void deleteComposerById(int id) throws Exception {

        boolean check = true;
       //Composer composer = composerRes.findById(id).orElseThrow(()->new NotDeleteException("ko xoa duoc"));
        List<Integer> listId = musicRes.getDistinctByComposer_Id2();
        for(int i =0 ;i<listId.size();i++)
        {
            if(listId.get(i).equals(id))
            {
                check = false;
            }
        }
        if(check==true)
        {
            composerRes.deleteById(id);
        }
        else {
            throw new NotDeleteException("Ko xoa duoc");
        }

    }

   public List givenMinAge_whenGettingListOfUsers_thenCorrect()
   {
       ComposerSpecification spec = new ComposerSpecification(new SearchCriteria("age", SearchOperation.GREATER_THAN, "50"));

       List<Composer> results = composerRes.findAll(Specification.where(spec));

       return results;

   }



}
