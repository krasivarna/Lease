package bg.lease.web;

import bg.lease.model.dto.PaySmallDTO;
import bg.lease.service.PayoffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PayoffRestController {

    private PayoffService payoffService;

    public PayoffRestController(PayoffService payoffService) {
        this.payoffService = payoffService;
    }

    @GetMapping("/payoffsmalllist")
    public ResponseEntity<List<PaySmallDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey){
        return ResponseEntity.ok(this.payoffService.smallListPay(searchKey));
    }
}
