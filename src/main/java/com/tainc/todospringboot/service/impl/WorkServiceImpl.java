package com.tainc.todospringboot.service.impl;

import com.tainc.todospringboot.model.WorkEntity;
import com.tainc.todospringboot.repository.WorkRepository;
import com.tainc.todospringboot.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public Page<WorkEntity> findAll(Pageable page) {
        return workRepository.findAll(page);
    }


}
