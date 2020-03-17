package com.educational.MovieChallenge.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieDTO {
    private String title;
    private int year;
}
