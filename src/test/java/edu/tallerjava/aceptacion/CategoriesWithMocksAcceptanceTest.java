package edu.tallerjava.aceptacion;

import edu.tallerjava.modelo.Category;
import edu.tallerjava.repositorios.CategoryRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CategoriesWithMocksAcceptanceTest extends AcceptanceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void findAll() {

        List<Category> list = new LinkedList<>();
        Category c1 = new Category();
        c1.setCodigo("MLA5725");
        c1.setNombre("Accesorios para Vehículos");
        list.add(c1);
        Category c2 = new Category();
        c2.setCodigo("MLA1403");
        c2.setNombre("Alimentos y Bebidas");
        list.add(c2);

        when(categoryRepository.findAll()).thenReturn(list);

        final List results = this.restTemplate.getForObject(url + "/categories", List.class);
        assertThat(results).hasSize(2);
    }

}
