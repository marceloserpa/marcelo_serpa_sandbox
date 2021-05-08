package com.marceloserpa.lucenepoc;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueriesPoC {

    public static void main(String[] args) throws IOException, ParseException {
        Directory memoryIndex = new RAMDirectory();

        createIndex(memoryIndex, new Book("The shining", "Stephen King"));
        createIndex(memoryIndex, new Book("A Brief History of Time", "Stephen Hawking"));

        System.out.println(">> Fuzzy Query...");
        Term term = new Term("title", "shinning");
        Query query = new FuzzyQuery(term);

        IndexReader indexReader = DirectoryReader.open(memoryIndex);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        TopDocs hits = searcher.search(query, 10);

        List<Book> books = new ArrayList<>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            System.out.println(searcher.doc(scoreDoc.doc));
            String title = searcher.doc(scoreDoc.doc).get("title");
            String author = searcher.doc(scoreDoc.doc).get("author");
            books.add(new Book(title, author));
        }

        System.out.println("--" + books.size());
    }

    private static void createIndex(Directory memoryIndex, Book book) {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        try(IndexWriter writter = new IndexWriter(memoryIndex, indexWriterConfig)) {
            Document document = new Document();
            document.add(new TextField("title", book.getTitle(), Field.Store.YES));
            document.add(new TextField("author", book.getAuthor(), Field.Store.YES));
            writter.addDocument(document);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
