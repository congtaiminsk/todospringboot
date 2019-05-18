package com.tainc.todospringboot.controller;

import com.tainc.todospringboot.exeptions.WorkExistException;
import com.tainc.todospringboot.exeptions.WorkNotFoundException;
import com.tainc.todospringboot.form.WorkForm;
import com.tainc.todospringboot.model.WorkEntity;
import com.tainc.todospringboot.service.WorkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    WorkService workService;

    /**
     * get works by paging and sorting
     * @param sortBy sort works by field
     * @param order order it
     * @param page current page
     * @param pageSize number of records in page
     * @return page entity if OK, else NO_CONTENT
     */
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

    /**
     * get work by id
     * @param id id of work
     * @return work and status
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<WorkEntity> getById(
        @PathVariable("id") Integer id) {
        Optional<WorkEntity> work = workService.findById(id);
        if (!work.isPresent()) {
            throw new WorkNotFoundException("Work not found: id = " + id);
        }
        return new ResponseEntity<>(work.get(), HttpStatus.OK);
    }

    /**
     * create new work
     * @param workForm form to create
     * @return new work response if success
     */
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<WorkEntity> create(@RequestBody @Valid WorkForm workForm) {
        WorkEntity workEntity = new WorkEntity();
        try {
            BeanUtils.copyProperties(workForm, workEntity);
            workEntity = workService.save(workEntity);
        } catch (DataAccessException ex) {
            throw new WorkExistException("This work is already exist");
        }
        return new ResponseEntity<>(workEntity, HttpStatus.CREATED);
    }

    /**
     * update work
     * @param id id of work
     * @param workForm form to update
     * @return updated work if success
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<WorkEntity> update(@PathVariable("id") Integer id,
                                             @RequestBody @Valid WorkForm workForm) {

        Optional<WorkEntity> currentWork = workService.findById(id);

        if (!currentWork.isPresent()) {
            throw new WorkNotFoundException("Invalid work id : " + id);
        }

        BeanUtils.copyProperties(workForm, currentWork.get());
        try {
            workService.save(currentWork.get());
        } catch (DataAccessException ex) {
            throw new WorkExistException("This work is already exist");
        }

        return new ResponseEntity<>(currentWork.get(), HttpStatus.OK);
    }
}
