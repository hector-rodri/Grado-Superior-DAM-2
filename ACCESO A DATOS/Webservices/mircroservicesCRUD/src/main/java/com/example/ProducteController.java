package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productes")
public class ProducteController {

    @Autowired
    private ProducteService producteService;

    // POST /productes - Crear un nou producte
    @PostMapping
    public ResponseEntity<Producte> crearProducte(@RequestBody Producte producte) {
        Producte nouProducte = producteService.crearProducte(producte);
        return new ResponseEntity<>(nouProducte, HttpStatus.CREATED);
    }

    // GET /productes - Llistar tots els productes
    @GetMapping
    public ResponseEntity<List<Producte>> obtenirTots() {
        List<Producte> productes = producteService.obtenirTots();
        return ResponseEntity.ok(productes);
    }

    // GET /productes/{id} - Obtenir un producte per ID
    @GetMapping("/{id}")
    public ResponseEntity<Producte> obtenirPerID(@PathVariable Integer id) {
        return producteService.obtenirPerID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /productes/{id} - Actualitzar un producte existent
    @PutMapping("/{id}")
    public ResponseEntity<Producte> actualitzarProducte(
            @PathVariable Integer id,
            @RequestBody Producte dades) {
        return producteService.actualitzarProducte(id, dades)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /productes/{id} - Esborrar un producte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> esborrarProducte(@PathVariable Integer id) {
        if (producteService.esborrarProducte(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
