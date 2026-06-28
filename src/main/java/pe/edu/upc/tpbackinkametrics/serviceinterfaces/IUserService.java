package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.User;

import java.util.List;

public interface IUserService {
    public void insert(User user);
    public List<User> list();
    public void delete(Long idUser);
    public User listId(Long idUser);
    public void update(User user);
}
