/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.apps.mste.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import jp.vemi.mipla.foundation.web.api.dto.node.Node;
import jp.vemi.mipla.foundation.web.model.ValueTextItems;
import jp.vemi.ste.domain.dto.yml.StencilSettingsYml;

/**
 * サジェスト結果.<br/>
 */
public class SuggestResult {

    public StencilSettingsYml.Stencil stencil;

    /** パラメーターズ */
    public Node params;

    /** ステンシル種類ストア */
    public ValueTextItems fltStrStencilCategory = new ValueTextItems();

    /** ステンシルストア */
    public ValueTextItems fltStrStencilCd = new ValueTextItems();

    /** シリアルナンバーストア */
    public ValueTextItems fltStrSerialNo = new ValueTextItems();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
