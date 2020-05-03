package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Role;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    //Получение всех ролей пользователя
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    //Получение одной роли по id
    public Role getOneRole(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound(id));
    }

    //Добавление новой роли
    public void addRole(Role role){
        roleRepository.save(role);
    }

    //Метод для проверки наличия роли
    private boolean checkRecordToBase(Long id){
        return roleRepository.existsById(id);
    }

    //Редактирование роли
    public void updateRole(Role role){
        if (checkRecordToBase(role.getId())){
            roleRepository.save(role);
        } else{
            throw new RecordNotFound(role.getId());
        }
    }

    //Удаление роли по id
    public void deleteRoleById(Long id) {
        if (checkRecordToBase(id)){
            roleRepository.deleteById(id);
        } else{
            throw new RecordNotFound(id);
        }
    }
    
}
