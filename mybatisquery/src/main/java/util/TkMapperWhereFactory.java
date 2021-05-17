package util;

import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;
import tk.mybatis.mapper.util.StringUtil;
import util.Part.Type;
import util.PartTree.OrPart;

/**
 * where条件构建类 参考spring data PredicateBuilder
 * 
 * @author OYGD
 *
 */
public class TkMapperWhereFactory {

  protected final static Log logger = LogFactory.getLog(TkMapperWhereFactory.class);

  private TkMapperWhereFactory() {}

  /**
   * 根据MappedStatement id 与参数生成Example
   * 
   * @param msId
   * @param params
   * @return
   * @throws ClassNotFoundException
   */
  public static Example getExampleByMsId(String msId, Queue<Object> params)
      throws ClassNotFoundException {

    Class<?> entityClass = MsIdUtil.getEntityClass(msId);

    String methodName = MsIdUtil.getMethodName(msId);

    PartTree tree = PartTreeFactory.create(msId, methodName);
    Example example = new Example(entityClass, true);
    example.setDistinct(tree.isDistinct());
    if (StringUtil.isNotEmpty(tree.getQueryProperty())) {
      example.selectProperties(tree.getQueryProperty());
    }
    try {
      Criteria criteria = example.createCriteria();
      for (OrPart node : tree) {
        for (Part part : node) {
          build(part, criteria, params, msId);
        }
        criteria = example.createCriteria();
        example.or(criteria);
      }
    } catch (MapperException e) {
      throw new MapperException(e.getMessage() + " -> " + msId, e);
    }
    Sort sort = tree.getSort();
    if (sort != null) {
      for (Sort.Order order : sort) {
        OrderBy orderBy = example.orderBy(order.getProperty());
        if (order.isAscending()) {
          orderBy.asc();
        } else {
          orderBy.desc();
        }
      }
    }
    return example;
  }

  /**
   * 拼装where条件，参考spring data PredicateBuilder
   * 
   * @return
   */
  public static Criteria build(Part part, Criteria root, Queue<Object> args, String msId) {

    PropertyPath pp = part.getProperty();
    Type type = part.getType();
    String property = pp.getSegment();
    switch (type) {
      case BETWEEN:
        Object a = checkNotNull(property, args, msId);
        Object b = checkNotNull(property, args, msId);
        return root.andBetween(property, a, b);
      case AFTER:
      case GREATER_THAN:
        a = checkNotNull(property, args, msId);
        return root.andGreaterThan(property, a);
      case GREATER_THAN_EQUAL:
        a = checkNotNull(property, args, msId);
        return root.andGreaterThanOrEqualTo(property, a);
      case BEFORE:
      case LESS_THAN:
        a = checkNotNull(property, args, msId);
        return root.andLessThan(property, a);
      case LESS_THAN_EQUAL:
        a = checkNotNull(property, args, msId);
        return root.andLessThanOrEqualTo(property, a);
      case IS_NULL:
        return root.andIsNull(property);
      case IS_NOT_NULL:
        return root.andIsNotNull(property);
      case NOT_IN:
        a = checkNotNull(property, args, msId);
        return root.andNotIn(property, (Iterable<?>) a);
      case IN:
        a = checkNotNull(property, args, msId);
        return root.andIn(property, (Iterable<?>) a);
      case STARTING_WITH:
        a = checkNotNull(property, args, msId);
        return root.andLike(property, a.toString() + "%");
      case ENDING_WITH:
        a = checkNotNull(property, args, msId);
        return root.andLike(property, "%" + a.toString());
      case CONTAINING:
        a = checkNotNull(property, args, msId);
        return root.andLike(property, "%" + a.toString() + "%");
      case NOT_CONTAINING:
        a = checkNotNull(property, args, msId);
        return root.andNotLike(property, "%" + a.toString() + "%");
      case LIKE:
        a = checkNotNull(property, args, msId);
        return root.andLike(property, a.toString());
      case NOT_LIKE:
        a = checkNotNull(property, args, msId);
        return root.andNotLike(property, a.toString());
      case TRUE:
        return root.andEqualTo(property, 1);
      case FALSE:
        return root.andEqualTo(property, 0);
      case SIMPLE_PROPERTY:
        a = args.poll();
        if (a == null) {
          return root.andIsNull(property);
        }
        return root.andEqualTo(property, a);
      case NEGATING_SIMPLE_PROPERTY:
        a = args.poll();
        if (a == null) {
          return root.andIsNotNull(property);
        }
        return root.andNotEqualTo(property, a);
      default:
        throw new IllegalArgumentException("Unsupported keyword " + type);
    }
  }
  
  static Object checkNotNull(String property, Queue<Object> args, String msId) {
    Object param = args.poll();
    if (param == null) {
      throw new IllegalArgumentException("Value must not be null! [" + property + " -> " + msId + "]");
    }
    return param;
  }

}
