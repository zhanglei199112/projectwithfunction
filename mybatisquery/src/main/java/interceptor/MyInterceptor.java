package interceptor;

import config.QueryMethodsConfig;
import exception.QueryMethodsException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import util.QueryMethodsHelper;
import util.TkMapperWhereFactory;

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
    if (QueryMethodsHelper.isQueryMethod(msId)) {
      Object parameter = args[1];
      RowBounds rbs = (RowBounds) args[2];
      ResultHandler<?> rh = (ResultHandler<?>) args[3];

      Object example = getExample(msId, parameter);

      return invocation.getMethod().invoke(executor, ms, example, rbs, rh);
    } else if (QueryMethodsHelper.isDeleteMethod(msId)) {
      Object parameter = args[1];
      Object example = getExample(msId, parameter);
      return invocation.getMethod().invoke(executor, ms, example);
    }
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {

  }

  private Object getExample(String msId, Object parameter) throws ClassNotFoundException {
    Queue<Object> params = new LinkedList<>();
    if (parameter instanceof ParamMap) {
      @SuppressWarnings("unchecked")
      ParamMap<Object> pm = (ParamMap<Object>) parameter;

      String regex = "^param(\\d)+$";
      String[] keys = pm.keySet().toArray(new String[] {});
      Arrays.sort(keys);
      for (String key : keys) {
        if (key.matches(regex)) {
          params.add(pm.get(key));
        }
      }
    } else if (parameter instanceof StrictMap) {
      @SuppressWarnings("unchecked")
      StrictMap<Object> pm = (StrictMap<Object>) parameter;
      String[] keys = pm.keySet().toArray(new String[] {});
      Arrays.sort(keys);
      for (String key : keys) {
        params.add(pm.get(key));
      }
    } else {
      params.add(parameter);
    }
    if (QueryMethodsConfig.isTkMapper()) {
      Object example = TkMapperWhereFactory.getExampleByMsId(msId, params);
      return example;
    }

    throw new QueryMethodsException("no orm find tkmapper or mybatis-plus");
  }
}
