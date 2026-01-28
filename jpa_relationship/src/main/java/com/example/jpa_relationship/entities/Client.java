package com.example.jpa_relationship.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
     /*
     Esto crea una tabla de muchos a muchos entre clients y address
      */
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Address> addresses = new ArrayList<>();


    /*
    Esto crea una relación uno a muchos. Un cliente y muchas direcciones
     */
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name="client_id")
//    private List<Address> addresses = new ArrayList<>();

    /*
     * Esto personaliza la relación de una tabla muchos a muchos
     * */

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_clientes_to_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"})
    )
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices = new HashSet<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientDetails details;

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        if (address == null) {
            return;
        }
        addresses.remove(address);
    }


    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setDetails(ClientDetails details) {
        this.details = details;
        if (details != null) {
            details.setClient(this);
        }
    }

    public void removeDetails() {
        if (this.details != null) {
            this.details.setClient(null);
            this.details = null;
        }
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
