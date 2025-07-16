package br.com.movieflix.controller;

import br.com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieflix/movie")
@RestController
public class MovieController {

    private final MovieService movieService;
}
