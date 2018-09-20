package com.javarush.parts.controller;

import com.javarush.parts.model.Part;
import com.javarush.parts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author dubetskyi_ov on 13.09.2018
 */

@Controller
@RequestMapping(path = "/parts")
public class PartController {

  @Autowired
   // @Qualifier("partService")
   // @Resource(name="partService")
    private PartService partService;


    @RequestMapping(path = "")
    public String viewPartsList (
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order,
            @RequestParam(required = false, defaultValue = "") String term, // запрос на поиск
            @RequestParam(required = false, defaultValue = "") String required,
            Model uiModel
    ){
        Sort sort;
        if (order.equals("desc")) sort = new Sort(Sort.Direction.DESC, sortBy);
        else sort = new Sort(Sort.Direction.ASC, sortBy);

        //Нумерация страниц для Spring Data JPA начинается с 0
        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = new PageRequest(pageNumber, 10, sort);

        Page<Part> parts;
        Part minObject;

        if (!required.equals("") && (required.equals("true") || required.equals("false")))
            parts = partService.search(term,  Boolean.parseBoolean(required), pageRequest);
       else parts = partService.search(term,  pageRequest);

       List<Part> comps = parts.getContent ();

        Comparator<Part> comparator = Comparator.comparing( Part::getAmount );
        try {
             minObject = comps.stream ()
                    .filter (item -> item.getMust () == true)
                    .min (comparator).get ();
        }catch (Exception ex)
        {
             minObject = new Part (0);
        }

        uiModel.addAttribute("parts", parts);
        uiModel.addAttribute("current", pageNumber);
        uiModel.addAttribute("term", term);
        uiModel.addAttribute("required", required);
        uiModel.addAttribute("minObject", minObject);

        return "parts/list";
    }

   @GetMapping(path="/all")
    public @ResponseBody Iterable<Part> getAllParts() {
        // This returns a JSON or XML with the users
        return partService.findAll();
    }


    @GetMapping(path = "/{id}")
    public String viewPart (@PathVariable Long id, Model uiModel){
        Optional<Part> part = partService.findById(id);
        if (part.isPresent()) {
            uiModel.addAttribute("part", part.get ());
            return "parts/view";
        } else {
            // handle not found, return null or throw
            return  null;
        }

    }

    @GetMapping(path = "/add")
    public String addPart(Model uiModel){
        uiModel.addAttribute("part", new Part());
        return "parts/newPart";
    }
    @PostMapping(path = "/add")
    public String addSubmit(
            @ModelAttribute Part part
    ) throws IOException {


        partService.save(part);

        return "redirect:/parts";
    }

    @GetMapping(path = "/edition/{id}")
    public String newEditionPart(@PathVariable Long id, Model uiModel){
        Optional<Part> part = partService.findById(id);
        if (part.isPresent()) {
            uiModel.addAttribute ("part", part.get ());
            return "parts/edition";
        }
        else
            return null;
    }

    @PostMapping(path = "/edition/{id}")
    public String editionSubmit(
            @ModelAttribute Part part,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        partService.update(part, id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/parts/{id}";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePart(@PathVariable Long id){
        Optional<Part> part = partService.findById(id);
        if (part.isPresent()) {


            partService.delete (part.get ());
        }
            return  "redirect:/parts";
    }

}
