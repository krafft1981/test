package com.work.test.dao;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="author", schema = "public", catalog = "relationship")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

	private Integer id;
	private String fio;
	private Integer birthYear;
	private Set<BookEntity> books;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToMany
	@JoinTable(name = "book_author",
			joinColumns = @JoinColumn(name = "author_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	public Set<BookEntity> getBooks() {
		return books;
	}

	public void addBook(BookEntity entity) {
		books.add(entity);
	}

	@Basic
	@Column(name = "fio", nullable = true, insertable = true, updatable = true)
	public String getFio() {
		return fio;
	}

	@Basic
	@Column(name = "birthYear", nullable = true, insertable = true, updatable = true)
	public Integer getBirthYear() {
		return birthYear;
	}
}
