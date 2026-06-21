package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Users;

import java.util.List;

public interface IUserService {
    public void insert(Users user);
    public List<Users> list();
    public void delete(Long idUser);
    public Users listId(Long idUser);
    public void update(Users user);
}
