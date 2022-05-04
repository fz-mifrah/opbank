package com.sgaraba.library.repository;

import com.sgaraba.library.domain.Book;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class BookRepositoryWithBagRelationshipsImpl implements BookRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> fetchBagRelationships(Optional<Book> book) {
        return book.map(this::fetchAuthors);
    }

    @Override
    public Page<Book> fetchBagRelationships(Page<Book> books) {
        return new PageImpl<>(fetchBagRelationships(books.getContent()), books.getPageable(), books.getTotalElements());
    }

    @Override
    public List<Book> fetchBagRelationships(List<Book> books) {
        return Optional.of(books).map(this::fetchAuthors).orElse(Collections.emptyList());
    }

    Book fetchAuthors(Book result) {
        return entityManager
            .createQuery("select book from Book book left join fetch book.authors where book is :book", Book.class)
            .setParameter("book", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Book> fetchAuthors(List<Book> books) {
        return entityManager
            .createQuery("select distinct book from Book book left join fetch book.authors where book in :books", Book.class)
            .setParameter("books", books)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
