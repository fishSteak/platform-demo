package com.example.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@Accessors(chain = true)
@Data
public class PushPlusData {
    @NotEmpty
    String title;
    @NotEmpty
    String content;
    String token;
    String template = "html";

    public PushPlusData(String title, String content, String token) {
        this.title = title;
        this.content = content;
        this.token = token;
    }
}
