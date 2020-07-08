package com.work.test.dao;

import java.text.DateFormat;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order", schema = "public", catalog = "relationship")
@Setter
@NoArgsConstructor
public class OrderEntity {

    private Integer id;
//    private Integer customerId;
//    private Set<BookEntity> books;
    private DateFormat startedAt;
    private DateFormat stoppedAt;
    private boolean finished;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    public Integer getId() {
        return id;
    }

/*
    @Basic
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customerId")
    public Integer getCustomerId() {
        return customerId;
    }
*/
    @Basic
    @Column(name = "startedAt", nullable = true, insertable = true, updatable = true)
    public DateFormat getStartedAt() {
        return startedAt;
    }

    @Basic
    @Column(name = "stoppedAt", nullable = true, insertable = true, updatable = true)
    public DateFormat getStoppedAt() {
        return stoppedAt;
    }

    @Basic
    @Column(name = "finished", nullable = true, insertable = true, updatable = true)
    public boolean getFinished() {
        return finished;
    }
}
