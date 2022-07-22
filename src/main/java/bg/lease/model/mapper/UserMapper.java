package bg.lease.model.mapper;

import bg.lease.model.UserEntity;
import bg.lease.model.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}
