package com.klef.fsad.exam;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        // Insert Records
        Invoice i1 = new Invoice();
        i1.setId(3);
        i1.setName("Laptop");
        i1.setDate("2026-03-10");
        i1.setStatus("Paid");
        i1.setAmount(50000);

        Invoice i2 = new Invoice();
        i2.setId(4);
        i2.setName("Mobile");
        i2.setDate("2026-03-11");
        i2.setStatus("Pending");
        i2.setAmount(20000);

        session.persist(i1);
        session.persist(i2);

        t.commit();

        // HQL Query without WHERE
        Query<Invoice> q = session.createQuery("from Invoice", Invoice.class);

        List<Invoice> list = q.list();

        System.out.println("Invoice Records");

        for(Invoice inv : list)
        {
            System.out.println(inv.getId()+" "+inv.getName()+" "+inv.getDate()+" "+inv.getStatus()+" "+inv.getAmount());
        }

        session.close();
        sf.close();
    }
}