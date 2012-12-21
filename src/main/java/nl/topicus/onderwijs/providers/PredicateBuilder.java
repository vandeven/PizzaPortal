package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.collect.Lists;

public class PredicateBuilder
{
	private List<Predicate> predicates = Lists.newArrayList();

	private final Root< ? > root;

	private final CriteriaBuilder builder;

	public PredicateBuilder(Root< ? > root, CriteriaBuilder builder)
	{
		this.root = root;
		this.builder = builder;
	}

	public PredicateBuilder addEq(String property, Object obj)
	{
		predicates.add(builder.equal(root.<String> get(property), obj));
		return this;
	}

	public PredicateBuilder addLike(String property, Object obj)
	{
		predicates.add(builder.like(root.<String> get(property), "%" + obj + "%"));
		return this;
	}

	public List<Predicate> build()
	{
		return predicates;
	}
}
