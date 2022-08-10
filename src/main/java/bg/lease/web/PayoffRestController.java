package bg.lease.web;

import bg.lease.model.dto.PaySmallDTO;
import bg.lease.service.PayoffService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class PayoffRestController {

    private PayoffService payoffService;

    public PayoffRestController(PayoffService payoffService) {
        this.payoffService = payoffService;
    }

    @PreAuthorize("@globalPermissionService.PayoffIsRead(#principal.name)")
    @GetMapping("/payoffsmalllist")
    public ResponseEntity<List<PaySmallDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey,
                                                       Principal principal){
        return ResponseEntity.ok(this.payoffService.smallListPay(searchKey));
    }
}
