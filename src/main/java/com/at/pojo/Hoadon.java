/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thu
 */
@Entity
@Table(name = "hoadon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoadon.findAll", query = "SELECT h FROM Hoadon h"),
    @NamedQuery(name = "Hoadon.findByMaHoaDon", query = "SELECT h FROM Hoadon h WHERE h.maHoaDon = :maHoaDon"),
    @NamedQuery(name = "Hoadon.findByHinhThucTT", query = "SELECT h FROM Hoadon h WHERE h.hinhThucTT = :hinhThucTT"),
    @NamedQuery(name = "Hoadon.findByTrangThaiTT", query = "SELECT h FROM Hoadon h WHERE h.trangThaiTT = :trangThaiTT"),
    @NamedQuery(name = "Hoadon.findByNgayDatVe", query = "SELECT h FROM Hoadon h WHERE h.ngayDatVe = :ngayDatVe"),
    @NamedQuery(name = "Hoadon.findBySoVe", query = "SELECT h FROM Hoadon h WHERE h.soVe = :soVe"),
    @NamedQuery(name = "Hoadon.findByTongTien", query = "SELECT h FROM Hoadon h WHERE h.tongTien = :tongTien")})
public class Hoadon implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHoaDon")
    private Integer maHoaDon;
    @Column(name = "HinhThucTT")
    private Character hinhThucTT;
    @Column(name = "TrangThaiTT")
    private Boolean trangThaiTT;
    
    @Column(name = "SDT")
    private String sdt;
    
     
    @Column(name = "Email")
    private String email;
    
      
    @Column(name = "HoTen")
    private String hoTen;
    
      
    @Column(name = "GhiChu")
    private String ghiChu;
    
    @Column(name = "NgayDatVe")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDatVe;
    @Column(name = "SoVe")
    private Integer soVe;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @JoinColumn(name = "MaChuyenXe", referencedColumnName = "MaChuyenXe")
    @ManyToOne
    private Chuyenxe maChuyenXe;
    @JoinColumn(name = "MaKH", referencedColumnName = "MaKH")
    @ManyToOne
    private Khachhang maKH;
    
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne
    private Nhanvien maNV;
    @OneToMany(mappedBy = "maHD",fetch = FetchType.EAGER)
    private Set<Chitiethoadon> chitiethoadonSet;
    @Transient
    @JsonIgnore
    private String trangThaiThanhToan;

    public Hoadon() {
    }

    public Hoadon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Character getHinhThucTT() {
        return hinhThucTT;
    }

    public void setHinhThucTT(Character hinhThucTT) {
        this.hinhThucTT = hinhThucTT;
    }

    public Boolean getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(Boolean trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }

    public Date getNgayDatVe() {
        return ngayDatVe;
    }

    public void setNgayDatVe(Date ngayDatVe) {
        this.ngayDatVe = ngayDatVe;
    }

    public Integer getSoVe() {
        return soVe;
    }

    public void setSoVe(Integer soVe) {
        this.soVe = soVe;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Chuyenxe getMaChuyenXe() {
        return maChuyenXe;
    }

    public void setMaChuyenXe(Chuyenxe maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    public Khachhang getMaKH() {
        return maKH;
    }

    public void setMaKH(Khachhang maKH) {
        this.maKH = maKH;
    }

    @XmlTransient
    public Set<Chitiethoadon> getChitiethoadonSet() {
        return chitiethoadonSet;
    }

    public void setChitiethoadonSet(Set<Chitiethoadon> chitiethoadonSet) {
        this.chitiethoadonSet = chitiethoadonSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getMaHoaDon() != null ? getMaHoaDon().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoadon)) {
            return false;
        }
        Hoadon other = (Hoadon) object;
        if ((this.getMaHoaDon() == null && other.getMaHoaDon() != null) || (this.getMaHoaDon() != null && !this.maHoaDon.equals(other.maHoaDon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.at.pojo.Hoadon[ maHoaDon=" + getMaHoaDon() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hoten
     */
    

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the maNV
     */
    public Nhanvien getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(Nhanvien maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the trangThaiThanhToan
     */
    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    /**
     * @param trangThaiThanhToan the trangThaiThanhToan to set
     */
    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }
    
}
