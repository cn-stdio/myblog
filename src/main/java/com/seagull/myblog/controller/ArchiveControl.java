package com.seagull.myblog.controller;

import com.seagull.myblog.service.ArchiveService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seagull_gby
 * @date 2018/12/19 21:01
 * Description: 归档跳转
 */
@Controller
public class ArchiveControl {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/archive")
    public String archive() {
        return "archive";
    }

    @ResponseBody
    @RequestMapping("/getArchive")
    public JSONObject archiveArticles(int rows, int pageNum) {
        JSONObject returnArchive = new JSONObject();

        returnArchive = archiveService.getArchiveArticles(rows, pageNum);

        return returnArchive;
    }

    @ResponseBody
    @RequestMapping("/getArchiveDate")
    public JSONObject archiveArticleDate() {
        JSONObject returnArchive = new JSONObject();

        returnArchive = archiveService.getArchiveDates();

        return returnArchive;
    }
}
