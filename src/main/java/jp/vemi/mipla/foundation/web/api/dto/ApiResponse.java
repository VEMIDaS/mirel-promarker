/*
 * Copyright(c) 2019 mirelplatform All Right Reserved.
 */
package jp.vemi.mirel.foundation.web.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * APIレスポンスの共通クラスです .<br/>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    public T model;

    @Builder.Default
    public List<String> infos = new ArrayList<>();

    @Builder.Default
    public List<String> errs = new ArrayList<>();

    public void addErr(final String message) {
        this.errs.add(message);
    }

    /**
     * default constructor.
     */
    public ApiResponse(T model) {
        this.model = model;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
