package com.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewTestController {
    @RequestMapping(value = "/hello")
    public String ViewTest() {
        return "hello";
    }



    @RequestMapping(value="/testBoardList")
    public void testBoardList () {

    }

    @GetMapping(value = "/testBoardContent")
    public String testBoardContent(String board_seq) {
        return board_seq;
    }


}
