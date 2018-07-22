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

    private ReadinglistRepository readinglistRepository;

    @Autowired
    public ReadinglistController(ReadinglistRepository readinglistRepository) {
        this.readinglistRepository = readinglistRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String readerBooks(Reader reader, Model model) {
        List<Book> readingList = readinglistRepository.findByReader(reader);
        if( readingList != null ) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
        }
        return "readingList";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readinglistRepository.save(book);
        return "redirect:/";
    }
}
