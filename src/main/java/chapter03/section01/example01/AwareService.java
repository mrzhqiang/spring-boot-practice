package chapter03.section01.example01;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
  private String beanName;
  private ResourceLoader loader;

  @Override public void setBeanName(String s) {
    beanName = s;
  }

  @Override public void setResourceLoader(ResourceLoader resourceLoader) {
    loader = resourceLoader;
  }

  public void outputResult() {
    System.out.println("Bean 的名称为：" + beanName);
    Resource resource = loader.getResource("classpath:chapter03/section01/example01/test.txt");
    try {
      System.out.println("ResourceLoader 加载的文件内容为：" + IOUtils.toString(resource.getInputStream()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
