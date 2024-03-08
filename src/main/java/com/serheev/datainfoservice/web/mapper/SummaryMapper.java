package com.serheev.datainfoservice.web.mapper;

import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.web.dto.SummaryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryMapper extends Mappable<Summary, SummaryDto> {
}
