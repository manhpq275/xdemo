package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public enum Region {
    HaNoi(0),
    HoChiMinh(1),
    DaNang(2),
    AnGiang(3),
    BaRiaVungTau(4),
    BacGiang(5),
    BacKan(6),
    BacLieu(7),
    BacNinh(8),
    BenTre(9),
    BinhDinh(10),
    BinhDuong(11),
    BinhPhuoc(12),
    BinhThuan(13),
    CaMau(14),
    CaoBang(15),
    DakLak(16),
    DakNong(17),
    DienBien(18),
    DongNai(19),
    DongThap(20),
    GiaLai(21),
    HaGiang(22),
    HaNam(23),
    HaTinh(24),
    HaiDuong(25),
    HaiPhong(26),
    HauGiang(27),
    HoaBinh(28),
    HungYen(29),
    KhanhHoa(30),
    KienGiang(31),
    KonTum(32),
    LaiChau(33),
    LamDong(34),
    LangSon(35),
    LaoCai(36),
    LongAn(37),
    NamDinh(38),
    NgheAn(39),
    NinhBinh(40),
    NinhThuan(41),
    PhuTho(42),
    QuangBinh(43),
    QuangNam(44),
    QuangNgai(45),
    QuangTri(46),
    SocTrang(47),
    SonLa(48),
    TayNinh(49),
    ThaiBinh(50),
    ThaiNguyen(51),
    ThanhHoa(52),
    TraVinh(53),
    TuyenQuang(54),
    VinhLong(55),
    VinhPhuc(56),
    YenBai(57),
    PhuYen(58);

    private int value;
    Region(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Region fromInteger(int id){
        Region[] values = Region.values();
        for (Region v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
