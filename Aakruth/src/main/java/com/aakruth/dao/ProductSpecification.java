package com.aakruth.dao;

import org.springframework.data.jpa.domain.Specification;

import com.aakruth.model.PrdTbl;
import com.aakruth.model.PrdTbl_;

public class ProductSpecification {

	static Specification<PrdTbl> isLive(char sta) {
        return (root, query, cb) -> {
            return cb.equal(root.get(PrdTbl_.sta), sta);
        };
    }
}
