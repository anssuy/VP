package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @GetMapping
    public String getReservationConfirmationPage(@RequestParam String readerName, @RequestParam String bookTitle, @RequestParam Integer numberOfCopies, HttpServletRequest request, Model model) {
        model.addAttribute("readerName", readerName);
        model.addAttribute("ipAddress", request.getRemoteAddr());
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("numberOfCopies", numberOfCopies);
        return "reservationConfirmation";
    }

    @PostMapping
    public String placeReservation(@RequestParam String bookTitle, @RequestParam String readerName, @RequestParam String readerAddress, @RequestParam Integer numCopies, Model model) {
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("numCopies", numCopies);
        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
        String params = String.format("bookTitle=%s&readerName=%s&readerAddress=%s&numberOfCopies=%d", bookTitle, readerName, readerAddress, numCopies);
        return "redirect:bookReservation?" + params;
    }
}