package oit.is.lec05.kadai.lec05.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncCountFruit56 {
  int count = 1;
  private final Logger logger = LoggerFactory.getLogger(AsyncCountFruit56.class);

  @Async
  public void count(SseEmitter emitter) throws IOException {
    logger.info("count start");
    try {
      while (true) {// 無限ループ
        logger.info("send:" + count);
        // sendによってcountがブラウザにpushされる
        emitter.send(count);
        count++;
        // 1秒STOP
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    }
  }
}
