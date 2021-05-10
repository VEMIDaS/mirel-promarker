package jp.vemi.mirel.apps.selenade.dto.evidence;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Evidence {

    java.util.List<Object> headers;
    java.util.List<Object> data;
    java.util.List<Object> footers;

    /**
     * default constructor.
     *
     * @param arg
     *            エビデンス名
     */
    private Evidence(Object... headers) {
        this.headers = Lists.newArrayList(headers);
        this.data = Lists.newArrayList();
        this.footers = Lists.newArrayList();
    }

    /**
     * $header.<br/>
     *
     * @param args
     * @return this.
     */
    public static Evidence $evidenceHeader(Object... headers) {
        return new Evidence(headers);
    }

    public static Evidence $empty() {
        return new Evidence();

    }

    public Evidence addHeader(String message) {
        this.headers.add(message);
        return this;
    }

    public Evidence addData(Object data) {
        this.data.add(data);
        return this;
    }

    public Evidence addData(java.util.List<Object> data) {
        this.data.addAll(data);
        return this;
    }
}
