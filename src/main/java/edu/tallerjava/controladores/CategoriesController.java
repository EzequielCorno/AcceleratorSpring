package edu.tallerjava.controladores;


import edu.tallerjava.modelo.Category;
import edu.tallerjava.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CategoriesController {

    private final CategoriesService catService;
    private final RestTemplate restTemplate;

    public CategoriesController(CategoriesService catService, RestTemplateBuilder builder) {
        this.catService = catService;
        this.restTemplate = builder.build();
    }

    @GetMapping(path = "/categories")
    public List<Category> obtenerTodas() {
        return catService.findAll();
    }



    @GetMapping(path = "/categories/{idCat}")
    public ResponseEntity<Category> obtenerPorId(@PathVariable String idCat) {
        Optional<Category> catFound = catService.findById(Long.parseLong(idCat));
        return catFound.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    @GetMapping(path = "/categoriesByCode/{code}")
    public ResponseEntity<List<Category>> obtenerPorCodigo(@PathVariable String code) {
        List<Category> catList = new ArrayList<>();
        Optional<Category> catFound = catService.findByCode(code);
        if (catFound.isPresent()) {
            catList.add(catFound.get());
            return new ResponseEntity<List<Category>>(catList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/categoriesByName/{name}")
    public ResponseEntity<List<Category>> obtenerPorNombre(@PathVariable String name) {
        List<Category> catList = new ArrayList<>();
        Optional<Category> catFound = catService.findByName(name);
        if (catFound.isPresent()) {
            catList.add(catFound.get());
            return new ResponseEntity<>(catList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/categoriesByCodeAndName/{code}/{name}")
    public ResponseEntity<List<Category>> obtenerPorCodigoYNombre(@PathVariable String code, @PathVariable String name) {
        List<Category> catList = new ArrayList<>();
        Category catFound = catService.findByCodeAndName(code, name);
        if (catFound != null) {
            catList.add(catFound);
            return new ResponseEntity<>(catList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(path = "/categories")
    public ResponseEntity<Category> crearNuevaCategoria(@RequestBody Category newCategory) {
        Category catCreated = catService.create(newCategory);
        if (catCreated != null) {
            return new ResponseEntity<>(catCreated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
