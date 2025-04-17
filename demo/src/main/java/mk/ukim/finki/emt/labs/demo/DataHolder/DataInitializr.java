package mk.ukim.finki.emt.labs.demo.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.labs.demo.Model.domain.Country;
import mk.ukim.finki.emt.labs.demo.Model.domain.Host;
import mk.ukim.finki.emt.labs.demo.Repository.CountryRepository;
import mk.ukim.finki.emt.labs.demo.Repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializr(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }


    @PostConstruct
    public void initializeData() {
        // Тhe country is dependent on the author, it must be the first!
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Brasil", "South America");
        Country country3 = new Country("Japan", "Asia");

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);

        Host author1 = new Host("Petre M.", "Andreevski", country1);
        Host author2 = new Host("Paulo", "Coelho", country2);
        Host author3 = new Host("Haruki", "Murakami", country3);

        this.hostRepository.save(author1);
        this.hostRepository.save(author2);
        this.hostRepository.save(author3);
    }
}
