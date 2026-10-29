package com.axity.curso.retailservice.config;

import com.axity.curso.retailservice.model.Product;

import com.axity.curso.retailservice.model.PurchaseOrder;
import com.axity.curso.retailservice.repository.ProductRepository;
import com.axity.curso.retailservice.repository.PurchaseOrderRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    // Inyectamos ambos repositorios
    public DatabaseSeeder(ProductRepository productRepository , PurchaseOrderRepository purchaseOrderRepository) {
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        // ---------------------------------------------------------
        // 1. POBLAR 120 PRODUCTOS
        // ---------------------------------------------------------
        if (productRepository.count() == 0) {
            List<Product> productsToSave = new ArrayList<>();
            
            // Diccionarios de datos para generar combinaciones aleatorias
            String[] categories = {"Periféricos", "Monitores", "Componentes", "Audio", "Laptops", "Accesorios"};
            String[] brands = {"Logitech", "ASUS", "EPOMAKER", "Sony", "Corsair", "Razer", "HyperX"};
            String[] types = {"Teclado", "Mouse", "Monitor", "Audífonos", "Gabinete", "Memoria RAM"};

            for (int i = 1; i <= 120; i++) {
                String brand = brands[random.nextInt(brands.length)];
                String type = types[random.nextInt(types.length)];
                String category = categories[random.nextInt(categories.length)];

                Product p = new Product();
                // Genera un SKU como: SKU-LOG-0045
                p.setSku("SKU-" + brand.substring(0, 3).toUpperCase() + "-" + String.format("%04d", i));
                p.setName(type + " " + brand + " Serie " + i);
                p.setDescription(type + " de alta calidad diseñado para máximo rendimiento. Marca " + brand);
                
                // Genera un precio aleatorio entre $500 y $8500
                double randomPrice = 500 + (8000 * random.nextDouble());
                p.setPrice(BigDecimal.valueOf(randomPrice).setScale(2, RoundingMode.HALF_UP));
                
                p.setStock(random.nextInt(100) + 1); // Stock entre 1 y 100
                p.setCategory(category);
                p.setDeleted(false);

                productsToSave.add(p);
            }
            productRepository.saveAll(productsToSave);
            System.out.println("✅ Base de datos poblada con " + productRepository.count() + " productos dinámicos.");
        }

        // ---------------------------------------------------------
        // 2. POBLAR 120 ÓRDENES DE COMPRA
        // ---------------------------------------------------------
        // IMPORTANTE: Descomenta este bloque cuando tengas listo tu PurchaseOrderRepository
        if (purchaseOrderRepository.count() == 0) {
            List<PurchaseOrder> ordersToSave = new ArrayList<>();
            String[] statuses = {"PENDIENTE", "PROCESANDO", "COMPLETADA", "CANCELADA"};

            for (int i = 1; i <= 120; i++) {
                PurchaseOrder order = new PurchaseOrder();
                // Genera número de orden como: ORD-2026-0045
                order.setOrderNumber("ORD-2026-" + String.format("%04d", i));
                
                // Monto aleatorio entre $1000 y $15000
                double randomAmount = 1000 + (14000 * random.nextDouble());
                
                // NOTA: Si en tu entidad PurchaseOrder el totalAmount es Double, usa esto:
                order.setTotalAmount(Math.round(randomAmount * 100.0) / 100.0);
                
                // Si en tu entidad es BigDecimal, usa esto:
                // order.setTotalAmount(BigDecimal.valueOf(randomAmount).setScale(2, RoundingMode.HALF_UP));

                order.setStatus(statuses[random.nextInt(statuses.length)]);
                ordersToSave.add(order);
            }
            purchaseOrderRepository.saveAll(ordersToSave);
            System.out.println("✅ Base de datos poblada con " + purchaseOrderRepository.count() + " órdenes de compra dinámicas.");
        }
    }
}