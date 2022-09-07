/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.repository.impl;

import com.at.pojo.ChuyennxeChongoi;
import com.at.pojo.Chuyenxe;
import com.at.pojo.Comment;
import com.at.pojo.Hoadon;
import com.at.pojo.Nhanvien;
import com.at.pojo.Taixe;
import com.at.pojo.Tuyenxe;
import com.at.pojo.User;
import com.at.pojo.Xe;
import com.at.repository.ChuyenXeChoNgoiRepository;
import com.at.repository.ChuyenXeRepository;
import com.at.repository.CommentRepository;
import com.at.repository.HoaDonRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
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
public class ChuyenXeRepositoryImpl implements ChuyenXeRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    ChuyenXeChoNgoiRepository chuyenXeChoNgoiRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Chuyenxe> listCX(String kw) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chuyenxe> q = b.createQuery(Chuyenxe.class);
        Root root = q.from(Chuyenxe.class);
        q.select(root);
        if (kw != null) {
            Predicate p1 = b.like(root.get("maChuyenXe").as(String.class),
                    String.format("%%%s%%", kw));

            q = q.where(p1);
        }
        q = q.orderBy(b.asc(root.get("maChuyenXe")));

        Query query = s.createQuery(q);

        return query.getResultList();

    }

    @Override
    public boolean addChuyenXe(Chuyenxe cx) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            cx.setMaTuyenXe(s.get(Tuyenxe.class, cx.getFTuyenXe()));
            cx.setMaXe(s.get(Xe.class, cx.getFMaXe()));
            cx.setMaTaiXeChinh(s.get(Taixe.class, cx.getFTaiXeChinh()));
            cx.setMaTaiXePhu(s.get(Taixe.class, cx.getFTaiXePhu()));

            s.save(cx);

            if (!chuyenXeChoNgoiRepository.addCXCN(cx.getMaChuyenXe())) {
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteChuyenXe(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {

            Chuyenxe cx = s.get(Chuyenxe.class, id);

            for (Hoadon maHD : cx.getHoadonSet()) {
                if (!hoaDonRepository.deleteHD(maHD.getMaHoaDon())) {
                    return false;
                }
            }

            if (chuyenXeChoNgoiRepository.deleteCXCN(id)) {
                for (Comment c : cx.getCommentSet()) {
                    System.out.println("========" + c.getContent());
                    if (!commentRepository.deleteComment(c.getIdComment())) {
                        return false;
                    }
                }
                s.remove(cx);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Chuyenxe getChuyenXe(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            Chuyenxe cx = s.get(Chuyenxe.class, id);
            cx.getMaChuyenXe();
            return cx;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateCX(Chuyenxe cx) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            cx.setMaTuyenXe(s.get(Tuyenxe.class, cx.getFTuyenXe()));
            cx.setMaXe(s.get(Xe.class, cx.getFMaXe()));
            cx.setMaTaiXeChinh(s.get(Taixe.class, cx.getFTaiXeChinh()));
            cx.setMaTaiXePhu(s.get(Taixe.class, cx.getFTaiXePhu()));
            s.update(cx);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCX(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
         Chuyenxe cx = s.get(Chuyenxe.class, id);
            try{
            for (Hoadon maHD : cx.getHoadonSet()) {
                if (!hoaDonRepository.deleteHD(maHD.getMaHoaDon())) {
                    return false;
                }
            }

            if (chuyenXeChoNgoiRepository.deleteCXCN(id)) {
                for (Comment c : cx.getCommentSet()) {
                    System.out.println("========" + c.getContent());
                    if (!commentRepository.deleteComment(c.getIdComment())) {
                        return false;
                    }
                }
                s.remove(cx);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Chuyenxe> listCXSearch(String maTX, String day, int so) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
//        try {
//            Tuyenxe tx = s.get(Tuyenxe.class,maTX);
//            Set<Chuyenxe> cx = tx.getChuyenxeSet();
//            List<Chuyenxe> kq = new ArrayList<>();
//            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy");
//
//            for(Chuyenxe c : cx)
//            {
//                String ngay = formatter.format(c.getGioXuatPhat());
//                if(ngay.equals(day))
//                    kq.add(c);
//            }
//            return kq;
//           
//                 
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chuyenxe> q = b.createQuery(Chuyenxe.class);
        Root rootCX = q.from(Chuyenxe.class);
        Root rootTX = q.from(Tuyenxe.class);
        Predicate p2 = b.equal(rootCX.get("maTuyenXe"), rootTX.get("maTuyenXe"));

        q.select(rootCX);

        Predicate p1 = b.like(rootTX.get("maTuyenXe").as(String.class), maTX);
        q = q.where(b.and(p2, p1));
        if (so != -1) {
            if (so == 0) {
                q = q.orderBy(b.asc(rootCX.get("gioXuatPhat")));
            } else if (so == 1) {
                q = q.orderBy(b.desc(rootCX.get("gioXuatPhat")));
            } else if (so == 2) {
                q = q.orderBy(b.asc(rootCX.get("giaVe")));
            } else if (so == 3) {
                q = q.orderBy(b.desc(rootCX.get("giaVe")));
            }
        }

        Query query = s.createQuery(q);

        List<Chuyenxe> Listcx = query.getResultList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        List<Chuyenxe> kq = new ArrayList<>();
        for (Chuyenxe c : Listcx) {
            String ngay = formatter.format(c.getGioXuatPhat());
            if (ngay.equals(day) && c.getGioXuatPhat().after(new Date())) {
                kq.add(c);
            }
        }

        return kq;

    }

    @Override
    public BigDecimal getPrice(int macx) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Chuyenxe cx = s.get(Chuyenxe.class, macx);
        return cx.getGiaVe();

    }

    @Override
    public List<Chuyenxe> getCXByTaiXe(User u) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Chuyenxe> q = b.createQuery(Chuyenxe.class);
            Root rootCX = q.from(Chuyenxe.class);
            Root rootTX = q.from(Taixe.class);
            Taixe t = u.getTaixe();
            Predicate p1 = null;
            q.select(rootCX);

            if (t.getLoaitx() == '1') {
                p1 = b.equal(rootCX.get("maTaiXeChinh"), rootTX.get("maTaiXe"));

            } else if (t.getLoaitx() == '0') {
                p1 = b.equal(rootCX.get("maTaiXePhu"), rootTX.get("maTaiXe"));

            }
            Predicate p2 = b.equal(rootTX.get("maTaiXe"), t.getMaTaiXe());

//
//if (t.getLoaitx() == '1') {
//                p1 = b.equal(rootCX.get("maTaiXeChinh"), t);
//
//            } else if (t.getLoaitx() == '0') {
//                p1 = b.equal(rootCX.get("maTaiXePhu"), t);
//
//            }
            q.where(b.and(p1, p2));

            q = q.orderBy(b.asc(rootCX.get("maChuyenXe")));

            Query query = s.createQuery(q);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
