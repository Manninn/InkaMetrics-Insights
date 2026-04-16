package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Usuarios")
public class Usuarios{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresas_clientes empresa;

    public Usuarios(int id, String email, String password, Roles roles, Empresas_clientes empresa) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.empresa = empresa;
    }

    public Usuarios() {

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRoles() {
        return roles;
    }

    public Empresas_clientes getEmpresa() {
        return empresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void setEmpresa(Empresas_clientes empresa) {
        this.empresa = empresa;
    }
}
