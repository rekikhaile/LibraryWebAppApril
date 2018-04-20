package com.rekik.librarywebappapril;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryAppController {

    @Autowired
    BookRepository bookRepository;

    //@Autowired
    //LibraryService libraryService;

    @GetMapping("/")
    public String showBook(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return "index";
    }

    @GetMapping("/addbook")
    public String addBook(Model model)
    {
        /* Add an object to the container (model) named book in the HTML,
        * Containing an empty instantieted book object so that the user
        * can enter values in the form*/
        model.addAttribute("book",new Book());
        return "addbook";
    }



    @PostMapping("/savebook")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addbook";
        }
        bookRepository.save(book);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "addbook";
    }

    @RequestMapping("/changestatus/{id}")
    public String borrowReturn(@PathVariable("id") long id) {
        Book thisBook = bookRepository.findById(id).get();
        //Reverse the status
        thisBook.setAvailable(!thisBook.isAvailable());
        bookRepository.save(thisBook);
        return "redirect:/";
    }


}
