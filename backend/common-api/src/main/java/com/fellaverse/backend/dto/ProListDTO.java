package com.fellaverse.backend.dto;

import com.fellaverse.backend.annotation.EnumString;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

@Data
@Accessors(chain = true)
public class ProListDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "User ID cannot be null")
    private Long id;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "title cannot be blank")
    @Size(max = 60)
    private String title;

    @NotNull(message = "time cannot be blank")
    private Instant description;

    private String extra;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "content cannot be blank")
    private String content;
}
