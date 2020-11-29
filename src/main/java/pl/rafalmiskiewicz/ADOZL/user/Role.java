package pl.rafalmiskiewicz.ADOZL.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Integer id;

    @Column(name = "role")
    @NotNull
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        Role role = (Role) obj;
        if(role.getId()==getId()||role.getRole()==getRole())
            return true;
        else
            return false;
    }

}
