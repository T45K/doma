package org.seasar.doma.jdbc.criteria.declaration;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.seasar.doma.jdbc.criteria.LikeOption;
import org.seasar.doma.jdbc.criteria.Tuple2;
import org.seasar.doma.jdbc.criteria.context.Context;
import org.seasar.doma.jdbc.criteria.context.Criterion;
import org.seasar.doma.jdbc.criteria.context.Operand;
import org.seasar.doma.jdbc.criteria.context.SubSelectContext;
import org.seasar.doma.jdbc.criteria.def.EntityDef;
import org.seasar.doma.jdbc.criteria.def.PropertyDef;

public class WhereDeclaration extends ComparisonDeclaration<Context> {

  public WhereDeclaration(Context context) {
    super(context);
  }

  public <PROPERTY> void isNull(PropertyDef<PROPERTY> propertyDef) {
    Objects.requireNonNull(propertyDef);
    add(new Criterion.IsNull(support.toProp(propertyDef)));
  }

  public <PROPERTY> void isNotNull(PropertyDef<PROPERTY> propertyDef) {
    Objects.requireNonNull(propertyDef);
    add(new Criterion.IsNotNull(support.toProp(propertyDef)));
  }

  public <PROPERTY> void like(PropertyDef<PROPERTY> left, PROPERTY right) {
    Objects.requireNonNull(left);
    add(new Criterion.Like(support.toProp(left), support.toParam(left, right), LikeOption.NONE));
  }

  public <PROPERTY> void like(PropertyDef<PROPERTY> left, PROPERTY right, LikeOption option) {
    Objects.requireNonNull(left);
    add(new Criterion.Like(support.toProp(left), support.toParam(left, right), option));
  }

  public <PROPERTY> void notLike(PropertyDef<PROPERTY> left, PROPERTY right) {
    Objects.requireNonNull(left);
    add(new Criterion.NotLike(support.toProp(left), support.toParam(left, right), LikeOption.NONE));
  }

  public <PROPERTY> void notLike(PropertyDef<PROPERTY> left, PROPERTY right, LikeOption option) {
    Objects.requireNonNull(left);
    add(new Criterion.NotLike(support.toProp(left), support.toParam(left, right), option));
  }

  public <PROPERTY> void between(PropertyDef<PROPERTY> propertyDef, PROPERTY start, PROPERTY end) {
    Objects.requireNonNull(propertyDef);
    add(
        new Criterion.Between(
            support.toProp(propertyDef),
            support.toParam(propertyDef, start),
            support.toParam(propertyDef, end)));
  }

  public <PROPERTY> void in(PropertyDef<PROPERTY> left, List<PROPERTY> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    add(
        new Criterion.In(
            support.toProp(left),
            right.stream().map(p -> support.toParam(left, p)).collect(toList())));
  }

  public <PROPERTY> void notIn(PropertyDef<PROPERTY> left, List<PROPERTY> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    add(
        new Criterion.NotIn(
            support.toProp(left),
            right.stream().map(p -> support.toParam(left, p)).collect(toList())));
  }

  public <PROPERTY> void in(PropertyDef<PROPERTY> left, SubSelectContext<PROPERTY> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    add(new Criterion.InSubQuery(support.toProp(left), right.context));
  }

  public <PROPERTY> void notIn(PropertyDef<PROPERTY> left, SubSelectContext<PROPERTY> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    add(new Criterion.NotInSubQuery(support.toProp(left), right.context));
  }

  public <PROPERTY1, PROPERTY2> void in(
      Tuple2<PropertyDef<PROPERTY1>, PropertyDef<PROPERTY2>> left,
      List<Tuple2<PROPERTY1, PROPERTY2>> right) {
    Operand.Prop prop1 = support.toProp(left.first());
    Operand.Prop prop2 = support.toProp(left.second());
    List<Tuple2<Operand.Param, Operand.Param>> params =
        right.stream()
            .map(
                pair -> {
                  Operand.Param param1 = support.toParam(left.first(), pair.first());
                  Operand.Param param2 = support.toParam(left.second(), pair.second());
                  return new Tuple2<>(param1, param2);
                })
            .collect(toList());
    add(new Criterion.InTuple2(new Tuple2<>(prop1, prop2), params));
  }

  public <PROPERTY1, PROPERTY2> void notIn(
      Tuple2<PropertyDef<PROPERTY1>, PropertyDef<PROPERTY2>> left,
      List<Tuple2<PROPERTY1, PROPERTY2>> right) {
    Operand.Prop prop1 = support.toProp(left.first());
    Operand.Prop prop2 = support.toProp(left.second());
    List<Tuple2<Operand.Param, Operand.Param>> params =
        right.stream()
            .map(
                pair -> {
                  Operand.Param param1 = support.toParam(left.first(), pair.first());
                  Operand.Param param2 = support.toParam(left.second(), pair.second());
                  return new Tuple2<>(param1, param2);
                })
            .collect(toList());
    add(new Criterion.NotInTuple2(new Tuple2<>(prop1, prop2), params));
  }

  public <PROPERTY1, PROPERTY2> void in(
      Tuple2<PropertyDef<PROPERTY1>, PropertyDef<PROPERTY2>> left,
      SubSelectContext<Tuple2<PROPERTY1, PROPERTY2>> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    Operand.Prop prop1 = support.toProp(left.first());
    Operand.Prop prop2 = support.toProp(left.second());
    add(new Criterion.InTuple2SubQuery(new Tuple2<>(prop1, prop2), right.context));
  }

  public <PROPERTY1, PROPERTY2> void notIn(
      Tuple2<PropertyDef<PROPERTY1>, PropertyDef<PROPERTY2>> left,
      SubSelectContext<Tuple2<PROPERTY1, PROPERTY2>> right) {
    Objects.requireNonNull(left);
    Objects.requireNonNull(right);
    Operand.Prop prop1 = support.toProp(left.first());
    Operand.Prop prop2 = support.toProp(left.second());
    add(new Criterion.NotInTuple2SubQuery(new Tuple2<>(prop1, prop2), right.context));
  }

  public void exists(SubSelectContext<?> subSelectContext) {
    Objects.requireNonNull(subSelectContext);
    add(new Criterion.Exists(subSelectContext.context));
  }

  public void notExists(SubSelectContext<?> subSelectContext) {
    Objects.requireNonNull(subSelectContext);
    add(new Criterion.NotExists(subSelectContext.context));
  }

  public SubSelectFromDeclaration from(EntityDef<?> entityDef) {
    return new SubSelectFromDeclaration(entityDef);
  }

  public void and(Runnable block) {
    runBlock(block, Criterion.And::new);
  }

  public void or(Runnable block) {
    runBlock(block, Criterion.Or::new);
  }

  public void not(Runnable block) {
    runBlock(block, Criterion.Not::new);
  }

  @Override
  protected void runBlock(Runnable block, Function<List<Criterion>, Criterion> newCriterion) {
    List<Criterion> preservedWhere = context.getWhere();
    List<Criterion> newWhere = new ArrayList<>();
    context.setWhere(newWhere);
    block.run();
    context.setWhere(preservedWhere);
    if (!newWhere.isEmpty()) {
      add(newCriterion.apply(newWhere));
    }
  }

  @Override
  protected void add(Criterion criterion) {
    context.getWhere().add(criterion);
  }
}
