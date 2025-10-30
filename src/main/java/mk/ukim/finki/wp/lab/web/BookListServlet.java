package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        List<Book> books;
        double rating = 0.0;
        String text = request.getParameter("text");

        try {
            rating = Double.parseDouble(request.getParameter("rating"));
        } catch (Exception ignored) {}

        if (text != null) {
            books = bookService.searchBooks(text, rating);
        } else {
            books = bookService.listAll();
        }

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("books", books);

        templateEngine.process("listBooks", webContext, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = request.getParameter("text");
        String rating = request.getParameter("rating");
        String params = String.format("text=%s&rating=%s", text, rating);

        response.sendRedirect("/?" + params);
    }
}
