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

@SpringBootTest
@Sql(scripts = "/clean-db.sql", executionPhase = AFTER_TEST_METHOD)
public class EquipoServiceTest {

    @Autowired
    EquipoService equipoService;

    @Autowired
    UsuarioService usuarioService;

    class TresIds {
        Long equipo1Id;
        Long equipo2Id;
        Long usuarioId;
        TresIds(Long equipo1Id, Long equipo2Id, Long usuarioId) {
            this.equipo1Id = equipo1Id;
            this.equipo2Id = equipo2Id;
            this.usuarioId = usuarioId;
        }
    }

    // Añade dos equipos a la base de datos y un usuario en el primer equipo.
    // Devuelve el identificador de los equipos y de los usuarios.
    public TresIds addEquiposBD() {
        Equipo equipo1 = equipoService.crearEquipo("Proyecto Cobalto");
        Equipo equipo2 = equipoService.crearEquipo("Proyecto Níquel");
        Usuario usuario = new Usuario("user@ua");
        usuario.setPassword("123");
        usuario = usuarioService.registrar(usuario);
        equipoService.addUsuarioEquipo(usuario.getId(), equipo1.getId());
        return new TresIds(equipo1.getId(), equipo2.getId(), usuario.getId());
    }


}


    @Test
    public void obtenerEquipo() {
        // GIVEN
        // Un equipo en la base de datos
        Long equipoId = addEquiposBD().equipo1Id;

        // WHEN
        Equipo equipoBD = equipoService.findById(equipoId);

        // THEN
        assertThat(equipoBD.getNombre()).isEqualTo("Proyecto Cobalto");
        // Comprobamos que la relación con Usuarios es lazy: al
        // intentar acceder a la colección de usuarios se debe lanzar una
        // excepción
        assertThatThrownBy(() -> {
            equipoBD.getUsuarios().size();
        }).isInstanceOf(LazyInitializationException.class);
    }
    
