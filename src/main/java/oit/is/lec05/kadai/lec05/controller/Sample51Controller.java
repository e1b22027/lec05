package oit.is.lec05.kadai.lec05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /sample5へのリクエストを扱うクラス authenticateの設定をしていれば， /sample5へのアクセスはすべて認証が必要になる
 */
@Controller
@RequestMapping("/sample5")
public class Sample51Controller {
  @GetMapping("step1")
  public String sample51() {
    return "sample51.html";
  }
}
