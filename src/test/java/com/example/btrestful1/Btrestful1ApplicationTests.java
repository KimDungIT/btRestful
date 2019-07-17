package com.example.btrestful1;

import com.example.btrestful1.model.Composer;
import com.example.btrestful1.model.SearchCriteria;
import com.example.btrestful1.model.SearchOperation;
import com.example.btrestful1.repository.ComposerRes;
import com.example.btrestful1.specification.ComposerSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
@RestController
public class Btrestful1ApplicationTests {

    @Autowired
    private ComposerRes composerRes;
    @Test
    public void contextLoads() {

    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        ComposerSpecification spec = new ComposerSpecification(new SearchCriteria("age", SearchOperation.GREATER_THAN, 60));

        ComposerSpecification spec2 = new ComposerSpecification(new SearchCriteria("name", SearchOperation.STARTS_WITH,"huy" ));

        List<Composer> results = composerRes.findAll(Specification.where(spec).and(spec2));


    }






}
