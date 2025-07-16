package br.com.movieflix.service;

import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StreamingService {
    private StreamingRepository repository;
}
