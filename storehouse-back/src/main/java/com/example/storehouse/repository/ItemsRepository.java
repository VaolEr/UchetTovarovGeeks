package com.example.storehouse.repository;

import com.example.storehouse.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

//@Repository
@Transactional(readOnly = true)
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findByName(String name);

    List<Item> findByNameLike(String name);

    //    @EntityGraph(attributePaths = {"category", "storehouse"}, type = EntityGraph.EntityGraphType.FETCH)
    // Проверить запрос. С переходом к сущности ItemsStorehouses - запрос удалить
    @Query(nativeQuery = true, value =
        "select id, name, sku, supplier_id, category_id, storehouse_id, qty\n"
            + "from `geekbrains_trainee`.`items` i\n"
            + "         join `geekbrains_trainee`.items_storehouses s on i.id = s.item_id\n"
            + "where i.name like :name;")
    Optional<Item> getByName(String name);

}
