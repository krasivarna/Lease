package bg.lease.model.mapper;

import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.dto.LeaseCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeaseCardMapper {

    @Mapping(ignore=true,target = "vendorNo")
    LeaseHeaderEntity leaseCardDtOToLeaseHeaderEntity(LeaseCardDTO leaseCardDTO);

    @Mapping(target="vendorNo",ignore = true)
    LeaseCardDTO leaseHeaderEntityToLeaseCardDto(LeaseHeaderEntity leaseHeader);

    @Mapping(ignore=true,target = "vendorNo")
    void leaseCardDtoLeaseHeaderEntity(LeaseCardDTO leaseCardDTO, @MappingTarget LeaseHeaderEntity leaseHeaderEntity);
}
