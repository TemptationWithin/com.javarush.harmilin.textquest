package org.quest.textquest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {
    Long id;
    String text;
    String imageLink;
    List<Answer> answers;

    @JsonCreator
    public Question(@JsonProperty("id") Long id,
                    @JsonProperty("text") String text,
                    @JsonProperty("imageLink") String imageLink,
                    @JsonProperty("answers") List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.imageLink = imageLink;
        this.answers = answers;
    }
}
