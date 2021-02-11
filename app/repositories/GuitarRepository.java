package repositories;

import com.google.inject.Singleton;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import models.Guitar;
import play.db.jpa.JPAApi;

@Singleton
public class GuitarRepository {
    @Inject JPAApi jpaApi;

    private List<Guitar> guitarList = new ArrayList<>();

    public int bookId = 0;

    public List<Guitar> findAll() {

// List<Guitar> guitars = jpaApi.em().createQuery("SELECT guitars FROM Guitar guitars", Guitar.class).getResultList();
        System.out.println("I am printing shit"+ jpaApi.em().createQuery("SELECT guitars FROM Guitar guitars"));
        return guitarList;
    }

    public Optional<Guitar> findById(int id) {
        return guitarList.stream().filter(guitar -> guitar.getId() == id).findFirst();
    }

    public void delete(int id) {
        guitarList = guitarList.stream().filter(guitar -> guitar.getId() != id).collect(Collectors.toList());
    }

    public void add(Guitar guitar) {
        if (!findById(guitar.getId()).isPresent()) {
            guitar.setId(++bookId);
            guitarList.add(guitar);
        }
    }

    public void update(Guitar guitar) {
        Optional<Guitar> bookOptional = findById(guitar.getId());
        if (bookOptional.isPresent()) {
            Guitar guitarToUpdate = bookOptional.get();

            guitarToUpdate.setDescription(guitar.getDescription());
            guitarToUpdate.setName(guitar.getName());
        }
    }

}
