package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;

import java.util.List;

public interface InstanceOfItemRepository extends JpaRepository<InstanceOfItem, Long> {

    //Получение всех экземпляров товаров вместе с именами типов и видов товара
    @Query(value = "SELECT instance_of_item.id_instance_of_item, instance_of_item.id_item, " +
            "instance_of_item.purchase_price, instance_of_item.hours_of_use, item.id_item, " +
            "item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.number_of_copies, item.cost_one_hour_rental, type_of_item.id_type_of_item, " +
            "type_of_item.name, view_of_item.id_view_of_item, view_of_item.name " +
            "FROM item INNER JOIN type_of_item ON item.type_of_item_id = type_of_item.id_type_of_item " +
            "INNER JOIN view_of_item ON item.view_of_item_id = view_of_item.id_view_of_item " +
            "INNER JOIN instance_of_item " +
            "ON instance_of_item.id_item = item.id_item", nativeQuery = true)
    List<InstanceOfItem> findAllInstanceAndItemsAndTypeAndView();

    //Получение получение экземпляра по id
    @Query(value = "SELECT instance_of_item.id_instance_of_item, instance_of_item.id_item, " +
            "instance_of_item.purchase_price, instance_of_item.hours_of_use, item.id_item, " +
            "item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.number_of_copies, item.cost_one_hour_rental, type_of_item.id_type_of_item, " +
            "type_of_item.name, view_of_item.id_view_of_item, view_of_item.name " +
            "FROM item INNER JOIN type_of_item ON item.type_of_item_id = type_of_item.id_type_of_item " +
            "INNER JOIN view_of_item ON item.view_of_item_id = view_of_item.id_view_of_item " +
            "INNER JOIN instance_of_item ON instance_of_item.id_item = item.id_item " +
            "WHERE instance_of_item.id_instance_of_item = :id", nativeQuery = true)
    InstanceOfItem findInstancesOfItemById(@Param("id") Long id);
}
