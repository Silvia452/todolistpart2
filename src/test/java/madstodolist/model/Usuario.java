@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    Set<Tarea> tareas = new HashSet<>();

            @ManyToMany(mappedBy = "usuarios")
    Set<Equipo> equipos = new HashSet<>();



            public Set<Equipo> getEquipos() {
                return equipos;
            }