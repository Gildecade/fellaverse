package com.fellaverse.backend.delay;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskBase {
    // any unique String
    protected String identifier;
}
