package jpabook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager entityManager;

    public void save(Item item){
        if (item.getId() == null){
            entityManager.persist(item);
        }else {
            entityManager.merge(item);
        }
    }

    public Item find(Long id){
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll(){
        return entityManager.createQuery("select i from Item i", Item.class)
            .getResultList();
    }
}
