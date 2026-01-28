package com.example.jpa_relationship.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    @ManyToOne
    @JoinColumn(name = "client_id") // Nombre de cómo quedará la columna relacionada
    // Se puede omitir
    private Client client;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(description, invoice.description) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, total);
    }
}
