package com.abeldevelop.architecture.service.labelsanderrors.service.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.domain.pagination.in.SortDirection;
import com.abeldevelop.architecture.library.common.domain.pagination.in.SortIn;
import com.abeldevelop.architecture.library.common.mapper.pagination.SortMapper;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageSort;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity_;

@Component
public class ErrorMessageSortMapper implements SortMapper<ErrorMessageSort> {

	public SortIn map(ErrorMessageSort enumSort) {
		if(enumSort == null) {
			return getDefault();
		}
		SortIn sortIn = null;
		switch (enumSort) {
			case CODE_DESC:
				sortIn = SortIn.of(SortDirection.DESC, ErrorMessageEntity_.code.getName());
				break;
			case SERVICE_NAME_DESC:
				sortIn = SortIn.of(SortDirection.DESC, ErrorMessageEntity_.serviceName.getName());
				break;
			default:
				sortIn = getDefault();
				break;
		}
		return sortIn;
    }
	
	private SortIn getDefault() {
		return SortIn.of(SortDirection.DESC, ErrorMessageEntity_.serviceName.getName());
	}
}
