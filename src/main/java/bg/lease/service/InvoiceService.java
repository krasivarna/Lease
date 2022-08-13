package bg.lease.service;

import bg.lease.model.LeaseApplyEntity;
import bg.lease.model.PayoffPlan;
import bg.lease.model.dto.InvoiceDTO;
import bg.lease.repository.InvoiceRepository;
import bg.lease.repository.PayOffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private PayOffRepository payOffRepository;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          PayOffRepository payOffRepository) {
        this.invoiceRepository = invoiceRepository;
        this.payOffRepository = payOffRepository;
    }

    public List<InvoiceDTO> listInvoice() {
        return invoiceRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private InvoiceDTO map(LeaseApplyEntity entity){
        InvoiceDTO result=new InvoiceDTO();
        result.setEntryNo(entity.getEntryNo());
        result.setDocumentType(entity.getDocumentType());
        result.setDocumentNo(entity.getDocumentNo());
        result.setAmountExclVAT(entity.getAmountExclVAT());
        result.setAmountInclVAT(entity.getAmountInclVAT());
        result.setType(entity.getType());

        return result;
    }

    public void addCard(InvoiceDTO invoiceDTO) throws RuntimeException{
        LeaseApplyEntity invoice= new LeaseApplyEntity();
        invoice.setDocumentType(invoiceDTO.getDocumentType());
        invoice.setDocumentNo(invoiceDTO.getDocumentNo());
        invoice.setType(invoiceDTO.getType());
        invoice.setAmountExclVAT(invoiceDTO.getAmountExclVAT());
        invoice.setAmountInclVAT(invoiceDTO.getAmountInclVAT());

        Optional<PayoffPlan> opPay=payOffRepository.findByEntryNo(invoiceDTO.getPayplanEntry());
        if (opPay.isEmpty()){
            throw new RuntimeException("Entry "+ invoiceDTO.getPayplanEntry() + " is not found");
        }
        invoice.setPayplanEntry(opPay.get());

        invoiceRepository.save(invoice);

        List<LeaseApplyEntity> documents=opPay.get().getApplyList();
        documents.add(invoice);
        payOffRepository.save(opPay.get());
    }
}
