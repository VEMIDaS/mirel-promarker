/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mirel.foundation.abst.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
// import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * コード分類
 */
@Entity
@Table(name = "mir_code_category")
public class CodeCategory {

    /** ファイル名 */
    @EmbeddedId
    public PK pk;

    /** コード分類名 */
    public String codeCategoryName;

    /**
     * PK
     */
    @Embeddable
    public static class PK implements Serializable {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 5435L;

        /** 会社コード */
        @Column
        public String companyCd;

        /** コード分類コード */
        @Column
        public String codeCategoryCd;

    }

}
