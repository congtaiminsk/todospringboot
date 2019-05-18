package com.tainc.todospringboot.controller;

import com.tainc.todospringboot.model.WorkEntity;
import com.tainc.todospringboot.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    WorkService workService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<WorkEntity>> findAll(@RequestParam("sortBy") String sortBy,
                                                    @RequestParam("order") String order,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("pageSize") int pageSize) {
        Sort sortType = Sort.by(sortBy);
        Pageable pageableWithSort = PageRequest
            .of(page, pageSize,
                order.equals("asc") ? sortType.ascending() : sortType.descending());
        Page<WorkEntity> workPage = workService.findAll(pageableWithSort);
        if (workPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workPage, HttpStatus.OK);
    }
}
