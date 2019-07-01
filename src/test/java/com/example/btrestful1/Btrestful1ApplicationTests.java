package com.example.btrestful1;

import com.example.btrestful1.model.Composer;
import com.example.btrestful1.model.SearchCriteria;
import com.example.btrestful1.model.SearchOperation;
import com.example.btrestful1.repository.ComposerRes;
import com.example.btrestful1.specification.ComposerSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Btrestful1ApplicationTests {

    @Autowired
    private ComposerRes composerRes;
    @Test
    public void contextLoads() {
    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        ComposerSpecification spec = new ComposerSpecification(new SearchCriteria("age", SearchOperation.GREATER_THAN, 50));

        List<Composer> results = composerRes.findAll(Specification.where(spec));

    }

}
