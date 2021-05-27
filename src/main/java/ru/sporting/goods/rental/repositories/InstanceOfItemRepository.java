package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;

import java.util.List;

public interface InstanceOfItemRepository extends JpaRepository<InstanceOfItem, Long> {

    //Получение всех экземпляров товаров вместе с именами типов и видов товара
    @Query(value = "SELECT instance_of_item.id_instance_of_item, instance_of_item.order_status, " +
            "instance_of_item.id_item, instance_of_item.purchase_price, instance_of_item.hours_of_use, " +
            "item.id_item, item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.cost_one_day_rental, type_of_item.id_type_of_item, " +
            "type_of_item.name, view_of_item.id_view_of_item, view_of_item.name " +
            "FROM item INNER JOIN type_of_item ON item.type_of_item_id = type_of_item.id_type_of_item " +
            "INNER JOIN view_of_item ON item.view_of_item_id = view_of_item.id_view_of_item " +
            "INNER JOIN instance_of_item " +
            "ON instance_of_item.id_item = item.id_item", nativeQuery = true)
    List<InstanceOfItem> findAllInstanceAndItemsAndTypeAndView();

    //Получение экземпляра по id
    @Query(value = "SELECT instance_of_item.id_instance_of_item, instance_of_item.id_item, instance_of_item.order_status, " +
            "instance_of_item.purchase_price, instance_of_item.hours_of_use, item.id_item, " +
            "item.description, item.type_of_item_id, item.view_of_item_id, item.name_item, " +
            "item.cost_one_day_rental, type_of_item.id_type_of_item, " +
            "type_of_item.name, view_of_item.id_view_of_item, view_of_item.name " +
            "FROM item INNER JOIN type_of_item ON item.type_of_item_id = type_of_item.id_type_of_item " +
            "INNER JOIN view_of_item ON item.view_of_item_id = view_of_item.id_view_of_item " +
            "INNER JOIN instance_of_item ON instance_of_item.id_item = item.id_item " +
            "WHERE instance_of_item.id_instance_of_item = :id", nativeQuery = true)
    InstanceOfItem findInstancesOfItemById(@Param("id") Long id);

    //Получение количества копий товара по id товара и статусу
    @Query(value = "SELECT COUNT(*) as numberOfCopies FROM instance_of_item " +
            "WHERE (instance_of_item.order_status = :status) " +
            "AND (instance_of_item.id_item = :id)", nativeQuery = true)
    int getNumberOfCopiesByIdItem(@Param("status") String status, @Param("id") Long id);

    //Получение списка экземпляров товара по id товара и статусу
    @Query(value = "SELECT ins.id_instance_of_item, ins.hours_of_use, ins.order_status, ins.purchase_price, " +
            "ins.id_item, i.id_item, i.description, i.name_item, i.cost_one_day_rental, i.type_of_item_id, " +
            "i.view_of_item_id FROM instance_of_item AS ins INNER JOIN item AS i ON ins.id_item = i.id_item " +
            "WHERE (ins.id_item = :id) AND (ins.order_status = :status)", nativeQuery = true)
    List<InstanceOfItem> getListOfCopiesByIdItemAndStatus(@Param("status") String status, @Param("id") Long id);

    //Получение всех экземпляров по id товара
    @Query(value = "SELECT instance_of_item.id_instance_of_item, instance_of_item." +
            "hours_of_use, instance_of_item.order_status, instance_of_item.purchase_price, instance_of_item.id_item, " +
            "item.id_item, item.name_item, item.description, " +
            "item.cost_one_day_rental, item.type_of_item_id, item.view_of_item_id " +
            "FROM instance_of_item INNER JOIN item " +
            "ON instance_of_item.id_item = item.id_item " +
            "WHERE instance_of_item.id_item = :id", nativeQuery = true)
    List<InstanceOfItem> findInstancesOfItemByIdItems(@Param("id") Long id);
}
