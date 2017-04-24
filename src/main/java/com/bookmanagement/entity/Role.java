package com.bookmanagement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_key")
    private Long roleKey;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Set<User> users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roleKey == null) ? 0 : roleKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (roleKey == null) {
            if (other.roleKey != null)
                return false;
        } else if (!roleKey.equals(other.roleKey))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Role [roleKey=" + roleKey + ", roleName=" + roleName + ", users=" + users + "]";
    }

}
