package com.ssyx.sys.controller;


import com.ssyx.common.result.Result;
import com.ssyx.model.sys.Region;
import com.ssyx.sys.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
@RestController
@RequestMapping("/admin/sys/region")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/findRegionByKeyword/{keyword}")
    public Result findRegionByKeyword(@PathVariable String keyword) {
        List<Region> regions = regionService.findRegionByKeyword(keyword);
        return Result.success(regions);
    }

}

