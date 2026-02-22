package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProducteService {

    @Autowired
    private ProducteRepository producteRepository;

    public Producte crearProducte(Producte producte) {
        return producteRepository.save(producte);
    }

    public List<Producte> obtenirTots() {
        return producteRepository.findAll();
    }

    public Optional<Producte> obtenirPerID(Integer id) {
        return producteRepository.findById(id);
    }

    public Optional<Producte> actualitzarProducte(Integer id, Producte dades) {
        return producteRepository.findById(id).map(p -> {
            p.setName(dades.getName());
            p.setDescription(dades.getDescription());
            p.setPrice(dades.getPrice());
            p.setQuantity(dades.getQuantity());
            return producteRepository.save(p);
        });
    }

    public boolean esborrarProducte(Integer id) {
        if (producteRepository.existsById(id)) {
            producteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}