package bg.lease.web;

import bg.lease.model.dto.CountryDTO;
import bg.lease.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryRestController {

    private CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countrysmalllist")
    public ResponseEntity<List<CountryDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey){
        return ResponseEntity.ok(this.countryService.smallListCountry(searchKey));
    }
}
