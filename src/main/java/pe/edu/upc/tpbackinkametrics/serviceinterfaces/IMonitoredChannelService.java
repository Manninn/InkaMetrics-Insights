package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.MonitoredChannel;

import java.util.List;
import java.util.Optional;

public interface IMonitoredChannelService {
    public List<MonitoredChannel> list();
    public MonitoredChannel insert(MonitoredChannel monitoredChannel);
    public Optional<MonitoredChannel> listId(int id);
    public MonitoredChannel update(MonitoredChannel monitoredChannel);
    public void delete(int id);
    public List<MonitoredChannel> listByCompany(int companyId);
}
