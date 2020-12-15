package com.company.app.controller;

import com.company.app.model.Booking;
import com.company.app.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/booking")
    public String getBooks(Model model) {
    	LocalDate date = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = date.format(myFormatObj);
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> booked = new ArrayList<>();
        for(Booking book : bookings){
        	if(book.getStatus().equalsIgnoreCase("Y") && book.getDate().equalsIgnoreCase(dateString)){
        		booked.add(book);
        	}
        }
        model.addAttribute("bookcount",booked.size());
        model.addAttribute("bookings", bookings);
        return "booking";
    }

    @GetMapping("/book/{id}/{status}")
    public String getBookById(Model model,@PathVariable int id,@PathVariable String status) {
    	LocalDate date = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = date.format(myFormatObj);
        Optional<Booking> booking = bookingRepository.findById(id);
        Booking bookd = booking.get();
        status = status.equalsIgnoreCase("Y")?"N":"Y";
        bookd.setStatus(status);
        if(status.equalsIgnoreCase("Y")){
            bookd.setDate(dateString);
        }
        bookingRepository.save(bookd);
        
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> booked = new ArrayList<>();
        for(Booking book : bookings){
        	if(book.getStatus().equalsIgnoreCase("Y") && book.getDate().equalsIgnoreCase(dateString)){
        		booked.add(book);
        	}
        }
        model.addAttribute("bookcount",booked.size());
        model.addAttribute("bookings", bookings);
        return "booking";
    }
    
    
}
