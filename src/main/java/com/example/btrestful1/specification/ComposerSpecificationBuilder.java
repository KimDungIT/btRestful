package com.example.btrestful1.specification;

import com.example.btrestful1.model.Composer;
import com.example.btrestful1.model.SearchCriteria;
import com.example.btrestful1.model.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComposerSpecificationBuilder {

    private List<SearchCriteria> params;


    public ComposerSpecificationBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Composer> build()
    {
        if(params.size() == 0)
        {
            return null;
        }

        Specification result = new ComposerSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ComposerSpecification(params.get(i)))
                    : Specification.where(result).and(new ComposerSpecification(params.get(i)));
        }

        return result;

    }

}
