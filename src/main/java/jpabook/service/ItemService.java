package jpabook.service;

import java.util.List;
import jpabook.domain.item.Item;
import jpabook.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    @Transactional
    public void save(Item item){
        repository.save(item);
    }

    public Item findItem(Long id){
        return repository.find(id);
    }

    public List<Item> findAll(){
        return repository.findAll();
    }
}
