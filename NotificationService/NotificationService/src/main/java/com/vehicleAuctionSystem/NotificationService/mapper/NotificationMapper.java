package com.vehicleAuctionSystem.NotificationService.mapper;

import com.vehicleAuctionSystem.NotificationService.dto.NotificationDto;
import com.vehicleAuctionSystem.NotificationService.entity.Notification;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationDto toDto(Notification notification);

    Notification toEntity(NotificationDto notificationDto);

    List<NotificationDto> toDtoList(List<Notification> notifications);

    List<Notification> toEntityList(List<NotificationDto> notificationDtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateNotificationFromDto(NotificationDto dto, @MappingTarget Notification entity);
}