package com.aakruth.dao;

import org.springframework.data.jpa.domain.Specification;

import com.aakruth.model.Dealer;
import com.aakruth.model.Dealer_;

public class DealerSpecification {

	static Specification<Dealer> who(char type) {
        return (root, query, cb) -> {
            return cb.equal(root.get(Dealer_.type),type);
        };
    }
}
