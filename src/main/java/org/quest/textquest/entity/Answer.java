package org.quest.textquest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Answer {
    Long id;
    String text;
    Long nextQuestionId;
    String reasonIfKnightDies;

    @JsonCreator
    public Answer(@JsonProperty("id") Long id, @JsonProperty("text") String text, @JsonProperty("nextQuestionId") Long nextQuestionId, @JsonProperty("reasonIfKnightDies") String reasonIfKnightDies) {
        this.id = id;
        this.text = text;
        this.nextQuestionId = nextQuestionId;
        this.reasonIfKnightDies = reasonIfKnightDies;
    }
}
