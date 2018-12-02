package com.partsshop.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partsshop.rest.dto.PartsCategoryRest;

import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.repo.PartsCategoryRepo;

@Service
public class PartsCategoryServiceImpl implements PartsCategoryService {

	private Logger log = LoggerFactory.getLogger(PartsCategoryServiceImpl.class);

	@Autowired
	private PartsCategoryRepo repo;

	@Override
	public PartsCategoryRest savePart(PartsCategoryRest partsCategoryRest) {

		PartsCategory part = new PartsCategory();

		part.setName(partsCategoryRest.getName());
		part.setNameAr(partsCategoryRest.getNameAr());

		repo.save(part);

		if (part.getId() == null) {
			return null;
		} else {
			partsCategoryRest.setId(part.getId());
			return partsCategoryRest;
		}

	}

	@Override
	public PartsCategoryRest updatePart(PartsCategoryRest partsCategoryRest) {
		try {
			PartsCategory part = new PartsCategory();

			part.setId(partsCategoryRest.getId());
			part.setName(partsCategoryRest.getName());
			part.setNameAr(partsCategoryRest.getNameAr());

			repo.save(part);
			return partsCategoryRest;
		} catch (Exception exp) {
			log.error(exp.getMessage());
			return null;
		}

	}

	@Override
	public PartsCategoryRest getNameById(String id) {
		PartsCategory part = repo.findById(id).orElse(null);
		if (part != null) {
			PartsCategoryRest partsCategoryRest = new PartsCategoryRest();

			partsCategoryRest.setId(part.getId());
			partsCategoryRest.setName(part.getName());
			partsCategoryRest.setNameAr(part.getNameAr());

			return partsCategoryRest;
		} else {
			return null;
		}
	}

	@Override
	public List<PartsCategoryRest> getNames() {

		List<PartsCategory> parts = repo.findAll();

		return parts.stream().map(part -> new PartsCategoryRest(part.getId(), part.getName(), part.getNameAr()))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteName(String id) {
		// TODO Auto-generated method stub

	}

}
