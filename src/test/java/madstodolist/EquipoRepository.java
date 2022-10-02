import java.util.List;

public interface EquipoRepository extends CrudRepository<Equipo, Long> {
    public List<Equipo> findAll();
}


