package EjA2XML;

public class Author {
    private String nombre;
    private String role;

    public Author(String nombre, String role) {
        this.nombre = nombre;
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role != null && !role.isEmpty()
                ? nombre + " (" + role + ")"
                : nombre;
    }
}
