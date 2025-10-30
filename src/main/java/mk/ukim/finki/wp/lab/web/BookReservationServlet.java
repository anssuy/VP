package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine templateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine templateEngine) {
        this.bookReservationService = bookReservationService;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("readerName", request.getParameter("readerName"));
        webContext.setVariable("ipAddress", request.getRemoteAddr());
        webContext.setVariable("bookTitle", request.getParameter("bookTitle"));
        webContext.setVariable("numberOfCopies", request.getParameter("numberOfCopies"));

        templateEngine.process("reservationConfirmation", webContext, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookTitle = request.getParameter("bookTitle");
        String readerName = request.getParameter("readerName");
        String readerAddress = request.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(request.getParameter("numCopies"));

        BookReservation bookReservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);

        String params = String.format("bookTitle=%s&readerName=%s&readerAddress=%s&numberOfCopies=%d", bookTitle, readerName, readerAddress, numberOfCopies);

        response.sendRedirect("/bookReservation?" + params);
    }
}
