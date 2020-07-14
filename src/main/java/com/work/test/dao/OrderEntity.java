package com.work.test.dao;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Entity
@Table(name="orders", schema = "public", catalog = "relationship")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Integer id;
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true)
    @Type(type="text")
    private String name;
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customer;
    public CustomerEntity getCustomer() {
        return customer;
    }

    @OneToMany
    private List<BookEntity> books = new ArrayList<>();
    public List<BookEntity> getBooks() {
        return books;
    }

    public void addBook(BookEntity entity) {
        books.add(entity);
    }

    @Basic
    @Column(name = "startedAt", nullable = true, insertable = true, updatable = true)
    private DateFormat startedAt;
    public DateFormat getStartedAt() {
        return startedAt;
    }

    @Basic
    @Column(name = "stoppedAt", nullable = true, insertable = true, updatable = true)
    private DateFormat stoppedAt;
    public DateFormat getStoppedAt() {
        return stoppedAt;
    }

    @Basic
    @Column(name = "finished", nullable = true, insertable = true, updatable = true)
    @ColumnDefault("false")
    private boolean finished;
    public boolean getFinished() {
        return finished;
    }
}
