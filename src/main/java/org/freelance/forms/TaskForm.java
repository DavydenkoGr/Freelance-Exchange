package org.freelance.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Auxiliary dto which transport information from page to program
 */
public class TaskForm {
    @NotNull
    @Size(min = 2)
    @Getter @Setter private String name;

    @NotNull
    @Getter @Setter private int price;

    @Getter @Setter private String description;
}
