package br.com.movieflix.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,
                            double rating,
                            LocalDate releaseDate,
                            List<CategoryResponse> categories,
                            List<StreamingResponse> streamings ) {
}
