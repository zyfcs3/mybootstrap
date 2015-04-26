package cn.zhuyafeng.mybootstrap.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item")
// 指定表名
public class Item extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 商品id
    private Long id;
    // 商品名称
    private String title;
    // 商品卖点
    @Column(name = "sell_point")
    private String sellPoint;
    // 商品价格
    private Long price;
    // 库存数量
    private Integer num;
    //商品图片路径
    private String image;
    
    //条形码
    private String barcode;
    //
    private Long cid;
    //商品状态
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price
                + ", num=" + num + ", image=" + image + ", barcode=" + barcode + ", cid=" + cid + ", status="
                + status + "]";
    }

}
