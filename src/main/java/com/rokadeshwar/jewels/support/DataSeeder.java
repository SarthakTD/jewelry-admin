package com.rokadeshwar.jewels.support;

import com.rokadeshwar.jewels.products.domain.Product;
import com.rokadeshwar.jewels.products.repo.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DataSeeder {
    private final ProductRepository repo;

    public DataSeeder(ProductRepository repo) { this.repo = repo; }

    @PostConstruct
    public void seed() {
        if (repo.count() > 0) return;

        String[] bases = {"Mangalsutra","Necklace","Earrings","Bracelet","Ganthan","Ring","Pendant","Bangle","Anklet"};
        String[] adjs = {"Classic","Elegant","Statement","Delicate","Modern","Royal","Premium","Heritage","Aesthetic","Timeless"};

        long id = 1;
        List<Product> batch = new ArrayList<>();
        while (batch.size() < 50) {
            for (String base : bases) {
                if (batch.size() >= 50) break;
                String adj = adjs[(int)(id % adjs.length)];
                String title = adj + " " + base + " " + id;

                String slug = base.toLowerCase();
                String img1 = "/images/products/" + slug + "-1.png";
                String img2 = "/images/products/" + slug + "-2.png";
                String img3 = "/images/products/" + slug + "-3.png";
                BigDecimal price = BigDecimal.valueOf(1200 + (id * 137) % 3000);

                Map<String,String> specs = new LinkedHashMap<>();
                specs.put("type","Gold Ornament");
                specs.put("purity", (id % 2 == 0) ? "22 kt" : "18 kt");
                specs.put("grossWt", String.format("%.2f", 10 + (id % 10) + 0.14));
                specs.put("netWt", String.format("%.2f", 9.7 + (id % 10) + 0.18));
                specs.put("colour", (id % 3 == 0) ? "Rose Gold" : "Yellow Gold");
                specs.put("productType", base);
                specs.put("gender", (id % 2 == 0) ? "Women" : "Men");
                specs.put("brand","Rokadeshwar");

                List<Map<String,String>> highlights = List.of(
                        Map.of("icon","purity","title","Purity Guaranteed"),
                        Map.of("icon","legacy","title","Trusted Legacy"),
                        Map.of("icon","aesthetic","title","Aesthetic Design")
                );

                Product p = new Product();
                p.setTitle(title);
                p.setBaseType(base);
                p.setPrice(price);
                p.setImageUrl(img1);
                p.setBestSeller(true);
                p.setImages(List.of(img1,img2,img3));
                p.setSpecs(specs);
                p.setHighlights(highlights);

                batch.add(p);
                id++;
            }
        }
        repo.saveAll(batch);
    }
}
