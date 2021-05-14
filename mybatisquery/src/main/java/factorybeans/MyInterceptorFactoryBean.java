package factorybeans;

import interceptor.MyInterceptor;
import org.springframework.beans.factory.FactoryBean;

public class MyInterceptorFactoryBean implements FactoryBean<MyInterceptor> {

  public MyInterceptor getObject() throws Exception {
    return new MyInterceptor();
  }

  public Class<?> getObjectType() {
    return MyInterceptor.class;
  }
}
