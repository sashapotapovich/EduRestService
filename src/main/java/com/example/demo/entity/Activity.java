package com.example.demo.entity;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "activity")
public class Activity {

    @MongoId
    @Indexed
    private String id;

    @NotEmpty
    @Size(max = 100)
    private String title;

    @NotEmpty
    @Size(max = 25)
    private String summary;

    @NotEmpty
    @Size(max = 200)
    private String description;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;

    @Size(max = 255)
    private String info;

    public Activity(@NotEmpty @Size(max = 100) String title, @NotEmpty @Size(max = 25) String summary, @NotEmpty @Size(max = 200) String description, @NotBlank LocalDateTime startDateTime, @NotBlank LocalDateTime endDateTime, @Size(max = 255) String info) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.info = info;
    }
}
