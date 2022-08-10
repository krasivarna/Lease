package bg.lease.web;

import bg.lease.model.dto.CountryDTO;
import bg.lease.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CountryRestController {

    private CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("@globalPermissionService.CountryIsRead(#principal.name)")
    @GetMapping("/countrysmalllist")
    public ResponseEntity<List<CountryDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey,
                                                      Principal principal){
        return ResponseEntity.ok(this.countryService.smallListCountry(searchKey));
    }
}
