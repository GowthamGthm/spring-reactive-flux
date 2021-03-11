package dev.bluvolve.reactive.courseservice.course.commands;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using= LegacyUuidDeserializer.class)
public class CreateCourse {
	
    @NotNull
    @Size(min = 5, max = 25, message = "The title must be between 5 and 25 characters long.")
    private String title;

    @Size(max = 250, message = "The description must be a maximum of 250 characters long.")
    private String description;

    @NotNull
//    @JsonSerialize(using = LegacyUuidSerializer.class)
//    @JsonDeserialize(using = LegacyUuidDeserializer.class)
    private UUID categoryId;

    @NotNull
//    @JsonSerialize(using = LegacyUuidSerializer.class)
//    @JsonDeserialize(using = LegacyUuidDeserializer.class)
    private UUID createdByUserId;

    @NotNull
    @Range(min = 15L, max = 480L)
    private long duration = 45L;
    
}
