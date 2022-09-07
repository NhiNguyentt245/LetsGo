/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.repository.impl;

import com.at.pojo.Chuyenxe;
import com.at.pojo.Taixe;
import com.at.pojo.User;
import com.at.repository.TaiXeRepository;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thu
 */
@Repository
@Transactional

public class TaiXeRepositoryImpl implements TaiXeRepository{   
  @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    
    @Override
    public List<Taixe> listTX(String kw) {
         Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Taixe> q = b.createQuery(Taixe.class);
        Root root = q.from(Taixe.class);
        q.select(root);
        if(kw != null)
        {
            Predicate p1 = b.like(root.get("tenTaiXe").as(String.class),
                        String.format("%%%s%%", kw));
            Predicate p2 = b.like(root.get("maTaiXe").as(String.class),
                    String.format("%%%s%%", kw));
         q = q.where(b.or(p1,p2));
        }
        q = q.orderBy(b.asc(root.get("maTaiXe")));

        Query query = s.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public boolean addTX(Taixe tx) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {

            s.save(tx);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Taixe getTaiXe(int id) {
             Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            return s.get(Taixe.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateTX(Taixe tx) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
             String maU = tx.getMaU();
             
             if(maU != "null")
             {  
                  User u = s.get(User.class,maU);
                  tx.setMaUser(u);
             }
             else
                 tx.setMaUser(null);
  
           
            s.clear();
            s.update(tx);
            s.flush();
            
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleTX(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
             Taixe tx = s.get(Taixe.class, id);
             
            Set<Chuyenxe> cx1 = tx.getChuyenxeSet();
             Set<Chuyenxe> cx2 = tx.getChuyenxeSet1();
            for(Chuyenxe c : cx1)
            {
                c.setMaTaiXeChinh(null);
                s.update(c);
            }
            for(Chuyenxe c : cx2)
            {
                c.setMaTaiXePhu(null);
                s.update(c);
            }
            
            s.remove(tx);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
