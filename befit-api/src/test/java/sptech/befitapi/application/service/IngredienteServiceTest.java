package sptech.befitapi.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sptech.befitapi.resources.repository.IngredienteRepository;
import sptech.befitapi.resources.repository.entity.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredienteServiceTest {

    @InjectMocks
    IngredienteService service;

    @Mock
    IngredienteRepository ingredienteRepository;

    @Test
    void cadastrar_deveCadastrarUmIngrediente() {
        service.cadastrar(new Ingrediente());

        verify(ingredienteRepository, times(1)).save(any());
    }

    @Test
    void getAll_deveRetornarTodosOsIngredientes() {
        when(ingredienteRepository.findAll()).thenReturn(List.of(new Ingrediente()));
        List<Ingrediente> resultado = service.getAll();

        assertNotNull(resultado);
    }

    @Test
    void gerarCsv_deveGerarOCsvComSucesso() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(1);
        ingrediente.setPorcao(100);
        ingrediente.setProteina(100.00);
        ingrediente.setLipidio(100.00);
        ingrediente.setCarboidrato(100.00);
        ingrediente.setSodio(100.00);
        ingrediente.setCaloria(1001.00);

        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setId(2);
        ingrediente2.setPorcao(100);
        ingrediente2.setProteina(100.00);
        ingrediente2.setLipidio(100.00);
        ingrediente2.setCarboidrato(100.00);
        ingrediente2.setSodio(100.00);
        ingrediente2.setCaloria(1000.00);

        List<Ingrediente> lista = new ArrayList<>();
        lista.add(ingrediente);
        lista.add(ingrediente2);
        when(ingredienteRepository.findAll()).thenReturn(lista);

        service.gerarCsv();
    }

    @Test
    void gerarCsv_deveGerarOCsvComSucessoParaIngredienteComCaloriaIgual() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(1);
        ingrediente.setPorcao(100);
        ingrediente.setProteina(100.00);
        ingrediente.setLipidio(100.00);
        ingrediente.setCarboidrato(100.00);
        ingrediente.setSodio(100.00);
        ingrediente.setCaloria(1000.00);

        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setId(2);
        ingrediente2.setPorcao(100);
        ingrediente2.setProteina(100.00);
        ingrediente2.setLipidio(100.00);
        ingrediente2.setCarboidrato(100.00);
        ingrediente2.setSodio(100.00);
        ingrediente2.setCaloria(1000.00);

        List<Ingrediente> lista = new ArrayList<>();
        lista.add(ingrediente);
        lista.add(ingrediente2);
        when(ingredienteRepository.findAll()).thenReturn(lista);

        service.gerarCsv();
    }

    @Test
    void get_deveDevolverOIngrediente() {
        when(ingredienteRepository.findById(anyInt())).thenReturn(Optional.of(new Ingrediente()));

        Optional<Ingrediente> resultado = service.get(1);

        assertNotNull(resultado);
    }
}