package bg.lease.web;

import bg.lease.model.dto.VendorSmallDTO;
import bg.lease.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @GetMapping("/vendorsmalllist")
    public ResponseEntity<List<VendorSmallDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey){
        return ResponseEntity.ok(this.vendorService.smallListVendor(searchKey));
    }
}
