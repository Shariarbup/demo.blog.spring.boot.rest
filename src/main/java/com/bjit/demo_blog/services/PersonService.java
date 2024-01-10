package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.criteria_entity.Partner;
import com.bjit.demo_blog.entity.criteria_entity.Person;
import com.bjit.demo_blog.entity.criteria_entity.Phone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private EntityManager entityManager;

    public void selectFromMultipleRoot() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        Root<Partner> partnerRoot = criteriaQuery.from(Partner.class);

        Predicate personResitriction = builder.and(
                builder.equal(personRoot.get("address"), "dhaka"),
                builder.isNotEmpty(personRoot.get("phones")));

        Predicate partnerResitriction = builder.and(
                builder.like(partnerRoot.get("name"), "%Tonni%"),
                builder.equal(partnerRoot.get("version"), 1));

        criteriaQuery.where(builder.and(personResitriction, partnerResitriction));

        List<Tuple> tuples = entityManager.createQuery(criteriaQuery).getResultList();

        for (Tuple tuple: tuples) {
            Person person = (Person) tuple.get(0);
            if(person != null) {
                System.out.println(person);
                List<Phone> phones = person.getPhones();
                for(Phone phone: phones) {
                    System.out.println(phone.getId()+"\t"+phone.getNumber()+"\t"+phone.getType().toString());
                }
                Partner partner = (Partner) tuple.get(1);
                System.out.println(partner);
            }
        }
    }
}
