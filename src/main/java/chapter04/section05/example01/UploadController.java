package chapter04.section05.example01;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody String upload(MultipartFile file) {
    try {
      FileUtils.writeByteArrayToFile(new File(".", "/upload/" + file.getOriginalFilename()),
          file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      return "wrong";
    }
    return "ok";
  }
}
