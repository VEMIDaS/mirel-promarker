package jp.vemi.ste.domain.context;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import jp.vemi.framework.exeption.MirelApplicationException;
import jp.vemi.framework.util.InstanceUtil;
import jp.vemi.framework.util.StringConvertionUtil;

/**
 * STEコンテキスト
 *
 * @author mirelplatform
 *
 */
public abstract class SteContext extends LinkedHashMap<String, Object> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6159738237750006932L;

    /** ステンシル名 */
    public abstract String getStencilCanonicalName();

    /** 機能名 */
    public abstract String getFeatureName();

    /** シリアル */
    public abstract String getSerialNo();

    public static <T extends SteContext> SteContext of(Class<T> clazz) {
        return InstanceUtil.newInstance(clazz);
    }

    @Deprecated
    public static <T extends SteContext> SteContext getContext(Class<T> clazz) {
        return InstanceUtil.newInstance(clazz);
    }

    public static <T extends SteContext> SteContext standard(){
         return of(DefaultSteContext.class);
    }

    public static SteContext standard(String stencilName) {

        SteContext ctx = standard();
        ctx.put("stencilCanonicalName", stencilName);
        return ctx;

    }

    public static SteContext standard(String stencilName, String serialNo) {

        SteContext ctx = standard(stencilName);
        ctx.put("serialNo", serialNo);
        return ctx;

    }

    public static class StringContent {
        protected String content;

        public static StringContent string(String content) {
            return new StringContent(content);
        }

        public StringContent(String content) {
            this.content = content;
        }

        public String toString() {
            return content;
        }

        public String ucc() {
            return StringConvertionUtil.toUpperCamelCase(content);
        }

        public String toUpperCamelCase() {
            return ucc();
        }

        public String d2bs() {
            return StringUtils.defaultIfEmpty(content, "").replaceAll("\\.", "\\\\");
        }

        public String replaceDot2Bs() {
            return d2bs();
        }

        public void appendSubInvoker(String name, Map<String, Object> binding) {
            if (null == binding) {
                throw new MirelApplicationException();
            }
            // UpperCamelCase.
            binding.put(StringUtils.join(name, ".toUpperCamelCase()"), toUpperCamelCase());
            binding.put(StringUtils.join(name, ".ucc()"), toUpperCamelCase());

            // Dot -> BackSpace.
            binding.put(StringUtils.join(name, ".replaceDot2Bs()"), replaceDot2Bs());
            binding.put(StringUtils.join(name, ".d2bs()"), replaceDot2Bs());
        }
    }

    public static SteContext newSteContext(Map<String, Object> map) {

        SteContext context = standard();
        context.putAll(map);

        return context;

    }

    public String getAsStirng(String key) {

        Object object = this.get(key);

        if (object instanceof StringContent) {
            return ((StringContent) object).content;
        }
        if (object instanceof String) {
            return (String) object;
        }
        return "";
    }

}
