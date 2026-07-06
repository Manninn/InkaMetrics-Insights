package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.MonitoredChannelDTO;
import pe.edu.upc.tpbackinkametrics.entities.Channel;
import pe.edu.upc.tpbackinkametrics.entities.Company;
import pe.edu.upc.tpbackinkametrics.entities.MonitoredChannel;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMonitoredChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ICompanyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitored-channels")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class MonitoredChannelController {
    @Autowired
    private IMonitoredChannelService monitoredChannelService;
    @Autowired
    private IChannelService channelService;
    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<MonitoredChannelDTO> list() {
        return monitoredChannelService.list().stream()
                .map(entity -> new ModelMapper().map(entity, MonitoredChannelDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody MonitoredChannelDTO dto) {
        Optional<Channel> channel = channelService.listId(dto.getChannelId());
        if (channel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        MonitoredChannel mc = new MonitoredChannel();
        mc.setChannel(channel.get());
        if (dto.getCompanyId() != null) {
            Optional<Company> company = companyService.listId(dto.getCompanyId());
            company.ifPresent(mc::setCompany);
        }
        monitoredChannelService.insert(mc);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody MonitoredChannelDTO dto) {
        Optional<MonitoredChannel> existing = monitoredChannelService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monitored Channel not found");
        Optional<Channel> channel = channelService.listId(dto.getChannelId());
        if (channel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        MonitoredChannel mc = existing.get();
        mc.setChannel(channel.get());
        if (dto.getCompanyId() != null) {
            Optional<Company> company = companyService.listId(dto.getCompanyId());
            mc.setCompany(company.orElse(null));
        } else {
            mc.setCompany(null);
        }
        monitoredChannelService.update(mc);
        return ResponseEntity.ok("Monitored Channel updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<MonitoredChannel> mc = monitoredChannelService.listId(id);
        if (mc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monitored Channel not found");
        }
        try {
            monitoredChannelService.delete(id);
            return ResponseEntity.ok("Monitored Channel deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el canal monitoreado porque tiene registros asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<MonitoredChannel> mc = monitoredChannelService.listId(id);
        if (mc.isPresent()) {
            MonitoredChannelDTO dto = new ModelMapper().map(mc.get(), MonitoredChannelDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monitored Channel not found");
        }
    }
}
