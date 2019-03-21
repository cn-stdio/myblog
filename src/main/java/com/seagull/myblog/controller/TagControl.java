package com.seagull.myblog.controller;

import com.seagull.myblog.service.TagService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/3/20 14:41
 * Description: 标签跳转类
 */

@Controller
public class TagControl {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag/{tag}")
    public String tagPageJump(Model model, @PathVariable("tag") String tag) {
        model.addAttribute("tag", tag);

        return "tagPage";
    }

    @GetMapping("/type/{type}")
    public String typePageJump(Model model, @PathVariable("type") String type) {
        model.addAttribute("type", type);

        return "typePage";
    }

    @ResponseBody
    @RequestMapping("/getTagPage")
    public JSONObject getTagPage(HttpServletRequest request, String tagName) {
        JSONObject gtp = new JSONObject();

        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        gtp = tagService.getArticleByTag(rows, pageNum, tagName);

        return gtp;
    }

    @ResponseBody
    @RequestMapping("/getTypePage")
    public JSONObject getTypePage(HttpServletRequest request, String typeName) {
        JSONObject gtp = new JSONObject();

        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        gtp = tagService.getArticleByType(rows, pageNum, typeName);

        return gtp;
    }

    @ResponseBody
    @RequestMapping("/getPageCategory")
    public JSONObject getPageCategory() {
        JSONObject gpcg = new JSONObject();

        gpcg = tagService.getAllArticleType();

        return gpcg;
    }
}
