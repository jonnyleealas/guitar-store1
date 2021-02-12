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

    public List<Guitar> findAll() {
        return jpaApi.em().createQuery("SELECT guitars FROM Guitar guitars", Guitar.class).getResultList();
    }

    public Optional<Guitar> findById(int id) {
//        return guitarList.stream().filter(guitar -> guitar.getId() == id).findFirst();
        return jpaApi.em().createQuery("SELECT guitar FROM Guitar guitar WHERE id = :id", Guitar.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public void delete(int id) {
//        guitarList = guitarList.stream().filter(guitar -> guitar.getId() != id).collect(Collectors.toList());
    }

//    public void add(Guitar guitar) {
//        if (!findById(guitar.getId()).isPresent()) {
//            guitar.setId(++guitarId);
//            guitarList.add(guitar);
//        }
//    }

    public void update(Guitar guitar) {
        Optional<Guitar> guitarOptional = findById(guitar.getId());
        if (guitarOptional.isPresent()) {
            Guitar guitarToUpdate = guitarOptional.get();

            guitarToUpdate.setDescription(guitar.getDescription());
            guitarToUpdate.setName(guitar.getName());
        }
    }

    public void add(Guitar guitar) {
        jpaApi.em().persist(guitar);
    }

}
