package cc.msbootcamp.goods.controller;

import cc.msbootcamp.goods.domain.Goods;
import cc.msbootcamp.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    public List<Goods> goods() {
        return goodsService.listGoods();
    }
}
