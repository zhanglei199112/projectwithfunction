package interceptor;

import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
        Object.class})})
public class MyInterceptor implements Interceptor {

  public Object intercept(Invocation invocation) throws Throwable {
    Executor executor = (Executor) invocation.getTarget();
    Object[] args = invocation.getArgs();
    MappedStatement ms = (MappedStatement) args[0];
    String msId = ms.getId();
//    if (QueryMethodsHelper.isQueryMethod(msId)) {
//      Object parameter = args[1];
//      RowBounds rbs = (RowBounds) args[2];
//      ResultHandler<?> rh = (ResultHandler<?>) args[3];
//
//      Object example = getExample(msId, parameter);
//
//      return invocation.getMethod().invoke(executor, ms, example, rbs, rh);
//    } else if (QueryMethodsHelper.isDeleteMethod(msId)) {
//      Object parameter = args[1];
//      Object example = getExample(msId, parameter);
//      return invocation.getMethod().invoke(executor, ms, example);
//    }
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {

  }
}
