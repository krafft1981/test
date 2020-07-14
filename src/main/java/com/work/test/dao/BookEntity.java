package com.work.test.dao;

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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="book", schema = "public", catalog = "relationship")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

	private Integer id;
	private String name;
	private Integer publishingYear;
	private String annotation;
	private Set<AuthorEntity> authors;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="book_author",
		joinColumns=@JoinColumn(name="book_id", nullable = false),
		inverseJoinColumns=@JoinColumn(name="author_id", nullable = false)
	)
	public Set<AuthorEntity> getAuthors() {
		return authors;
	}

	public void addAuthor(AuthorEntity entity) {
		authors.add(entity);
	}

	@Basic
	@Column(name = "name", nullable = true, insertable = true, updatable = true)
	@Type(type="text")
	public String getName() {
		return name;
	}

	@Basic
	@Column(name = "publishingYear", nullable = true, insertable = true, updatable = true)
	public Integer getPublishingYear() {
		return publishingYear;
	}

	@Basic
	@Column(name = "annotation", nullable = true, insertable = true, updatable = true)
	@Type(type="text")
	public String getAnnotation() {
		return annotation;
	}
}
