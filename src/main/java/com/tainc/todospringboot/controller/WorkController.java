package com.tainc.todospringboot.controller;

import com.tainc.todospringboot.exeptions.WorkExistException;
import com.tainc.todospringboot.exeptions.WorkNotFoundException;
import com.tainc.todospringboot.form.WorkForm;
import com.tainc.todospringboot.utils.Messages;
import com.tainc.todospringboot.model.WorkEntity;
import com.tainc.todospringboot.service.WorkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    // function 3
    // function 4
    // function 5
    // function 6
    // function 7
    // function 8
}