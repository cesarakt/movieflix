package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie (MovieRequest movieRequest) {

        //Mapeia a lista de categories ID para uma lista de Objetos de categories só com o ID
        List<Category> categoriesId = movieRequest.categoriesId().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        //Mapeia a lista de streamings ID para uma lista de Objetos de streamings só com o ID
        List<Streaming> streamingsId = movieRequest.streamingsId().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .rating(movieRequest.rating())
                .releaseDate(movieRequest.releaseDate())
                .categories(categoriesId)
                .streamings(streamingsId)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie) {

        List<CategoryResponse> categoryResponses = movie.getCategories().stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamingResponses = movie.getStreamings().stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categoryResponses)
                .streamings(streamingResponses)
                .build();
    }
}
