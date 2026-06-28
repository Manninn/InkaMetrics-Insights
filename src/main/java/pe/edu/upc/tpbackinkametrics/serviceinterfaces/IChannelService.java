package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Channel;

import java.util.List;
import java.util.Optional;

public interface IChannelService {
    public List<Channel> list();
    public Channel insert(Channel channel);
    public Optional<Channel> listId(int id);
    public Channel update(Channel channel);
    public void delete(int id);
    public List<Channel> listByCompany(int companyId);
}
