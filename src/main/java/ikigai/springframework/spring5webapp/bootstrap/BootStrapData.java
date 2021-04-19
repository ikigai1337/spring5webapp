package ikigai.springframework.spring5webapp.bootstrap;

import ikigai.springframework.spring5webapp.domain.Author;
import ikigai.springframework.spring5webapp.domain.Book;
import ikigai.springframework.spring5webapp.domain.Publisher;
import ikigai.springframework.spring5webapp.repositories.AuthorRepository;
import ikigai.springframework.spring5webapp.repositories.BookRepository;
import ikigai.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Boostrap");

        Publisher publisher = new Publisher("White", "Sk", "New York", "NYC", "4050");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "154584");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "5495464");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("Numbers of Books: " + bookRepository.count());
        System.out.println("Numbers of Publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books "+ publisher.getBooks().size());
    }
}
