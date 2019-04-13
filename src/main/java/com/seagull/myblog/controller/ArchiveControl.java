package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.service.ArchiveService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author Seagull_gby
 * @date 2018/12/19 21:01
 * Description: 归档跳转
 */
@Controller
public class ArchiveControl {

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("time", articleMapper.queryLastCreateTime().getTime());

        return "archive";
    }

    @ResponseBody
    @RequestMapping("/getArchives")
    public JSONObject archiveArticles(HttpServletRequest request) throws ParseException {
        JSONObject returnArchive = new JSONObject();

        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        String date = request.getParameter("archiveDate");

        if(date.split("年").length==1) {
            returnArchive = archiveService.getArchiveArticles(rows, pageNum);
        } else {
            returnArchive = archiveService.getArchiveArticleOfDate(rows, pageNum, date);
        }

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
