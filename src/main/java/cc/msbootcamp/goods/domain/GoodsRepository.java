package cc.msbootcamp.goods.domain;

import cc.msbootcamp.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsRepository {
    @Autowired
    private GoodsService goodsService;

    public List<Goods> findAll(){
        return null;
    }
}
