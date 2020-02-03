package com.abeldevelop.architecture.service.labelsanderrors.service.service.v1;

import java.util.List;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;

public interface FindErrorMessageService {

	public ErrorMessage executeFindById(Long id);
	
	public ErrorMessage executeFindOne(List<String> usedLibraries, String serviceName, String languageCode, String code);
	
	public PaginationResult<ErrorMessage> executeFindAll(PaginationAndSortIn paginationAndSortIn, String serviceName, String languageCode, String code);
	
}
