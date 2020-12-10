/*
 * Copyright(c) 2019 mirelplatform All Right Reserved.
 */
package jp.vemi.mipla.apps.mste.domain.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * MSTE ステンシル
 */
@Entity
@Table(name = "mste_stencil")
@Setter
@Getter
public class MsteStencil {

    /** ステンシルコード */
    @Id
    @Column
    public String stencilCd;

    /** ステンシル名 */
    @Column
    public String stencilName;

    /** 項目種類 */
    /*
     * 0：ステンシル分類, 1：ステンシル
     */
    @Column
    public String itemKind;

    /**
     * ソート
     */
    @Column
    public Integer sort;
}
