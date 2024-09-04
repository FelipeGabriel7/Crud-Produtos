package br.com.example;

import java.util.List;

import br.com.example.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Produto> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Produto());
        return "new_product";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Produto product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable("id") Long productId, Model model) {
        Produto product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable("id") Long productId, @ModelAttribute("product") Produto product) {
        product.setId(productId);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/products";
    }

}
