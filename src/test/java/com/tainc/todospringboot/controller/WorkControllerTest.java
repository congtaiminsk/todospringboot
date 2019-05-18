package com.tainc.todospringboot.controller;

import com.tainc.todospringboot.configuration.WebConfig;
import com.tainc.todospringboot.constants.Status;
import com.tainc.todospringboot.filter.CORSFilter;
import com.tainc.todospringboot.model.WorkEntity;
import com.tainc.todospringboot.service.WorkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class WorkControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WorkService workService;

    @InjectMocks
    private WorkController workController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
            .standaloneSetup(workController)
            .addFilters(new CORSFilter())
            .build();
    }
    // ====This is sample UT, must to make all UT for controller, service,..========================
    // ==================================================TAINC======================================
    // =========================================== Get All Works ===================================

    @Test
    public void test_get_all_success() throws Exception {

        LocalDateTime date = LocalDateTime.of(2019, Month.JANUARY, 1, 10, 10, 30);

        List<WorkEntity> workEntities = Arrays.asList(
            new WorkEntity(1, "TODO 1", date, date, Status.DOING),
            new WorkEntity(2, "TODO 2", date, date, Status.COMPLETE));

        Page<WorkEntity> pageWorks = new PageImpl<>(workEntities);

        when(workService.findAll(anyObject())).thenReturn(pageWorks);

        String sortBy = "name";
        String order = "asc";
        String page = "1";
        String pageSize = "2";

        MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                        MediaType.APPLICATION_JSON.getSubtype(),
                                                        Charset
                                                            .forName("utf8"));
        mockMvc.perform(get("/api/works")
                            .param("sortBy", sortBy)
                            .param("order", order)
                            .param("page", page)
                            .param("pageSize", pageSize).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.content", hasSize(2)))
            .andExpect(jsonPath("$.content[0].id", is(1)))
            .andExpect(jsonPath("$.content[0].name", is("TODO 1")))
            .andExpect(jsonPath("$.content[0].startingDate[0]", is(2019)))
            .andExpect(jsonPath("$.content[0].startingDate[1]", is(1)))
            .andExpect(jsonPath("$.content[0].startingDate[2]", is(1)))
            .andExpect(jsonPath("$.content[0].startingDate[3]", is(10)))
            .andExpect(jsonPath("$.content[0].startingDate[4]", is(10)))
            .andExpect(jsonPath("$.content[0].startingDate[5]", is(30)))
            .andExpect(jsonPath("$.content[0].endingDate[0]", is(2019)))
            .andExpect(jsonPath("$.content[0].endingDate[1]", is(1)))
            .andExpect(jsonPath("$.content[0].endingDate[2]", is(1)))
            .andExpect(jsonPath("$.content[0].endingDate[3]", is(10)))
            .andExpect(jsonPath("$.content[0].endingDate[4]", is(10)))
            .andExpect(jsonPath("$.content[0].endingDate[5]", is(30)))
            .andExpect(jsonPath("$.content[0].status", is("DOING")))
            .andExpect(jsonPath("$.content[1].id", is(2)))
            .andExpect(jsonPath("$.content[1].name", is("TODO 2")))
            .andExpect(jsonPath("$.content[1].startingDate[0]", is(2019)))
            .andExpect(jsonPath("$.content[1].startingDate[1]", is(1)))
            .andExpect(jsonPath("$.content[1].startingDate[2]", is(1)))
            .andExpect(jsonPath("$.content[1].startingDate[3]", is(10)))
            .andExpect(jsonPath("$.content[1].startingDate[4]", is(10)))
            .andExpect(jsonPath("$.content[1].startingDate[5]", is(30)))
            .andExpect(jsonPath("$.content[1].endingDate[0]", is(2019)))
            .andExpect(jsonPath("$.content[1].endingDate[1]", is(1)))
            .andExpect(jsonPath("$.content[1].endingDate[2]", is(1)))
            .andExpect(jsonPath("$.content[1].endingDate[3]", is(10)))
            .andExpect(jsonPath("$.content[1].endingDate[4]", is(10)))
            .andExpect(jsonPath("$.content[1].endingDate[5]", is(30)))
            .andExpect(jsonPath("$.content[1].status", is("COMPLETE")));
        verify(workService, times(1)).findAll(anyObject());
        verifyNoMoreInteractions(workService);
    }

    @Test
    public void test_get_all_no_content() throws Exception {
        List<WorkEntity> workEntities = Arrays.asList();

        Page<WorkEntity> pageWorks = new PageImpl<>(workEntities);

        when(workService.findAll(anyObject())).thenReturn(pageWorks);

        String sortBy = "name";
        String order = "asc";
        String page = "1";
        String pageSize = "2";

        mockMvc.perform(get("/api/works")
                            .param("sortBy", sortBy)
                            .param("order", order)
                            .param("page", page)
                            .param("pageSize", pageSize).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        verify(workService, times(1)).findAll(anyObject());
        verifyNoMoreInteractions(workService);
    }

}
