package com.tainc.todospringboot.service;

import com.tainc.todospringboot.model.WorkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkService {

    Page<WorkEntity> findAll(Pageable page);

}
