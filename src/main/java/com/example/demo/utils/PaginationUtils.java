package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.BaseEntity;

public class PaginationUtils {

	public static <T extends BaseEntity> Page<T> findPaginated(List<T> listOfEntities, Pageable pageable) {
		Integer pageSize = pageable.getPageSize();
		Integer currentPage = pageable.getPageNumber();
		Integer startItem = currentPage * pageSize;

		List<T> pagedList;

		if (listOfEntities.size() < startItem) {
			pagedList = Collections.emptyList();
		} else {
			Integer toIndex = Math.min(startItem + pageSize, listOfEntities.size());
			pagedList = listOfEntities.subList(startItem, toIndex);
		}

		Page<T> entityPage = new PageImpl<T>(pagedList, PageRequest.of(currentPage, pageSize), listOfEntities.size());

		return entityPage;
	}

	@SuppressWarnings("rawtypes")
	public static <T extends BaseEntity> Map<String, Collection> getPaginationAttributes(List<T> listOfEntities,
			Integer currentPage, Integer pageSize) {
		Page<T> entityPage = findPaginated(listOfEntities, PageRequest.of(currentPage - 1, pageSize));

		Map<String, Collection> paginationAttributes = new HashMap<String, Collection>();

		List<Integer> pageNumbers = new ArrayList<Integer>();
		Integer totalPages = entityPage.getTotalPages();
		Integer totalNumberOfEntity = 0;
		Integer startNumberOfCurrentPage = 0;
		Integer endNumberOfCurrentPage = 0;
		String errorString = null;

		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			totalNumberOfEntity = listOfEntities.size();
			startNumberOfCurrentPage = currentPage * pageSize + 1 - pageSize;
			endNumberOfCurrentPage = startNumberOfCurrentPage + entityPage.getNumberOfElements() - 1;
		} else {
			errorString = "Nothing found";
		}

		paginationAttributes.put("pageNumbers", pageNumbers);
		paginationAttributes.put("totalPages", Arrays.asList(totalPages));
		paginationAttributes.put("totalNumberOfEntity", Arrays.asList(totalNumberOfEntity));
		paginationAttributes.put("startNumberOfCurrentPage", Arrays.asList(startNumberOfCurrentPage));
		paginationAttributes.put("endNumberOfCurrentPage", Arrays.asList(endNumberOfCurrentPage));
		paginationAttributes.put("errorString", Arrays.asList(errorString));
		paginationAttributes.put("entityPage", Arrays.asList(entityPage));
		return paginationAttributes;
	}
}
