package com.example.controller;

import com.example.ExceptionHandler.BookShowException;
import com.example.entity.BookShow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BookShowClientController {

	private final WebClient.Builder webClientBuilder;

	@PostMapping("/")
	public Mono<String> BookNow(@RequestBody BookShow request) {
		return webClientBuilder.build()
				.post()
				.uri("/")
				.syncBody(request)
				.retrieve()
				.bodyToMono(String.class);
	}

	@GetMapping("/")
	public Flux<BookShow> trackAllBooking() {
		return webClientBuilder.build()
				.get()
				.uri("/")
				.retrieve()
				.bodyToFlux(BookShow.class);
	}

	@GetMapping("/{bookingId}")
	public Mono<BookShow> getBookingById(@PathVariable int bookingId) {
		return webClientBuilder.build()
				.get()
				.uri("/" + bookingId)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,
						clientResponse -> Mono.error(new BookShowException(" 404 Unsupported exception")))
				.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new BookShowException(" 505 Server exception")))
				.bodyToMono(BookShow.class);
	}

	@DeleteMapping("/{bookingId}")
	public Mono<String> cancelBooking(@PathVariable int bookingId) {
		return webClientBuilder.build()
				.delete()
				.uri("/" + bookingId)
				.retrieve()
				.bodyToMono(String.class);
	}

	@PutMapping("/{bookingId}")
	public Mono<BookShow> updateBooking(@PathVariable int bookingId, @RequestBody BookShow request) {
		return webClientBuilder.build()
				.put()
				.uri("/" + bookingId)
				.syncBody(request)
				.retrieve()
				.bodyToMono(BookShow.class);
	}
}
