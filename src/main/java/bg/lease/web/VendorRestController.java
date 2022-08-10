package bg.lease.web;

import bg.lease.model.dto.VendorSmallDTO;
import bg.lease.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @PreAuthorize("@globalPermissionService.VendorIsRead(#principal.name)")
    @GetMapping("/vendorsmalllist")
    public ResponseEntity<List<VendorSmallDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey,
                                                          Principal principal){
        return ResponseEntity.ok(this.vendorService.smallListVendor(searchKey));
    }
}
