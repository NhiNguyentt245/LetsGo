/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.service.impl;

import com.at.repository.BieuDoRepository;
import com.at.service.BieuDoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thu
 */
@Service
public class BieuDoServiceImpl implements BieuDoService {

    @Autowired
    BieuDoRepository bieuDoRepository;

    @Override
    public List<Object[]> doanhThu(int tk) {

        List<Object[]> ob = this.bieuDoRepository.doanhThu(tk);
        List<Object[]> kq = new ArrayList<>();
        if (tk == 0) {
            for (int i = 1; i <= 12; i++) {
                int check = 0;
                for (Object[] o : ob) {

                    if (o[0].equals(i)) {
                        kq.add(o);
                        check = 1;
                    }
                }

                if (check == 0) {
                    Object[] value = new Object[2];
                    value[0] = i;
                    value[1] = 0;
                    kq.add(value);
                }
            }

        } else if (tk == 2) {
            int y = new Date().getYear() + 1900;
            for (int i = y - 5; i <= y; i++) {
                int check = 0;
                for (Object[] o : ob) {

                    if (o[0].equals(i)) {
                        kq.add(o);
                        check = 1;
                    }
                }

                if (check == 0) {
                    Object[] value = new Object[2];
                    value[0] = i;
                    value[1] = 0;
                    kq.add(value);
                }
            }

        }
//        else if(tk == 1)
//        {
//           for (int i = 1; i <= 12; i++) 
//           {
//               
//               if(i < 4)
//               {
//                   for (Object[] o : ob) {
//                    if (o[0].equals(i)) {
//                        kq.add(o);
//                    }
//                }
//               }
//               
//                
//
//                if (check == 0) {
//                    Object[] value = new Object[2];
//                    value[0] = i;
//                    value[1] = 0;
//                    kq.add(value);
//                }
//            }
//
//        }

        return kq;
    }

    @Override
    public List<Object[]> matDoChuyenXe() {
        return this.bieuDoRepository.matDoChuyenXe();
    }

    @Override
    public Map<Integer, BigDecimal> thongKeDoanhThu(int tk) {

        List<Object[]> listO = this.bieuDoRepository.doanhThu(tk);
        Map<Integer, BigDecimal> map = new HashMap<>();

        Map<Integer, BigDecimal> kq = new HashMap<>();

        for (Object[] o : listO) {
            map.put(Integer.parseInt(o[0].toString()), BigDecimal.valueOf(Double.parseDouble(o[1].toString())));
        }
        if (tk == 0) {
            for (int i = 1; i <= 12; i++) {
                if (map.containsKey(i)) {
                    kq.put(i, map.get(i));
                } else {
                    kq.put(i,BigDecimal.valueOf(0));
                }

            }

        } else if (tk == 2) {
            int y = new Date().getYear() + 1900;
            for (int i = y - 5; i <= y; i++) {
                if (map.containsKey(i)) {
                    kq.put(i, map.get(i));
                } else {
                    kq.put(i, BigDecimal.valueOf(0));
                }
            }
        } 
        else if (tk == 1) {
            
            kq.put(1,BigDecimal.valueOf(0));
            kq.put(2, BigDecimal.valueOf(0));
            kq.put(3, BigDecimal.valueOf(0));
            kq.put(4, BigDecimal.valueOf(0));
            
            for (int i = 1; i <= 12; i++) {

                if (i <= 3) {
                    if (map.containsKey(i)) {
                        kq.put(1, map.get(i).add(kq.get(1)));
                    }
                }
                else if(i <= 6)
                {
                    if (map.containsKey(i)) {
                        kq.put(2, map.get(i).add(kq.get(2)));
                    }
                }
                
                 else if(i <= 9 )
                {
                    if (map.containsKey(i)) {
                        kq.put(3, map.get(i).add(kq.get(3)));
                    }
                }
                else
                {
                    if (map.containsKey(i)) {
                        kq.put(4, map.get(i).add(kq.get(4)));
                    }
                }
                

            }
        }

        return kq;
    }

}
