package com.spring.webflux.jdbc;

import java.util.concurrent.TimeUnit;

import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.DatabaseType;
import org.springframework.stereotype.Component;

import io.reactivex.Flowable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookRepository {

	private Database db;

	public BookRepository() throws Exception {
		String connectionString = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=docker";
		this.db = Database.nonBlocking()
				// the jdbc url of the connections to be placed in the pool
				.url(connectionString)
				// an unused connection will be closed after thirty minutes
				.maxIdleTime(30, TimeUnit.MINUTES)
				// connections are checked for healthiness on checkout if the connection
				// has been idle for at least 5 seconds
				.healthCheck(DatabaseType.POSTGRES).idleTimeBeforeHealthCheck(5, TimeUnit.SECONDS)
				// if a connection fails creation then retry after 30 seconds
				// .createRetryInterval(30, TimeUnit.SECONDS)
				// the maximum number of connections in the pool
				.maxPoolSize(3).build();

		// this.db = Database.from(pool);
	}

	public Flux<BookModel> getAll() {
		String sql = "SELECT * FROM books";

		Flowable<BookModel> bookFlowable = db.select(sql).get(rs -> {
			BookModel book = new BookModel();
			book.setId(rs.getLong("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			return book;
		});

		return Flux.from(bookFlowable);
	}

	Mono<BookModel> createNewBook(Mono<BookModel> bookMono) {

		String createBook = "INSERT INTO books (title, author) VALUES (?, ?)";

		return bookMono.flatMap(newBook -> {
			Flowable<Integer> keys = db.update(createBook).parameters(newBook.getTitle(), newBook.getAuthor())
					.returnGeneratedKeys().getAs(Integer.class);

			Flowable<BookModel> bookFlowable = db.select("SELECT * FROM books WHERE id = ?").parameterStream(keys)
					.get(rs -> {
						BookModel book = new BookModel();
						book.setId(rs.getLong("id"));
						book.setTitle(rs.getString("title"));
						book.setAuthor(rs.getString("author"));

						return book;
					});

			return Mono.from(bookFlowable);
		});

	}

	Mono<Void> deleteBook(Long id) {
		String sql = "DELETE FROM books WHERE id = ?";
		Flowable<Integer> counts = db.update(sql).parameter(id).counts();
		return Flux.from(counts).then();
	}

}
