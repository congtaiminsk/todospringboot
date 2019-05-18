package com.tainc.todospringboot.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tainc.todospringboot.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkForm {

    @NotEmpty(message = "Name must be not empty")
    private String name;

    @NotNull(message = "Starting Date must be not null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startingDate;

    @NotNull(message = "Ending Date must be not null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endingDate;

    @NotNull(message = "Status must be not null")
    @Enumerated(EnumType.STRING)
    private Status status;
}
