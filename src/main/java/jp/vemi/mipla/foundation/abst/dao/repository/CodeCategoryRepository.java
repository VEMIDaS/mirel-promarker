/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.foundation.abst.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.vemi.mipla.foundation.abst.dao.entity.CodeCategory;

/**
 * {@link CodeCategory} のrepositoryです。.<br/>
 */
public interface CodeCategoryRepository
    extends JpaRepository<CodeCategory, String>{

}
