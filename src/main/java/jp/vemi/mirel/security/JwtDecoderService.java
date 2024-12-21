/*
 * Copyright(c) 2015-2024 mirelplatform.
 */
package jp.vemi.mirel.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;
import jp.vemi.mirel.foundation.abst.dao.entity.TenantSystemMaster;
import jp.vemi.mirel.foundation.abst.dao.repository.TenantSystemMasterRepository;

@Service
public class JwtDecoderService {

    @Autowired
    private TenantSystemMasterRepository tenantSystemMasterRepository;

    public JwtDecoder getJwtDecoder(String tenantId) {
        Optional<TenantSystemMaster> tenant = tenantSystemMasterRepository.findByTenantIdAndKey(tenantId, "jwkSetUri");
        if (tenant.isPresent()) {
            return NimbusJwtDecoder.withJwkSetUri(tenant.get().getValue()).build();
        } else {
            throw new IllegalArgumentException("Invalid tenant ID or JWK Set URI not found");
        }
    }
}
