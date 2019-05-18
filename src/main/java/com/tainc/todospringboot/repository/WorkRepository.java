package com.tainc.todospringboot.repository;

import com.tainc.todospringboot.model.WorkEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends PagingAndSortingRepository<WorkEntity, Integer> {

}
