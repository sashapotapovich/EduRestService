package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;
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
@EqualsAndHashCode(exclude = "changes")
@ToString
@Document(collection = "history")
public class History {

    @Id
    private String id;
    private String activityId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;
    private ActionType type;
    private List<ChangeSet> changes;

    public History(String activityId, LocalDateTime dateTime, ActionType type, List<ChangeSet> changes) {
        this.activityId = activityId;
        this.dateTime = dateTime;
        this.type = type;
        this.changes = changes;
    }
}
