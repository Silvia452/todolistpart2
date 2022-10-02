package madstodolist;

// imports

@SpringBootTest
@Sql(scripts = "/clean-db.sql", executionPhase = AFTER_TEST_METHOD)
public class EquipoServiceTest {

    @Autowired
    EquipoService equipoService;

    // Añade dos equipos a la base de datos
    public void addEquiposBD() {
        Equipo equipo1 = equipoService.crearEquipo("Proyecto Cobalto");
        Equipo equipo2 = equipoService.crearEquipo("Proyecto Níquel");
    }

    @Test
    public void obtenerListadoEquipos() {
        // GIVEN
        // Dos equipos en la base de datos
        addEquiposBD();

        // WHEN
        List<Equipo> equipos = equipoService.findAllOrderedByName();

        // THEN
        assertThat(equipos).hasSize(2);
        assertThat(equipos.get(0).getNombre()).isEqualTo("Proyecto Cobalto");
        assertThat(equipos.get(1).getNombre()).isEqualTo("Proyecto Níquel");
    }
}
