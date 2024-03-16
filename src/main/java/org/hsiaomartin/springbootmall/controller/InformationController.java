package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.model.Information;
import org.hsiaomartin.springbootmall.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import static org.hsiaomartin.springbootmall.constant.InformationType.NEWS;
import static org.hsiaomartin.springbootmall.constant.InformationType.NORMAL;

@Controller
@SessionAttributes(value = {"userLogin"})
public class InformationController {

    @Autowired
    private InformationService infoService;

    // 首頁
    @GetMapping("/")
    public String getInformations(Model model) {

        List<Information> newsList = infoService.getInfosByType(NEWS);
        model.addAttribute("newsList", newsList);
        List<Information> normalList = infoService.getInfosByType(NORMAL);
        model.addAttribute("normalList", normalList);

        return "home";
    }

    @GetMapping("/info/{infoId}")
    public ResponseEntity<Information> getInfoById(@PathVariable Integer infoId) {

        Information info = infoService.getInfoById(infoId);

        if(info == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(info);
        }
    }
}
