/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.foundation.abst.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ファイル管理
 */
@Entity
@Table(name = "mir_file_access_authory")
public class FileAccessAuthority {

    /** ファイルID */
    @Id
    @Column(name = "file_id")
    public String fileId;

    /** ロールID */
    public String roleId;

    /** 権限ID */
    public String authoryId;

}
