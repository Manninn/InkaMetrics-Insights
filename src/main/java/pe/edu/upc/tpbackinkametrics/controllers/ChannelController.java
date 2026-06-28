package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.ChannelDTO;
import pe.edu.upc.tpbackinkametrics.entities.Channel;
import pe.edu.upc.tpbackinkametrics.entities.Platform;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMonitoredChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMetricSnapshotService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlatformService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IStreamerService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBroadcastService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/channels")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class ChannelController {
    @Autowired
    private IChannelService channelService;
    @Autowired
    private IMonitoredChannelService monitoredChannelService;
    @Autowired
    private IBroadcastService broadcastService;
    @Autowired
    private IMetricSnapshotService metricSnapshotService;
    @Autowired
    private IPlatformService platformService;
    @Autowired
    private IStreamerService streamerService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<ChannelDTO> list() {
        return channelService.list().stream()
                .map(entity -> new ModelMapper().map(entity, ChannelDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody ChannelDTO dto) {
        Optional<Platform> platform = platformService.listId(dto.getPlatformId());
        if (platform.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Platform not found");
        Channel channel = new Channel();
        channel.setChannelUrl(dto.getChannelUrl());
        channel.setCurrentFollowers(dto.getCurrentFollowers());
        channel.setPlatform(platform.get());
        if (dto.getStreamerId() != null) {
            Optional<Streamer> streamer = streamerService.listId(dto.getStreamerId());
            streamer.ifPresent(channel::setStreamer);
        }
        channelService.insert(channel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody ChannelDTO dto) {
        Optional<Channel> existing = channelService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        Optional<Platform> platform = platformService.listId(dto.getPlatformId());
        if (platform.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Platform not found");
        Channel ch = existing.get();
        ch.setChannelUrl(dto.getChannelUrl());
        ch.setCurrentFollowers(dto.getCurrentFollowers());
        ch.setPlatform(platform.get());
        if (dto.getStreamerId() != null) {
            Optional<Streamer> streamer = streamerService.listId(dto.getStreamerId());
            ch.setStreamer(streamer.orElse(null));
        } else {
            ch.setStreamer(null);
        }
        channelService.update(ch);
        return ResponseEntity.ok("Channel updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Channel> channel = channelService.listId(id);
        if (channel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        }
        broadcastService.list().stream()
            .filter(b -> b.getChannel() != null && b.getChannel().getId() == id)
            .forEach(b -> {
                metricSnapshotService.list().stream()
                    .filter(ms -> ms.getBroadcast() != null && ms.getBroadcast().getId() == b.getId())
                    .forEach(ms -> metricSnapshotService.delete(ms.getId()));
                broadcastService.delete(b.getId());
            });
        monitoredChannelService.list().stream()
            .filter(mc -> mc.getChannel() != null && mc.getChannel().getId() == id)
            .forEach(mc -> monitoredChannelService.delete(mc.getId()));
        channelService.delete(id);
        return ResponseEntity.ok("Channel deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Channel> channel = channelService.listId(id);
        if (channel.isPresent()) {
            ChannelDTO dto = new ModelMapper().map(channel.get(), ChannelDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        }
    }
}
