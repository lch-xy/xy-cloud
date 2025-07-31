package org.xy.cloud.system.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xy.cloud.framework.common.pojo.CommonResponse;
import org.xy.cloud.system.api.user.dto.UserDTO;
import org.xy.cloud.system.enums.ApiConstants;

@FeignClient(name = ApiConstants.SERVER_NAME)
public interface AdminUserApi {

    @GetMapping(ApiConstants.PREFIX + "/getUserInfoById")
    CommonResponse<UserDTO> getUserInfoById(@RequestParam("id") Long id);

}
