/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.foundation.abst.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * ファイル管理
 */
@Setter
@Getter
@Entity
@Table(name = "mir_file_management")
public class FileManagement {

    /** ファイルID */
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column()
    public String fileId;

    /** ファイル名 */
    @Column()
    public String fileName;
    
    /** ファイルパス */
    @Column()
    public String filePath;
 
    /** 期日日 */
    @Column()
    public Date expireDate;

    /** バージョン */
    @Column(columnDefinition = "integer default 1")
    public long version;

    /** 削除フラグ */
    @Column(columnDefinition = "boolean default false")
    public Boolean deleteFlag = false;

    /** 作成ユーザ */
    @Column()
    public String createUserId;

    /** 作成日 */
    @Column()
    public Date createDate;

    /** 更新ユーザ */
    @Column()
    public String updateUserId;

    /** 更新日 */
    @Column()
    public Date updateDate;

    @PrePersist
    public void onPrePersist() {
        setDefault(this);
    }

    @PreUpdate
    public void onPreUpdate() {
        setDefault(this);
    }

    public static void setDefault(final FileManagement entity) {

        if(null == entity.createDate) {
            entity.createDate = new Date();
        }

        if(null == entity.updateDate) {
            entity.updateDate = new Date();
        }
    }
}
