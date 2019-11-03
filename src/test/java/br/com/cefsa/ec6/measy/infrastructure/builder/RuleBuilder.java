package br.com.cefsa.ec6.measy.infrastructure.builder;

import br.com.six2six.fixturefactory.Rule;

public class RuleBuilder implements Builder<Rule> {
  private Rule rule = new Rule();

  public RuleBuilder with(String field, Object value) {
    rule.add(field, value);
    return this;
  }

  @Override
  public Rule build() {
    return rule;
  }
}
