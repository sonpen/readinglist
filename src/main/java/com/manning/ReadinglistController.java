package com.manning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sonikju on 2018-06-30.
 */
@Controller
@RequestMapping("/")
public class ReadinglistController {
    private static final String reader = "craig";
    private ReadinglistRepository readinglistRepository;

    @Autowired
    public ReadinglistController(ReadinglistRepository readinglistRepository) {
        this.readinglistRepository = readinglistRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String readerBooks(Model model) {
        List<Book> readingList = readinglistRepository.findByReader(reader);
        if( readingList != null ) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addToReadingList(Book book) {
        book.setReader(reader);
        readinglistRepository.save(book);
        return "redirect:/";
    }
}
