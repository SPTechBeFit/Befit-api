package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.befitapi.application.Util.ListaObj;
import sptech.befitapi.resources.repository.IngredienteRepository;
import sptech.befitapi.resources.repository.entity.Ingrediente;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente cadastrar(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
        return ingrediente;
    }

    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    public void gerarCsv() {
        List<Ingrediente> lista = ingredienteRepository.findAll();

        int indMenor;
        for (int i = 0; i < lista.size()-1; i++) {
            indMenor = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).getCaloria() < lista.get(indMenor).getCaloria()) {
                    indMenor = j;
                }
            }
            Ingrediente aux = lista.get(i);
            lista.set(i, lista.get(indMenor));
            lista.set(indMenor, aux);
        }

        ListaObj<Ingrediente> listaObj = new ListaObj(lista.size());

        for (Ingrediente ingredienteDaVez:
                lista) {
            listaObj.adiciona(ingredienteDaVez);
        }

        listaObj.gravaArquivoCsv(lista, "Ingredientes");

    }

    public Optional<Ingrediente> get(int id) {
        return ingredienteRepository.findById(id);
    }
}
