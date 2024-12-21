package jp.vemi.mirel.apps.selenade.dto.evidence;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryLog {

    private String sql;
    private List<Map<String, Object>> resultList = Lists.newArrayList();

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
