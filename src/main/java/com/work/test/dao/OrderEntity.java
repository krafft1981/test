package com.work.test.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name="orders", schema = "public", catalog = "relationship")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    private Integer id;
    private String name;
    private CustomerEntity customer = new CustomerEntity();
    private Set<BookEntity> books = new HashSet<>();
    private Date startedAt;
    private Date stoppedAt;
    private boolean finished;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true)
    @Type(type="text")
    public String getName() {
        return name;
    }

    @ManyToOne
    public CustomerEntity getCustomer() {
        return customer;
    }

    @OneToMany
    public Set<BookEntity> getBooks() {
        return books;
    }

    public void addBook(BookEntity entity) {
        books.add(entity);
    }

    @Basic
    @Column(name = "startedAt", nullable = true, insertable = true, updatable = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartedAt() {
        return startedAt;
    }

    @Basic
    @Column(name = "stoppedAt", nullable = true, insertable = true, updatable = true)
    public Date getStoppedAt() {
        return stoppedAt;
    }

    @Basic
    @Column(name = "finished", nullable = true, insertable = true, updatable = true)
    @ColumnDefault("false")
    public boolean getFinished() {
        return finished;
    }

    public void setFinished() {
        finished = true;
    }
}
