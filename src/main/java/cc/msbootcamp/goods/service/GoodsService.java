package cc.msbootcamp.goods.service;

import cc.msbootcamp.goods.domain.Goods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    public List<Goods> listGoods(){
        return new ArrayList<>();
    }
}
