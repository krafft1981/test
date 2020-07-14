package com.work.test.dao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="customer", schema = "public", catalog = "relationship")
@Setter
@AllArgsConstructor
public class CustomerEntity {

    private Integer id;
    private String name;
    private String phone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    public Integer getId() {
        return id;
    }

    @OneToMany
    public Set<OrderEntity> getOrders() {
        return orders;
    }
    private Set<OrderEntity> orders = new HashSet<>();

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true)
    @Type(type="text")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "phone", nullable = true, insertable = true, updatable = true)
    public String getPhone() {
        return phone;
    }
}
