package jp.vemi.mirel.apps.selenade.dto.evidence;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryLog {

    private String sql;
    private List<Map<String, Object>> resultList = Lists.newArrayList();

    private QueryLog() {
        throw new IllegalAccessError("Required ");
    }
    public QueryLog(String sql) {
        this.sql = sql;
    }

    /**
     * countを取得します。
     *
     * @return count
     */
    public int getCount() {
        return this.resultList.size();
    }

}
