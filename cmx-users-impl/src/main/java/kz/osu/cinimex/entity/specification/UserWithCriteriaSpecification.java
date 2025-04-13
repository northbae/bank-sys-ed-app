package kz.osu.cinimex.entity.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.osu.cinimex.entity.SearchCriteria;
import kz.osu.cinimex.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
@NoArgsConstructor
public class UserWithCriteriaSpecification implements Specification<User> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getValue() == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        if (criteria.getOperation().equals("=")) {
            return criteriaBuilder.equal(
                    root.<String> get(criteria.getKey()), criteria.getValue());
        }
        if (criteria.getOperation().equals("like")) {
            return criteriaBuilder.like(
                    root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        else
            return null;
    }
}
