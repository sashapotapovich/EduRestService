package com.example.demo.entity;

import com.example.demo.validator.NotEmptyDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "activity")
public class Activity {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100, message = "{Activity.title.size.title}")
    private String title;

    @NotBlank
    @Size(max = 25, message = "{Activity.summary.size.title}")
    private String summary;

    @NotBlank
    @Size(max = 200, message = "{Activity.description.size.title}")
    private String description;

    @NotEmptyDate(message = "{NotEmptyDate.startDateTime.title}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;

    @NotEmptyDate(message = "{NotEmptyDate.endDateTime.title}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;

    @Size(max = 255, message = "{Activity.info.size.title}")
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
