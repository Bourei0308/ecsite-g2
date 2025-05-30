package com.example.demo.shop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EcommerceController {

    private final ItemDao itemDao;
    private final Map<Integer, Integer> cart = new HashMap<>();

    public EcommerceController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    // 商品一覧
    @GetMapping("/items")
    public String listItems(Model model) {
        model.addAttribute("itemList", itemDao.findAll());
        return "item_list";
    }


    // 商品詳細
    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable Integer id, Model model) {
        itemDao.findById(id).ifPresent(item -> model.addAttribute("item", item));
        return "item_detail";
    }

    // カートに追加
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Integer itemId, @RequestParam Integer quantity) {
        cart.put(itemId, cart.getOrDefault(itemId, 0) + quantity);
        return "redirect:/cart";
    }

    // カート表示
    @GetMapping("/cart")
    public String showCart(Model model) {
        List<Item> items = new ArrayList<>();
        int total = 0;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            itemDao.findById(entry.getKey()).ifPresent(item -> {
                item.setStock(entry.getValue());
                items.add(item);
            });
            total += itemDao.findById(entry.getKey())
                    .map(i -> i.getPrice() * entry.getValue())
                    .orElse(0);
        }

        model.addAttribute("cartItems", items);
        model.addAttribute("total", total);
        return "cart";
    }

    // 購入画面表示
    @GetMapping("/purchase")
    public String purchaseForm(Model model) {
        model.addAttribute("customerOrder", new CustomerOrder());
        return "purchase";
    }

    // 購入完了
    @PostMapping("/purchase/complete")
    public String completePurchase(@ModelAttribute CustomerOrder order, Model model) {
        model.addAttribute("message", "購入が完了しました。");
        cart.clear();
        return "purchase_complete";
    }
    
}