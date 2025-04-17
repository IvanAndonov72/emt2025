package mk.ukim.finki.emt.labs.demo.Service.implementation;

import mk.ukim.finki.emt.labs.demo.Model.domain.Host;
import mk.ukim.finki.emt.labs.demo.Repository.HostRepository;
import mk.ukim.finki.emt.labs.demo.Service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }
}
