package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sporting.goods.rental.entities.Items;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {

    //Получение всех товаров вместе с именами типов и видов товара
    @Query(value = "SELECT item.id_item, item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.cost_one_day_rental, type_of_item.id_type_of_item, type_of_item.name, " +
            "view_of_item.id_view_of_item, view_of_item.name FROM item INNER JOIN type_of_item " +
            "ON item.type_of_item_id = type_of_item.id_type_of_item INNER JOIN view_of_item " +
            "ON item.view_of_item_id = view_of_item.id_view_of_item", nativeQuery = true)
    List<Items> findAllItemsAndTypeAndView();

    //Получение одного товара по id, где вместо id типа и вида, их наименование
    @Query(value = "SELECT item.id_item, item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.cost_one_day_rental, type_of_item.id_type_of_item, type_of_item.name, " +
            "view_of_item.id_view_of_item, view_of_item.name FROM item INNER JOIN type_of_item " +
            "ON item.type_of_item_id = type_of_item.id_type_of_item INNER JOIN view_of_item " +
            "ON item.view_of_item_id = view_of_item.id_view_of_item WHERE item.id_item = :id", nativeQuery = true)
    Items findItemById(@Param("id") Long id);

    //Сортировка товара по виду и типу
    @Query(value = "SELECT i.*, v.*, t.* FROM item AS i INNER JOIN view_of_item AS v " +
            "ON v.id_view_of_item = i.view_of_item_id INNER JOIN type_of_item AS t " +
            "ON t.id_type_of_item = i.type_of_item_id WHERE i.view_of_item_id = :idView " +
            "AND i.type_of_item_id = :idType", nativeQuery = true)
    List<Items> sortItemByIdViewAndType(@Param("idView") Long idView, @Param("idType") Long idType);
}
