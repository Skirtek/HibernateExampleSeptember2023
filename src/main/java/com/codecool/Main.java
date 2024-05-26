package com.codecool;

import com.codecool.entity.Address;
import com.codecool.entity.SimpleUser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = sessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            SimpleUser simpleUser = new SimpleUser();

            Address address = Address
                    .builder()
                    .cityName("Warszawa")
                    .streetName("Al. Krakowskie")
                    .buildingNumber("13A")
                    .build();

            simpleUser.setAddress(address);

            session.persist(address);
            session.persist(simpleUser);

            session.getTransaction().commit();
            session.clear();
        }
    }

    private static SessionFactory sessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            log.error("Exception occurred when creating session factory", ex);
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(ex);
        }
    }
}