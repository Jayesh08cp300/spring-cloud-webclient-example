package com.example.controller;

import com.example.entity.BookShow;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookShowClientController {
	private WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder()
				.baseUrl("http://localhost:9191/api/bookShow")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@PostMapping("/")
	public Mono<String> BookNow(@RequestBody BookShow request) {
		return webClient.post()
				.uri("/")
				.syncBody(request)
				.retrieve()
				.bodyToMono(String.class);
	}

	@GetMapping("/")
	public Flux<BookShow> trackAllBooking() {
		return webClient.get()
				.uri("/")
				.retrieve()
				.bodyToFlux(BookShow.class);
	}

	@GetMapping("/{bookingId}")
	public Mono<BookShow> getBookingById(@PathVariable int bookingId) {
		return webClient.get()
				.uri("/" + bookingId)
				.retrieve()
				.bodyToMono(BookShow.class);
	}

	@DeleteMapping("/{bookingId}")
	public Mono<String> cancelBooking(@PathVariable int bookingId) {
		return webClient.delete()
				.uri("/" + bookingId)
				.retrieve()
				.bodyToMono(String.class);
	}

	@PutMapping("/{bookingId}")
	public Mono<BookShow> updateBooking(@PathVariable int bookingId, @RequestBody BookShow request) {
		return webClient.put()
				.uri("/" + bookingId)
				.syncBody(request)
				.retrieve()
				.bodyToMono(BookShow.class);
	}
}
