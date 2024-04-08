package com.example.controller;

import com.example.entity.BookShow;
import com.example.repository.BookShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/bookShow")
public class BookShowController {
	@Autowired
	private BookShowRepository repository;

	@PostMapping("/")
	public String bookShow(@RequestBody BookShow BookShow) {
		BookShow response = repository.save(BookShow);
		return "Hi " + response.getUserName() + " your Request for " + response.getShowName() + " on date " + response.getBookingTime()
				+ "Booking successfully..";
	}

	@GetMapping("/")
	public List<BookShow> getAllBooking() {
		return repository.findAll();
	}

	@GetMapping("/{bookingId}")
	public BookShow getBooking(@PathVariable int bookingId) {
		return repository.findById(bookingId)
				.get();
	}

	@DeleteMapping("/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}

	@PutMapping("/{bookingId}")
	public BookShow updateBooking(@RequestBody BookShow updateBookShow, @PathVariable int bookingId) {
		BookShow dbResponse = repository.findById(bookingId)
				.get();
		dbResponse.setBookingTime(updateBookShow.getBookingTime());
		dbResponse.setPrice(updateBookShow.getPrice());
		dbResponse.setShowName(updateBookShow.getShowName());
		dbResponse.setUserCount(updateBookShow.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}
}
