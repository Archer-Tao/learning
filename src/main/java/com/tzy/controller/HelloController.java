package com.tzy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@RestController
@Tag(name = "hello")
@RequestMapping("/v1")
public class HelloController {
  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("/hello")
  @Operation(summary = "hello")
  public void hello() {
    logger.info("hello world!");
  }

  @GetMapping("/hello2")
  public ResponseEntity<byte[]> download() {
    byte[] bytes = "Hello2 is OK 好的!".getBytes(StandardCharsets.UTF_8);

    //创建响应头对象，并添加头信息
    HttpHeaders headers = new HttpHeaders();
    //告知浏览器这是一个字节流，浏览器处理字节流的默认方式就是下载,
    //这个类型一般会配合另一个响应头Content-Disposition,该响应头指示回复的内容该以何种形式展示，
    //是以inline内联的形式（即网页或者网页的一部分），还是以attachment附件的形式下载并保存到本地。
    headers.add("Content-Type", "application/octet-stream");
    headers.add("Content-Disposition", "attachment; filename=\"hell2.txt\"");

    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }

  @GetMapping("/hello3")
  public void hello3(HttpServletResponse response) throws Exception {
    byte[] bytes = "Hello3 is OK 好的!".getBytes(StandardCharsets.UTF_8);

    response.setContentType("application/octet-stream");

    String excelName = "hello3.txt";
    //设置content-disposition响应头控制浏览器以下载的形式打开文件
    response.addHeader("Content-Disposition", "attachment;filename=\"" + excelName + "\"");
    try (OutputStream out = response.getOutputStream();) {
      out.write(bytes);
      out.flush();
    }
  }

}
