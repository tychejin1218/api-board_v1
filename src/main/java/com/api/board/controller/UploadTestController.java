package com.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadTestController {
    @GetMapping("/uploadForm")
    public String uploadForm() {

        return "uploadForm";
    }
}
